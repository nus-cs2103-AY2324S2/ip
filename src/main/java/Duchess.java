import java.util.Scanner;
import java.util.ArrayList;

public class Duchess {
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    // Declare the scanner as a static field in the class
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printHorizontalLine();
        printOpeningGreeting();
        printHorizontalLine();

        try {
            printEcho();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        } finally {
            //Close scanner
            scanner.close();
        }

        printHorizontalLine();
    }

    //Add a ToDo to the task list
    private static void addToDo(String userInput) throws DuchessException {
        String[] toDoTokens = userInput.split("todo"); //Split to find description
        if (toDoTokens.length > 1) {
            String description = toDoTokens[1].trim(); //Trim to only keep description
            ToDo newToDo = new ToDo(description);
            addTask(newToDo);
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: todo <description>");
        }
    }

    //Add a Deadline to the taskList
    private static void addDeadline(String userInput) throws DuchessException {
        String[] deadlineTokens = userInput.split("deadline");

        if (deadlineTokens.length > 1) {
            // Split further to extract description and deadline details
            String[] details = deadlineTokens[1].trim().split("/by");

            if (details.length > 1) {
                String description = details[0].trim();
                String by = details[1].trim(); // by is everything after
                Deadline newDeadline = new Deadline(description, by);
                addTask(newDeadline);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
        }
    }

    //Add an Event to the task list
    private static void addEvent(String userInput) throws DuchessException {
        String[] eventTokens = userInput.split("event");

        if (eventTokens.length > 1) {
            // Split further to extract description and event details
            String[] details = eventTokens[1].trim().split("/from|/to"); // Means can use either /from or /to as delimiter

            if (details.length > 2) {
                String description = details[0].trim();
                String from = details[1].trim(); // from is everything after
                String to = details[2].trim();   // to is everything after

                Event newEvent = new Event(description, from, to);
                addTask(newEvent);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
        }
    }

    private static void printTaskList() {
        printHorizontalLine();
        if (taskCount == 0) {
            System.out.println(" No tasks have been added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        printHorizontalLine();
    }

    //Add a task to task list
    private static void addTask(Task task) throws DuchessException {
        if (taskCount < MAX_TASKS) {
            tasks.add(task);
            taskCount++;

            printHorizontalLine();
            System.out.println(" Understood. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            printHorizontalLine();
        } else {
            throw new DuchessException("The task list is full. I cannot add more tasks.");
        }
    }

    private static void deleteTask(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            Task deletedTask = tasks.remove(taskIndex);
            taskCount--;

            printHorizontalLine();
            System.out.println(" Understood. I've deleted this task:");
            System.out.println(deletedTask.toString());
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Mark a task as done
    private static void markTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
            printHorizontalLine();
            System.out.println(" Perfect! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex).toString());
            printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Unmark a task as done
    private static void unmarkTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).unmarkAsDone();
            printHorizontalLine();
            System.out.println(" Understood, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex).toString());
            printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Check if the task index is valid
    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < taskCount;
    }



    //Adds user input to list, exits if user inputs "bye"
    private static void printEcho() throws DuchessException {
        // Loop to read user input
        while (true) {
            String userInput = scanner.nextLine();

            // Split user input into tokens
            String[] tokens = userInput.split(" ");

            // Based on user input, change output
            switch (tokens[0].toLowerCase()) {
                case "bye":
                    printClosingGreeting();
                    return;

                case "list":
                    printTaskList();
                    break;

                case "mark":
                    if (tokens.length > 1) {
                        int taskIndexToMark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        markTaskAsDone(taskIndexToMark);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: mark <taskIndex>");
                    }
                    break;

                case "unmark":
                    if (tokens.length > 1) {
                        int taskIndexToUnmark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        unmarkTaskAsDone(taskIndexToUnmark);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                case "todo":
                    addToDo(userInput);
                    break;

                case "deadline":
                    addDeadline(userInput);
                    break;

                case "event":
                    addEvent(userInput);
                    break;

                case "delete":
                    if (tokens.length > 1) {
                        int taskIndexToDelete = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        deleteTask(taskIndexToDelete);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                default:
                    throw new DuchessException("Oh dear, I can't make out what that is.");
            }
        }
    }

    //Print opening greeting
    private static void printOpeningGreeting() {
        String logo = " ____            __\n"
                + "|  _ \\ _   ______| |      ___  ___  ___\n"
                + "| | | | | | |  __| |__  /  _ \\/ __|/ __|\n"
                + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\\n"
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
