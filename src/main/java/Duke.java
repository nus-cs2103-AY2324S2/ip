import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
//    public static final String DBPATH = "../data/duke.txt"; // uncommment for runtest.sh
    public static final String DBPATH = "data/duke.txt";
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm steve\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";

        String goodbye = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";

        System.out.println(greeting);

        TaskList myTasks = new TaskList();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String line = scanner.nextLine(); // Get first input

        while (!Objects.equals(line, "bye")) {
            String border = "____________________________________________________________";

            try {
                System.out.println(border);

                String cmd = line.split(" ")[0];
                String params = line.substring(cmd.length()).trim();
                switch (cmd) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        myTasks.printTasks();
                        break;
                    case "mark":
                        if (params.length() == 0) {
                            throw new DukeException.MarkParamsException();
                        }
                        int num = Integer.valueOf(params);
                        myTasks.markTask(num);
                        Task t = myTasks.getTask(num);

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                        break;
                    case "unmark":
                        if (params.length() == 0) {
                            throw new DukeException.MarkParamsException();
                        }
                        num = Integer.valueOf(params);
                        myTasks.unmarkTask(num);
                        t = myTasks.getTask(num);

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t);
                        break;
                    case "delete":
                        if (params.length() == 0) {
                            throw new DukeException.DeleteParamsException();
                        }
                        num = Integer.valueOf(params);
                        Task toDelete = myTasks.getTask(num);
                        myTasks.deleteTask(num);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(toDelete);
                        System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                        break;
                    case "todo":
                        if (params.length() == 0) {
                            throw new DukeException.TodoDescriptionMissingException();
                        }

                        String desc = params;

                        Task newTask = new Todo(desc);
                        myTasks.addTask(newTask);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                        break;
                    case "deadline":
                        if (!params.contains("/by")) {
                            throw new DukeException.DeadlineDetailsMissingException();
                        }

                        desc = params.split("/by")[0].trim();
                        String by = params.split("/by")[1].trim();

                        newTask = new Deadline(desc, by);
                        myTasks.addTask(newTask);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                        break;
                    case "event":
                        if (!params.contains("/from") || !params.contains("/to")) {
                            throw new DukeException.EventDetailsMissingException();
                        }

                        desc = params.split("/from")[0];
                        String from = params.split("/from")[1].split("/to")[0].trim();
                        String to = params.split("/to")[1].trim();

                        newTask = new Event(desc, from, to);
                        myTasks.addTask(newTask);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                        break;
                    case "clear":
                        myTasks.clear();
                        System.out.println("Got it. I've cleared the database.");
                        break;
                    default:
                        throw new DukeException.UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println("DukeException: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("An unexpected error occurred.");
            } finally {
                System.out.println(border);
            }

            line = scanner.nextLine();
        }


        System.out.println(goodbye);

    }
}
