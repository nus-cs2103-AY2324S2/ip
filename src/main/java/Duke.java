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
                    String taskDesc = task.getDescription();
                    System.out.println(i + ". [" + taskStatus + "]"  + taskDesc);
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
                    System.out.println("[x] " + task.getDescription());
                } else {
                    System.out.println("Invalid task number. Please provide a valid task number.");
                }
                continue;
            }
            Task t = new Task(input);
            tasks[counter] = t;
            counter++;
        }
    }
}

