import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Task> list = new ArrayList<Task>();
    private String name = "NotDuke";

    private void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    private void load() {
        try {
            File f = new File("./data/tasks.txt");
            Scanner readFile = new Scanner(f);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] inputs = line.split(" \\| ");
                Boolean isDone;
                if (inputs[1].equals("0")) {
                    isDone = false;
                } else {
                    isDone = true;
                }
                switch (inputs[0]) {
                case "T":
                    Task todo = new ToDos(inputs[2], isDone);
                    list.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadlines(inputs[2], inputs[3], isDone);
                    list.add(deadline);
                    break;
                case "E":
                    String[] time = inputs[3].split("-");
                    Task event = new Events(inputs[2], time[0], time[1], isDone);
                    list.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No Save History");
        }

    }

    private void input() {
        try {
            String rawInput = scanner.nextLine();
            String[] input = rawInput.split(" ", 2);
            String command = input[0];
            if (command.equals("bye")) {
                return;
            }

            if (command.equals("list")) {
                list();
            } else if (command.equals("unmark")) {
                unmark(input);
            } else if (command.equals("mark")) {
                mark(input);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                add(input);
            } else if (command.equals("delete")) {
                delete(input);
            } else {
                throw new DukeInvalidCommand(rawInput);
            }
        } catch (DukeException e) {
            System.out.println(e);
        }

        input();
    }

    private void add(String[] input) throws DukeMissingArgument{
        Task task;
        String command = input[0];
        if (command.equals("todo")) {
            try {
                task = new ToDos(input[1], false);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingArgument(1,command);
            }
        } else if (command.equals("deadline")) {
            try {
                String[] values = input[1].split(" /by ", 2);
                task = new Deadlines(values[0], values[1], false);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingArgument(2,command);
            }
        } else if (command.equals("event")) {
            try {
                String[] event = input[1].split(" /from ", 2);
                String[] time = event[1].split(" /to ");
                task = new Events(event[0], time[0], time[1], false);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingArgument(3, command);
            }
        } else {
            return;
        }

        list.add(task);
        int length = list.size();
        System.out.println("Got it. I've added this task:");
        task.taskInfo();
        liststatus();

    }

    private void list() {
        if (list.size() == 0) {
            System.out.println("The list is empty");
        }
        int index = 0;
        for (Task item : list) {
            System.out.print(++index + ".");
            item.taskInfo();
        }
    }

    private void liststatus() {
        int length = list.size();
        System.out.print("Now you have " + length + " task");
        if (length > 1) {System.out.print("s");}
        System.out.println(" in the list.");
    }
    private void mark(String[] input) throws DukeException {
        try {
            int index = Integer.valueOf(input[1]);
            Task task = list.get(index - 1);
            task.mark();
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument(1, input[0]);
        } catch (NumberFormatException e2) {
            throw new DukeInvalidArgument();
        } catch (IndexOutOfBoundsException e3) {
            int index = Integer.valueOf(input[1]);
            throw new DukeTaskNotFound(index);
        }
    }

    private void unmark(String[] input) throws DukeException {
        try {
            int index = Integer.valueOf(input[1]);
            Task task = list.get(index - 1);
            task.unmark();
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument(1, input[0]);
        } catch (NumberFormatException e2) {
            throw new DukeInvalidArgument();
        } catch (IndexOutOfBoundsException e3) {
            int index = Integer.valueOf(input[1]);
            throw new DukeTaskNotFound(index);
        }
    }
    private void delete(String[] input) throws DukeException {
        try {
            int index = Integer.valueOf(input[1]);
            Task removed = list.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            removed.taskInfo();
            liststatus();
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument(1, "mark");
        } catch (NumberFormatException e2) {
            throw new DukeInvalidArgument();
        } catch (IndexOutOfBoundsException e3) {
            int index = Integer.valueOf(input[1]);
            throw new DukeTaskNotFound(index);
        }
    }

    private void save() {
        try {
            File savefile = new File("./data/tasks.txt");
            savefile.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(savefile);
            for (Task t : list) {
                fw.write(t.saveOutput());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to Save");
        }
    }


    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.load();
        bot.input();
        bot.save();
        bot.exit();
    }
}

