import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Cookie");
        System.out.println("What can I do for you?\n");

        Task[] tasks = new Task[100];
        int counter = 0;

        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                //print added items
                for (int i = 1; i <= counter; i++) {
                    Task task = tasks[i - 1];
                    String taskStatus = task.getStatusIcon();
                    String taskDesc = task.toString();
                    System.out.println(i + "." + taskDesc);
                }
                continue;
            }
            if (input.startsWith("mark ")) {
                String taskNum = input.substring(5);
                int taskNumber = Integer.valueOf(taskNum);

                if (taskNumber >= 1 && taskNumber <= counter) {
                    Task task = tasks[taskNumber - 1];
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.toString());
                } else {
                    System.out.println("Invalid task number. Please provide a valid task number.");
                }
                continue;
            }

            if (input.startsWith("todo")) {
                String taskDesc = input.substring(5);
                Task t = new Todo(taskDesc);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());

                tasks[counter] = t;
                counter++;

                System.out.println("Now you have " + counter + " tasks in the list.");

            }

            if (input.startsWith("deadline")) {
                String toSplit = input.substring(9);
                String[] parts = toSplit.split("/by");

                String taskDesc = parts[0].trim();
                String deadline = parts[1].trim();

                Task t = new Deadline(taskDesc, deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());

                tasks[counter] = t;
                counter++;

                System.out.println("Now you have " + counter + " tasks in the list.");
            }

            if (input.startsWith("event")) {
                String toSplit = input.substring(6);
                String[] parts = toSplit.split("/from");

                String taskDesc = parts[0].trim();
                String[] timeParts = parts[1].split("/to");
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                Task t = new Event(taskDesc, from, to);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());

                tasks[counter] = t;
                counter++;

                System.out.println("Now you have " + counter + " tasks in the list.");
            }


        }
    }
}



