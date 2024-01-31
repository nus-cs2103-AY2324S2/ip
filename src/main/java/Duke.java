import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void readFile(ArrayList<Task> tasks) {
        try {
            File file = new File("./duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] task = line.split("," );
                Task t = null;
                switch (task[0]) {
                    case "T":
                        t = new Todo(task[2]);
                        break;
                    case "D":
                        t = new Deadline(task[2], task[3]);
                        break;
                    case "E":
                        t = new Event(task[2], task[3], task[4]);
                        break;
                }
                if (task[1].equals("1")) {
                    t.mark();
                }
                tasks.add(t);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void updateFile(ArrayList<Task> tasks) {
        try {
            File file = new File("./duke.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("created file");
            }
            PrintWriter writer = new PrintWriter(file);
            for (Task t: tasks) {
                writer.println(t.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        Duke.readFile(tasks);

        System.out.println(
                "Hello! I'm Bob\n" +
                "What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            try {
                String command = userInput.nextLine();
                if (command.equals("list")) {
                    System.out.println("Here are your tasks in your list:");
                    for (Task t : tasks) {
                        System.out.println((tasks.indexOf(t) + 1) + "." + t);
                    }

                } else if (command.equals("bye")) {
                    Duke.updateFile(tasks);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);

                } else if (command.contains("unmark")) {
                    String[] splitCommand = command.split(" ");
                    if (splitCommand.length==1) {
                        throw new DukeException("Task number of unmark cannot be empty.");
                    }

                    int taskNumber = Integer.valueOf(splitCommand[1]);
                    if (taskNumber > tasks.size()) {
                        throw new DukeException("You do not have " + taskNumber + " tasks.");
                    }

                    Task t = tasks.get(taskNumber - 1);
                    t.unmark();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(t);

                } else if (command.contains("mark") ) {
                    String[] splitCommand = command.split(" ");
                    if (splitCommand.length==1) {
                        throw new DukeException("Task number of mark cannot be empty.");
                    }

                    int taskNumber = Integer.valueOf(splitCommand[1]);
                    if (taskNumber > tasks.size()) {
                        throw new DukeException("You do not have " + taskNumber + " tasks.");
                    }

                    Task t = tasks.get(taskNumber - 1);
                    t.mark();
                    System.out.println("Nice, I've marked this task as done:");
                    System.out.println(t);

                } else if (command.contains("delete")) {
                    String[] splitCommand = command.split(" ");
                    if (splitCommand.length==1) {
                        throw new DukeException("Task number of delete cannot be empty.");
                    }

                    int taskNumber = Integer.valueOf(splitCommand[1]);
                    if (taskNumber > tasks.size()) {
                        throw new DukeException("You do not have " + taskNumber + " tasks.");
                    }

                    Task t = tasks.remove(taskNumber - 1);
                    System.out.println("Ok, I've removed this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

                } else if (command.contains("todo") ||
                        command.contains("deadline") ||
                        command.contains("event")) {
                    Task t = null;
                    if (command.contains("todo")) {
                        String[] splitCommand = command.split(" ",2);
                        if (splitCommand.length==1) {
                            throw new DukeException("Description of a todo cannot be empty.");
                        } else {
                            t = new Todo(splitCommand[1]);
                        }

                    } else if (command.contains("deadline")) {
                        String[] splitCommand = command.split(" ",2);
                        if (splitCommand.length==1) {
                            throw new DukeException("Description of a deadline cannot be empty.");
                        }

                        String[] splitDesc = splitCommand[1].split(" /by ", 2);
                        if (splitDesc.length==1) {
                            throw new DukeException("Date/time of a deadline cannot be empty.");
                        }

                        t = new Deadline(splitDesc[0], splitDesc[1]);

                    } else if (command.contains("event")) {
                        String[] splitCommand = command.split(" ",2);
                        if (splitCommand.length==1) {
                            throw new DukeException("Description of a deadline cannot be empty.");
                        }

                        String[] splitDesc = splitCommand[1].split(" /from ", 2);
                        if (splitDesc.length==1) {
                            throw new DukeException("Start date/time of a deadline cannot be empty.");
                        }

                        String[] splitFrom = splitDesc[1].split(" /to ", 2);
                        if (splitFrom.length==1) {
                            throw new DukeException("End date/time of a deadline cannot be empty.");
                        }

                        t = new Event(splitDesc[0], splitFrom[0], splitFrom[1]);

                    }
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                } else {
                    throw new DukeException("I don't know what that means.");
                }
            } catch (DukeException err) {
                System.out.println(err);
            }
        }
    }
}
