import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Blob.\nWhat can I do for you?\n");
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            if (message.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("mark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    if ((n > 0) && (tasks[n - 1] != null)) {
                        tasks[n - 1].setDone();
                        System.out.println("Nice! I've marked this task done:");
                        System.out.println("[" + tasks[n - 1].getStatusIcon() + "] "
                                + tasks[n - 1].getDescription() + "\n");
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
                    if ((n > 0) && (tasks[n - 1] != null)) {
                        tasks[n - 1].setUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[" + tasks[n - 1].getStatusIcon() + "] "
                                + tasks[n - 1].getDescription() + "\n");
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
            } else if (message.split(" ")[0].equalsIgnoreCase("todo")) {
                try {
                    String task = message.split(" ", 2)[1];
                    ToDo todo = new ToDo(task);
                    tasks[count] = todo;
                    count++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + count + " tasks in the list.\n");
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
                    tasks[count] = deadline;
                    count++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + count + " tasks in the list.\n");
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
                    tasks[count] = event;
                    count++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + count + " tasks in the list.\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("event <description> /from <start time> /to <end time>\n");
                }
            } else {
                tasks[count] = new Task(message);
                count++;
                System.out.println("added: " + message + "\n");
            }
            message = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
