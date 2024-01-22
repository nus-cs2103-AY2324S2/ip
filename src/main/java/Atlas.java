import java.util.Scanner;

public class Atlas {
    private static final int MAX_TASKS = 100;
    private final String horizontalLine = "____________________________________________________________";
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCounter = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                listTasks();
            }
            else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                markTask(taskNumber);
            }
            else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1; // Adjust for array index
                unmarkTask(taskNumber);
            }
            else {
                addTask(input);
            }

        }
        exit();

    }

    private static void greet() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(String task) {
        tasks[taskCounter] = new Task(task);
        taskCounter++;
        System.out.println(" added: " + task);
    }

    private static void listTasks() {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    private static void markTask(int i) {
        tasks[i].toggle();
        System.out.println("Nice! I've marked this task as done:");
        String str = String.format("[%s] %s", tasks[i].getStatusIcon(), tasks[i].getDescription());
        System.out.println(str);
    }

    private static void unmarkTask(int i) {
        tasks[i].toggle();
        System.out.println("OK, I've marked this task as not done yet");
        String str = String.format("[%s] %s", tasks[i].getStatusIcon(), tasks[i].getDescription());
        System.out.println(str);
    }

}
