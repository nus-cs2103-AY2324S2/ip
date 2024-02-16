//package duke;
//
//import java.util.List;
//
///**
// * Represents the user interface of the Duke chatbot.
// */
//public class Ui {
//    private StringBuilder responseBuilder = new StringBuilder();
//
//    public void showMessage(String message) {
//        responseBuilder.append(message).append("\n");
//    }
//    /**
//     * Displays a welcome message.
//     */
//    public void showWelcome() {
//        System.out.println("Hello! I'm Aether!");
//        System.out.println("What can I do for you?");
//
//    }
//    /**
//     * Displays an error message for failed task loading.
//     */
//    public void showLoadingError() {
//        System.out.println("Error loading tasks from file.");
//    }
//
//    /**
//     * Displays a separator line.
//     */
//    public void showSeparator() {
//        System.out.println("_____________________________");
//    }
//
//    /**
//     * Displays an error message.
//     *
//     * @param errorMessage The error message to display.
//     */
//    public void showErrorMessage(String errorMessage) {
//        System.out.println("OOPS!!! " + errorMessage);
//        System.out.println("_____________________________");
//    }
//
//    /**
//     * Displays the list of tasks.
//     *
//     * @param taskList The task list to display.
//     */
//    public void showTaskList(TaskList taskList) {
//        System.out.println("Here are the tasks in your list:");
//        List<Task> tasks = taskList.getTasks();
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + "." + tasks.get(i));
//        }
//        System.out.println("_____________________________");
//    }
//
//
//    /**
//     * Displays a message indicating a task has been added.
//     *
//     * @param task       The added task.
//     * @param taskCount  The total number of tasks after adding.
//     */
//    public void showTaskAdded(Task task, int taskCount) {
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  " + task);
//        System.out.println("Now you have " + taskCount + " tasks in the list.");
//        System.out.println("_____________________________");
//    }
//
//    /**
//     * Displays a message indicating a task has been deleted.
//     *
//     * @param deletedTask      The deleted task.
//     * @param remainingTasks   The remaining number of tasks after deletion.
//     */
//    public void showTaskDeleted(Task deletedTask, int remainingTasks) {
//        System.out.println("Noted. I've removed this task:");
//        System.out.println("  " + deletedTask);
//        System.out.println("Now you have " + remainingTasks + " tasks in the list.");
//        System.out.println("_____________________________");
//    }
//
//    /**
//     * Displays a message for an invalid task index.
//     */
//    public void showInvalidTaskIndex() {
//        System.out.println("Invalid task index. Please provide a valid task index.");
//    }
//
//    /**
//     * Displays a message indicating a task has been marked as done.
//     *
//     * @param markedTask The marked task.
//     */
//    public void showTaskMarked(Task markedTask) {
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println("  " + markedTask);
//        System.out.println("_____________________________");
//    }
//
//    /**
//     * Displays a message indicating a task has been marked as not done.
//     *
//     * @param unmarkedTask The unmarked task.
//     */
//    public void showTaskUnmarked(Task unmarkedTask) {
//        System.out.println("OK, I've marked this task as not done yet:");
//        System.out.println("  " + unmarkedTask);
//        System.out.println("_____________________________");
//    }
//
//    public void showMatchingTasks(List<Task> matchingTasks) {
//        if (matchingTasks.isEmpty()) {
//            System.out.println("No matching tasks found.");
//        } else {
//            System.out.println("Here are the matching tasks in your list:");
//            for (int i = 0; i < matchingTasks.size(); i++) {
//                System.out.println((i + 1) + "." + matchingTasks.get(i));
//            }
//        }
//        System.out.println("_____________________________");
//    }
//
//    /**
//     * Displays a goodbye message.
//     */
//    public void showGoodbye() {
//        System.out.println("Goodbye. Hope to see you again soon!");
//        System.out.println("_____________________________");
//    }
//
//    public String getResponseAndClear() {
//        String response = responseBuilder.toString();
//        responseBuilder.setLength(0); // Clear the builder after retrieving the response
//        return response;
//    }
//
//}
package duke;

import java.util.List;

/**
 * Represents the user interface of the Aether chatbot.
 */
public class Ui {
    private StringBuilder responseBuilder = new StringBuilder();

    // Use this method to add messages to the responseBuilder with a newline
    private void addMessage(String message) {
        responseBuilder.append(message).append("\n");
    }

    /**
     * Displays a welcome message.
     */
    public String showWelcome() {
        String welcomeMessage = "Hello! I'm Aether!\n";
        welcomeMessage += "What can I do for you?";
        return welcomeMessage;
    }
    /**
     * Displays an error message for failed task loading.
     */
    public void showLoadingError() {
        addMessage("Error loading tasks from file.");
    }

    /**
     * Displays a separator line.
     */
    public void showSeparator() {
        addMessage("_____________________________");
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public String showErrorMessage(String errorMessage) {
//        addMessage("OOPS!!! " + errorMessage);
//        showSeparator();
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append("OOPS!!! ").append(errorMessage).append("\n");
        showSeparator();
        return errorMessageBuilder.toString();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list to display.
     */
    public String showTaskList(List<Task> tasks) {
        StringBuilder tasksMessage = new StringBuilder();
        tasksMessage.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksMessage.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        showSeparator();
        return tasksMessage.toString();
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task       The added task.
     * @param taskCount  The total number of tasks after adding.
     */
    public String showTaskAdded(Task task, int taskCount) {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n")
                .append("  ").append(task).append("\n")
                .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
        showSeparator();
        return message.toString();
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param deletedTask      The deleted task.
     * @param remainingTasks   The remaining number of tasks after deletion.
     */
    public String showTaskDeleted(Task deletedTask, int remainingTasks) {
        StringBuilder message = new StringBuilder();
        message.append("Noted. I've removed this task:\n")
                .append("  ").append(deletedTask).append("\n")
                .append("Now you have ").append(remainingTasks).append(" tasks in the list.");
        showSeparator();
        return message.toString();
    }
    /**
     * Displays a message for an invalid task index.
     */
    public void showInvalidTaskIndex() {
        addMessage("Invalid task index. Please provide a valid task index.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param markedTask The marked task.
     */
    public String showTaskMarked(Task markedTask) {
        StringBuilder message = new StringBuilder();
        message.append("Nice! I've marked this task as done:\n")
                .append("  ").append(markedTask);
        showSeparator();
        return message.toString();
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param unmarkedTask The unmarked task.
     */
    public String showTaskUnmarked(Task unmarkedTask) {
        StringBuilder message = new StringBuilder();
        message.append("OK, I've marked this task as not done yet:\n")
                .append("  ").append(unmarkedTask).append("\n");
        showSeparator();
        return message.toString();
    }

    /**
     * Displays matching tasks or a message if none are found.
     *
     * Iterates over a list of tasks, appending each to a message if the list is not empty.
     * If the list is empty, a message indicating no matches is displayed. Always ends by
     * adding a separator line.
     *
     * @param matchingTasks List of tasks that match a certain criterion.
     */
    public String showMatchingTasks(List<Task> matchingTasks) {
        StringBuilder responseBuilder = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            responseBuilder.append("No matching tasks found.");
        } else {
            responseBuilder.append("Here are the matching tasks in your list:\n");
            for (Task task : matchingTasks) {
                responseBuilder.append(task).append("\n");
            }
        }
        showSeparator();
        return responseBuilder.toString();
    }

    /**
     * Displays a goodbye message.
     */
    public String showGoodbye() {
        StringBuilder goodbyeMessage = new StringBuilder();
        addMessage("Goodbye. Hope to see you again soon!");
        showSeparator();
        goodbyeMessage.append("Goodbye. Hope to see you again soon!\n");
        return goodbyeMessage.toString();
    }
    /**
     * Displays a message indicating that a duplicate task was found.
     *
     * @param duplicateTask The duplicate task.
     * @return The message indicating the duplicate task.
     */
    public String showDuplicateTask(Task duplicateTask) {
        StringBuilder message = new StringBuilder();
        message.append("Duplicate task found:\n")
                .append("  ").append(duplicateTask).append("\n")
                .append("This task was not added to the list.");
        showSeparator();
        return message.toString();
    }

    public String getResponseAndClear() {
        String response = responseBuilder.toString();
        responseBuilder.setLength(0); // Clear the builder after retrieving the response
        return response;
    }
}
