import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static String space = "    ";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Dune");
        System.out.println("What can I do for you?");
        List<Task> tasks = new ArrayList<>();
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("list")) {
                // print out tasks line by line
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(space + i + ". " + tasks.get(i));
                }
                continue;
            } else if (text.equals("bye")) {
                // exit program
                break;
            }

            // check if the first 5 characters matches "mark "
            String upTo5Characters = text.substring(0, Math.min(text.length(), 5));
            if (upTo5Characters.equals("mark ")) {
                try {
                    String remaining = text.substring(5);
                    // parseInt might throw NumberFormatException
                    int index = Integer.parseInt(remaining);
                    // Index... exception
                    tasks.get(index).complete();
                    System.out.println(space + "Nice! I've marked this task as done:");
                    System.out.println(space + tasks.get(index));

                } catch (IndexOutOfBoundsException i) {
                    System.out.println(space + "Give a valid index to mark");
                } catch (NumberFormatException n) {
                    System.out.println(space + "Remaining characters do not match an integer");
                } finally {
                    continue;
                }
            }

            // check if first 7 characters match "unmark "

            // check if first x characters match "todo "
            String check = "todo ";
            if (text.startsWith(check)) {
                createNewToDo(text.substring(check.length()), tasks);
                continue;
            }

            // check if first x characters match "deadline "
            check = "deadline ";
            if (text.startsWith(check)) {
                createNewDeadline(text.substring(check.length()), tasks);
                continue;
            }

            // check if first x characters match "event "
            check = "event ";
            if (text.startsWith(check)) {
                createNewEvent(text.substring(check.length()), tasks);
                continue;
            }


            tasks.add(new Task(text));
            System.out.println(space + "added:" + text);  // Output user input
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void createNewToDo(String text, List<Task> tasks) {
        Task x = new ToDo(text);
        tasks.add(x);
        print(x, tasks);
    }

    public static void createNewDeadline(String text, List<Task> tasks) {
        // expected of length 2
        String[] parts = text.split("/by");
        Task x = new Deadline(parts[0], parts[1]);
        tasks.add(x);
        print(x, tasks);
    }

    public static void createNewEvent(String text, List<Task> tasks) {
        // expected of length 2
        String[] parts = text.split("/from");
        String[] dates = parts[1].split("/to");
        Task x = new Event(parts[0], dates[0], dates[1]);
        tasks.add(x);
        print(x, tasks);

    }

    public static void print(Task x, List<Task> tasks) {
        System.out.println(space + "Got it. I've added this task: ");
        System.out.println(space + x);
        System.out.println(space + "Now you have " + tasks.size() + " tasks in your list.");
    }
}
