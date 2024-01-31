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
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription() + "\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("mark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    if ((n > 0) && (tasks[n - 1] != null)) {
                        tasks[n - 1].setDone();
                        System.out.println("Nice! I've marked this task done:\n");
                        System.out.println("[" + tasks[n - 1].getStatusIcon() + "] "
                                + tasks[n - 1].getDescription() + "\n");
                    } else {
                        System.out.println("You don't have that task!\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!\n");
                    System.out.println("Type: 'mark n' to mark the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'mark n' to mark the n-th task.\n");
                    System.out.println("For example type: 'mark 1' to mark the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("unmark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    if ((n > 0) && (tasks[n - 1] != null)) {
                        tasks[n - 1].setUndone();
                        System.out.println("OK, I've marked this task as not done yet:\n");
                        System.out.println("[" + tasks[n - 1].getStatusIcon() + "] "
                                + tasks[n - 1].getDescription() + "\n");
                    } else {
                        System.out.println("You don't have that task!\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!\n");
                    System.out.println("Type: 'unmark n' to unmark the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'unmark n' to unmark the n-th task.\n");
                    System.out.println("For example, type: 'unmark 1' to mark the first task.\n");
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
