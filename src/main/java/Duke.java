import java.util.Scanner;
public class Duke {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(input)) {
                listTasks();
            } else {
                addTask(input);
            }
        }

        scanner.close();
    }
    private static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task + "\n");
    }

    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("");
    }
}
