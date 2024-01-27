import java.util.Scanner;

public class Ragdoll{
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
                + "| $$__  $$                          | $$          | $$| $$\n"
                + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
                + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
                + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
                + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
                + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
                + "                     /$$  \\ $$                            \n"
                + "                    |  $$$$$$/                            \n"
                + "                     \\______/                             \n";
        System.out.println("Hello! I am\n" + "\n" + logo);

        System.out.println("How can I assist you today?");

        System.out.println("____________________________________________________________");

        while(true) {
            String input = scanner.nextLine().trim();
            System.out.println("___________________________________list_________________________");

            if ("bye".equalsIgnoreCase(input)) {
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                listTasks();
            } else {
                addTask(input);
            }

            System.out.println("____________________________________________________________");

        }

        scanner.close();
        System.out.println("See ya!");
        System.out.println("____________________________________________________________");
    }

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("added: " + task);
        } else {
            System.out.println("Task list is full!");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("There is no task yet!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}
