import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Blob.\nWhat can I do for you?\n");
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            if (message.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println();
            } else if (message.split(" ")[0].equalsIgnoreCase("mark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    if ((n > 0) && (tasks.get(n - 1) != null)) {
                        tasks.get(n - 1).setDone();
                        System.out.println("Nice! I've marked this task done:");
                        System.out.println("[" + tasks.get(n - 1).getStatusIcon() + "] "
                                + tasks.get(n - 1).getDescription() + "\n");
                    } else {
                        System.out.println("You don't have that task!\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!");
                    System.out.println("Type: 'mark n' to mark the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'mark n' to mark the n-th task.");
                    System.out.println("For example type: 'mark 1' to mark the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("unmark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    if ((n > 0) && (tasks.get(n - 1) != null)) {
                        tasks.get(n - 1).setUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[" + tasks.get(n - 1).getStatusIcon() + "] "
                                + tasks.get(n - 1).getDescription() + "\n");
                    } else {
                        System.out.println("You don't have that task!\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!");
                    System.out.println("Type: 'unmark n' to unmark the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'unmark n' to unmark the n-th task.");
                    System.out.println("For example, type: 'unmark 1' to mark the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("delete")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    if ((n > 0) && (tasks.get(n - 1) != null)) {
                        Task removed = tasks.remove(n - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removed);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                    } else {
                        System.out.println("You don't have that task!\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!");
                    System.out.println("Type: 'delete n' to delete the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'delete n' to delete the n-th task.");
                    System.out.println("For example type: 'delete 1' to delete the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("todo")) {
                try {
                    String task = message.split(" ", 2)[1];
                    ToDo todo = new ToDo(task);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("todo <description>\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("deadline")) {
                try {
                    String task = message.split(" ", 2)[1];
                    String description = task.split(" /by ", 2)[0];
                    String by = task.split(" /by ", 2)[1];
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("deadline <description> /by <deadline time>\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("event")) {
                try {
                    String task = message.split(" ", 2)[1];
                    String description = task.split(" /from ", 2)[0];
                    String fromBy = task.split(" /from ", 2)[1];
                    String from = fromBy.split(" /to ", 2)[0];
                    String to = fromBy.split(" /to ", 2)[1];
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("event <description> /from <start time> /to <end time>\n");
                }
            } else {
                System.out.println("Sorry :(");
                System.out.println("You need to use 'todo', 'deadline' or 'event' command to add a task.");
                System.out.println("You can use 'list' to see all of your tasks.");
                System.out.println("Use 'mark' or 'unmark' for any of your tasks.\n");
            }
            message = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
