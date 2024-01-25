import java.util.Scanner;

public class Dav {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greetUser();
        startChat();
        exit();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Dav");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = getUserInput(scanner);
            processUserInput(userInput);

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    public static String getUserInput(Scanner scanner) {
        System.out.print("    ");
        return scanner.nextLine();
    }

    public static void processUserInput(String input) {
        System.out.println("____________________________________________________________");

        if (input.equalsIgnoreCase("list")) {
            listTasks();
        } else if (input.startsWith("mark ")) {
            int taskIndex = Integer.parseInt(input.substring(5).trim());
            markTaskDone(taskIndex);
        } else if (input.startsWith("unmark ")) {
            int taskIndex = Integer.parseInt(input.substring(7).trim());
            unmarkTaskDone(taskIndex);
        } else if (!input.equalsIgnoreCase("bye")) {
            addTask(input);
        }

        System.out.println("____________________________________________________________");
    }

    public static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = new Task(taskDescription);
            System.out.println(" added: " + taskDescription);
        } else {
            System.out.println(" Sorry, task list is full. Cannot add more tasks.");
        }
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks[i]);
            }
        }
    }

    public static void markTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks[taskIndex - 1].markAsDone();
            System.out.println(" I've marked this task as done:");
            System.out.println("   " + tasks[taskIndex - 1]);
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static void unmarkTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks[taskIndex - 1].unmarkAsDone();
            System.out.println(" I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskIndex - 1]);
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static boolean isValidIndex(int index) {
        return index >= 1 && index <= taskCount;
    }

    public static void exit() {
        System.out.println(" Goodbye.");
        System.out.println("____________________________________________________________");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with [X]
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}



