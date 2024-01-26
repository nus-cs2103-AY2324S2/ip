import java.util.Scanner;
public class HughJazz {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greeting();
        Scanner scn = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scn.nextLine().trim();

            if("bye".equalsIgnoreCase(userInput)) {
                break;
            } else if("list".equalsIgnoreCase(userInput)) {
                printTasks();
            } else{
                addTask(userInput);
                System.out.println("added: " + userInput);
            }
        }
        ending();
        scn.close();
    }

    public static void greeting() {
        System.out.println("Hello! I'm HughJazz");
        System.out.println("What can I do for you?");
    }
    public static void ending() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
        }
    }
    private static void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
