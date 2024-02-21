package osiris.ui;

import java.util.ArrayList;

/**
 * The Ui class provides methods for managing user interface interactions and displaying messages.
 */
public class Ui {

    /**
     * Outputs introduction messages to the user.
     *
     * @return The introduction messages as a single string.
     */
    public String displayIntroductions() {
        return "Hello, I'm Osiris.\nHow may I assist you today?";
    }

    /**
     * Outputs a message for unsupported commands.
     *
     * @return The message for unsupported commands as a single string.
     */
    public String displayUnsupportedCommandMessage() {
        return UiOutputs.UNSUPPORTED_COMMAND_OUTPUT;
    }

    /**
     * Outputs goodbye messages to the user.
     *
     * @return The goodbye messages as a single string.
     */
    public String displayGoodbyes() {
        return UiOutputs.GOODBYES;
    }

    /**
     * Displays a success notification for adding a to-do task.
     *
     * @param taskDetails The details of the added task.
     * @param taskCount   The total count of tasks.
     * @return The success notification for adding a to-do task as a single string.
     */
    public String displayToDoTaskAdditionNotification(String taskDetails, int taskCount) {
        String confirmationMessage = String.format("Task Added:%n");
        confirmationMessage += String.format("    %s%n", taskDetails);
        confirmationMessage += String.format("Curren Tack Count: %d%n", taskCount);
        return confirmationMessage;
    }

    /**
     * Displays a success notification for adding a deadline task.
     *
     * @param taskDetails The details of the added task.
     * @param taskCount   The total count of tasks.
     * @return The success notification for adding a deadline task as a single string.
     */
    public String displayDeadlineTaskAdditionNotification(String taskDetails, int taskCount) {
        String confirmationMessage = String.format("Task Added:%n");
        confirmationMessage += String.format("    %s%n", taskDetails);
        confirmationMessage += String.format("Curren Tack Count: %d%n", taskCount);
        return confirmationMessage;
    }

    /**
     * Displays a success notification for adding an event task.
     *
     * @param taskDetails The details of the added task.
     * @param taskCount   The total count of tasks.
     * @return The success notification for adding an event task as a single string.
     */
    public String displayEventTaskAdditionNotification(String taskDetails, int taskCount) {
        String confirmationMessage = String.format("Task Added:%n");
        confirmationMessage += String.format("    %s%n", taskDetails);
        confirmationMessage += String.format("Curren Tack Count: %d%n", taskCount);
        return confirmationMessage;
    }


    /**
     * Displays a success notification for marking a task as completed.
     *
     * @param taskDetails The details of the completed task.
     * @return The success notification for marking a task as completed as a single string.
     */
    public String displayMarkTaskCompleteNotification(String taskDetails) {
        return String.format("Marked Task:%n    %s", taskDetails);

    }


    /**
     * Displays a success notification for marking a task as incomplete.
     *
     * @param taskDetails The details of the incomplete task.
     * @return The success notification for marking a task as incomplete as a single string.
     */
    public String displayMarkTaskIncompleteNotification(String taskDetails) {
        return String.format("Unmarked Task:%n    %s", taskDetails);
    }

    /**
     * Displays a success notification for removing a task.
     *
     * @param taskDetails The details of the removed task.
     * @param taskCount   The total count of tasks.
     * @return The success notification for removing a task as a single string.
     */
    public String displayDeleteTaskNotification(String taskDetails, int taskCount) {
        String confirmationMessage = "Removed Task:\n";
        confirmationMessage += "    " + taskDetails + "\n";
        confirmationMessage += String.format("Curren Tack Count: %d%n", taskCount);
        return confirmationMessage;
    }


    /**
     * Prints the list of user tasks.
     *
     * @param taskDetailsList The list containing details of user tasks.
     * @return The list of user tasks as a single string.
     */
    public String printUserTasks(ArrayList<String> taskDetailsList) {
        StringBuilder taskList = new StringBuilder("Pending Tasks:\n");
        for (int i = 0; i < taskDetailsList.size(); i++) {
            taskList.append("    ").append(i + 1).append(". ").append(taskDetailsList.get(i)).append("\n");
        }
        return taskList.toString();
    }

    /**
     * Prints the details of the found tasks to the user interface.
     *
     * @param taskDetailsList The list of task details to be printed.
     * @return The details of the found tasks as a single string.
     */
    public String printFoundUserTasks(ArrayList<String> taskDetailsList) {
        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskDetailsList.size(); i++) {
            matchingTasks.append("    ").append(i + 1).append(". ").append(taskDetailsList.get(i)).append("\n");
        }
        return matchingTasks.toString();
    }

}
