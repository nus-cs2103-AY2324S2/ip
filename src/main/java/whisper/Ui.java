package whisper;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Ui class handles the user interface of the Whisper application, including input/output and displaying messages.
 */
public class Ui {
    private static final String LINE = "-------------------------------------------------\n";
    public static String NAME = "Whisper";
//    private String response;
    private StringBuilder responseBuilder;

    public Ui() {
        responseBuilder = new StringBuilder();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcomeMsg() {
//        printLine();
//        responseBuilder =  new StringBuilder("Hello! I'm " + NAME + " , your personal chat bot!\n" +
//                "What can I do for you?\n");
////        printLine();
//        return responseBuilder.toString();

        responseBuilder.append("Hello! I'm ").append(NAME).append(", your personal chat bot!\n");
        responseBuilder.append("What can I do for you?\n");
    }

    /**
     * Prints a line separator to the console.
     */
//    public String printLine() {
//        response = LINE;
//        return response;
//    }

    /**
     * Prints an error message to the console.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void printError(String errorMessage) {
//        responseBuilder = new StringBuilder("\nError: " + errorMessage);
//        return responseBuilder.toString();

        responseBuilder.append("\nError: ").append(errorMessage);
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user-entered command.
     */
    public String inputCommand() {
        return "Enter your input: ";
//        Scanner sc = new Scanner(System.in);
//        return sc.nextLine().trim();
    }

    /**
     * Prints an error message for failed file loading.
     */
    public void printLoadFileError() {
        //return printError("Error loading the file.");

        printError("Error loading the file.");
    }

    /**
     * Displays the list of tasks to the console.
     *
     * @param tasks The list of tasks to be displayed.
     */

//    public String printTasks(ArrayList<Task> tasks) {
////        printLine();
////        System.out.println("Here are your tasks:\n");
////        for (int i = 0; i < tasks.size(); i++) {
////            System.out.println((i + 1) + ". " + tasks.get(i));
////        }
//
//        StringBuilder message = new StringBuilder();
//        message.append(LINE).append("Here are your tasks:\n");
//        for (int i = 0; i < tasks.size(); i++) {
//            message.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
//        }
//        message.append(LINE);
//        response = message.toString();
//        return response;
////        printLine();
//    }

    public void printTasks(ArrayList<Task> tasks) {
//        responseBuilder = new StringBuilder(LINE).append("Here are your tasks:\n");
//        for (int i = 0; i < tasks.size(); i++) {
//            responseBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
//        }
//        responseBuilder.append(LINE);
//        return responseBuilder.toString();


        responseBuilder.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            responseBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
    }


    /**
     * Prints a message confirming the addition of a task.
     *
     * @param task       The task that was added.
     * @param totalTask The total number of tasks after the addition.
     */
    public void printTaskAdded(Task task, int totalTask) {
//        printLine();
        //responseBuilder = new StringBuilder("Got it. I've added this task:\n" + task
               // + "\nNow you have " + totalTask + " tasks in the list.");
//        printLine();
//        return responseBuilder.toString();

        responseBuilder.append("Got it. I've added this task:\n").append(task)
                .append("\nNow you have ").append(totalTask).append(" tasks in the list.\n");
    }

    /**
     * Prints a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskAsDone(Task task) {
//        printLine();
        //return "Nice! I've marked this task as done:\n" + task;
//        printLine();

        responseBuilder.append("Nice! I've marked this task as done:\n").append(task);
    }

    /**
     * Prints a message confirming the marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskAsUndone(Task task) {
//        printLine();
        //return "Nice! I've marked this task as not done:\n" + task;
//        printLine();

        responseBuilder.append("Nice! I've marked this task as not done:\n").append(task);
    }

    /**
     * Prints a message confirming the removal of a task.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after the removal.
     */
    public void printRemovedTask(Task task, int totalTasks) {
//        printLine();
        //return "Noted! I've removed this task: \n" + task + "\nNow you have " + totalTasks + " tasks in the list.";
//        printLine();

        responseBuilder.append("Noted! I've removed this task:\n").append(task)
                .append("\nNow you have ").append(totalTasks).append(" tasks in the list.\n");
    }

    /**
     * Prints tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
//        printLine();
//        if (matchingTasks.isEmpty()) {
//            System.out.println("No matching task found.\n");
//        } else {
//            System.out.println("Here are the matching tasks in your list! \n");
//            for (int i = 0; i < matchingTasks.size(); i++) {
//                System.out.println((i + 1) + ". " + matchingTasks.get(i));
//            }
//        }
//        printLine();

//        StringBuilder message = new StringBuilder();
//        message.append(LINE);
//        if (matchingTasks.isEmpty()) {
//            message.append("No matching task found.\n");
//        } else {
//            message.append("Here are the matching tasks in your list!\n");
//            for (int i = 0; i < matchingTasks.size(); i++) {
//                message.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
//            }
//        }
//        message.append(LINE);
//        return message.toString();

        if (matchingTasks.isEmpty()) {
            responseBuilder.append("No matching task found.\n");
        } else {
            responseBuilder.append("Here are the matching tasks in your list!\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                responseBuilder.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
    }

    public String getResponse() {
        String response = responseBuilder.toString();
        responseBuilder = new StringBuilder(); // Reset response builder for the next command
        return response;
    }

    /**
     * Displays the exit message when the application exits.
     */
    public void printExitMessage() {
//        printLine();
        //return "Bye. Hope to see you again soon!";
        responseBuilder.append("Bye. Hope to see you again soon!");
//        printLine();
    }
}