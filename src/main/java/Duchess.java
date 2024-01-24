import java.util.Scanner;

public class Duchess {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printHorizontalLine();

        printOpeningGreeting();

        printHorizontalLine();

        printEcho();

        printHorizontalLine();

    }

    private static void printTaskList() {
        printHorizontalLine();
        if (taskCount == 0) {
            System.out.println(" No tasks added yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i].description);
            }
        }
        printHorizontalLine();
    }

    //Add a task to task list
    private static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            printHorizontalLine();
            System.out.println(" added: " + task.description);
            printHorizontalLine();
        } else {
            System.out.println("Task list is full. Cannot add more tasks.");
        }
    }

    //Adds user input to list, exits if user inputs "bye"
    private static void printEcho() {
        Scanner scanner = new Scanner(System.in);

        // Loop to read user input
        while (true) {
            String userInput = scanner.nextLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                printClosingGreeting();
                break;  // Exit the loop
            } else if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            } else {
                Task newTask = new Task(userInput);
                addTask(newTask);
            }
        }

        // Close the scanner
        scanner.close();
    }

    //Print opening greeting
    private static void printOpeningGreeting() {
        String logo = " ____            __     \n"
                + "|  _ \\ _   ______| |      ___  ___  ___ \n"
                + "| | | | | | |  __| |__  /  _ \\/ __|/ __|  \n"
                + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\  \n"
                + "|____/ \\__,_|____|_| |_|\\ ___||___/|___/\n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duchess.");
        System.out.println("What can I do for you today?");
    }


    //Prints closing greeting
    private static void printClosingGreeting() {
        printHorizontalLine();
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    //Prints a Horizontal Line of 50 dashes
    private static void printHorizontalLine() {
        int lineLength = 50; // Specify the length of the line

        // Print the horizontal line
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }

        System.out.println();
    }
}
