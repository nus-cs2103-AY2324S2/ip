import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm steve\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";

        String goodbye = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";

        System.out.println(greeting);

        Task[] myList = new Task[100];
        int listIndex = 0;

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
                        for (int i = 0; i < listIndex; i++) {
                            System.out.println((i + 1) + ". " + myList[i]);
                        }
                        break;
                    case "mark":
                        if (params.length() == 0) {
                            throw new DukeException.MarkParamsException();
                        }
                        int num = Integer.valueOf(params);
                        Task t = myList[num - 1];
                        t.markAsDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                        break;
                    case "unmark":
                        if (params.length() == 0) {
                            throw new DukeException.MarkParamsException();
                        }
                        num = Integer.valueOf(params);
                        t = myList[num - 1];
                        t.unmarkAsDone();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t);
                        break;
                    case "todo":
                        if (params.length() == 0) {
                            throw new DukeException.TodoDescriptionMissingException();
                        }

                        String desc = params;

                        Task newTask = new Todo(desc);
                        myList[listIndex++] = newTask;

                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + listIndex + " tasks in the list");
                        break;
                    case "deadline":
                        if (!params.contains("/by")) {
                            throw new DukeException.DeadlineDetailsMissingException();
                        }

//                        params = params.substring(cmd.length());

                        desc = params.split("/by")[0].trim();
                        String by = params.split("/by")[1].trim();

                        newTask = new Deadline(desc, by);
                        myList[listIndex++] = newTask;

                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + listIndex + " tasks in the list");
                        break;
                    case "event":
                        if (!params.contains("/from") || !params.contains("/to")) {
                            throw new DukeException.EventDetailsMissingException();
                        }

//                        params = params.substring(cmd.length());

                        desc = params.split("/from")[0];
                        String from = params.split("/from")[1].split("/to")[0].trim();
                        String to = params.split("/to")[1].trim();

                        newTask = new Event(desc, from, to);
                        myList[listIndex++] = newTask;

                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + listIndex + " tasks in the list");
                        break;
                    default:
                        throw new DukeException.UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println("DukeException: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred.");
            } finally {
                System.out.println(border);
            }

            line = scanner.nextLine();
        }


        System.out.println(goodbye);

    }
}
