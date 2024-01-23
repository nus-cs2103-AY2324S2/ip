import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static String space = "    ";

    private static String[] taskTypes = new String[] {"todo ", "deadline ", "event "};
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Dune");
        System.out.println("What can I do for you?");
        List<Task> tasks = new ArrayList<>();
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("list")) {
                // print out tasks line by line
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i));
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
                    tasks.get(index - 1).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1));

                } catch (IndexOutOfBoundsException i) {
                    System.out.println("Give a valid index to mark");
                } catch (NumberFormatException n) {
                    System.out.println("Remaining characters do not match an integer");
                } finally {
                    continue;
                }
            }


            boolean addedTask = false;
            String check = "";
            for (int i = 0; i < taskTypes.length; i++) {
                check = taskTypes[i];
                if (text.startsWith(check)) {
                    createNewTask(i, text.substring(check.length()), tasks);
                    addedTask = true;
                    continue;
                }
            }
            if (addedTask) {
                continue;
            }

            tasks.add(new Task(text));
            System.out.println("added: " + text);  // Output user input
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void createNewTask(int i, String text, List<Task> tasks) {
        Task x = new Task("");
        if (i == 0) {
            x = new ToDo(text);
        } else if (i == 1) {
            String[] parts = text.split("/by");
            x = new Deadline(parts[0], parts[1]);
        } else if (i == 2) {
            String[] parts = text.split("/from");
            String[] dates = parts[1].split("/to");
            x = new Event(parts[0], dates[0], dates[1]);
        }
        tasks.add(x);
        System.out.println("Got it. I've added this task:");
        System.out.println(x);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }
}
