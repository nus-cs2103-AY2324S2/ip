import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Task> list = new ArrayList<Task>();
    private String name = "NotDuke";

    private void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
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
                unmark(input[1]);
            } else if (command.equals("mark")) {
                mark(input[1]);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                add(rawInput);
            } else {
                throw new DukeInvalidCommand(rawInput);
            }
        } catch (DukeException e) {
            System.out.println(e);
        } catch (Exception a) {
            System.out.println(a);
        } finally{
            input();
        }
    }

    private void add(String input) throws DukeMissingArgument{
        Task task;
        String[] info = input.split(" ", 2);
        String command = info[0];
        if (command.equals("todo")) {
            try {
                task = new ToDos(info[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingArgument(1,command);
            }
        } else if (command.equals("deadline")) {
            try {
                String[] inputs = info[1].split(" /by ", 2);
                task = new Deadlines(inputs[0], inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingArgument(2,command);
            }
        } else if (command.equals("event")) {
            try {
                String[] event = info[1].split(" /from ", 2);
                String[] time = event[1].split(" /to ");
                task = new Events(event[0], time[0], time[1]);
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
        System.out.print("Now you have " + length + " task");
        if (length > 1) {System.out.print("s");}
        System.out.println(" in the list.");
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
    private void mark(String input) throws DukeException {
        try {
            int index = Integer.valueOf(input);
            Task task = list.get(index - 1);
            task.mark();
        } catch (StringIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument(1, "mark");
        } catch (NumberFormatException e2) {
            throw new DukeInvalidArgument();
        } catch (IndexOutOfBoundsException e3) {
            int index = Integer.valueOf(input);
            throw new DukeTaskNotFound(index);
        }
    }

    private void unmark(String input) throws DukeException {
        try {
            int index = Integer.valueOf(input);
            Task task = list.get(index - 1);
            task.unmark();
        } catch (StringIndexOutOfBoundsException e1) {
            throw new DukeMissingArgument(1, "unmark");
        } catch (NumberFormatException e2) {
            throw new DukeInvalidArgument();
        } catch (IndexOutOfBoundsException e3) {
            int index = Integer.valueOf(input);
            throw new DukeTaskNotFound(index);
        }
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.input();
        bot.exit();
    }
}

