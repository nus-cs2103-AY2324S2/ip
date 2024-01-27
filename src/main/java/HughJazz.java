import java.util.Scanner;
public class HughJazz {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
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
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput, true);
            } else if (userInput.startsWith("unmark ")) {
                markTask(userInput, false);
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
    private static void addTask(String taskDesc) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDesc);
            taskCount++;
        }
    }
    private static void markTask(String userInput, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                if (isDone) {
                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                }
                else {
                    tasks[taskNumber].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println("  " + tasks[taskNumber].toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number");
        }
    }
    private static void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
    }
}

