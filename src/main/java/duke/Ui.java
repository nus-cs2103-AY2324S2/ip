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
     * Generates an error message with the provided error message content.
     *
     * @param errorMessage the content of the error message.
     * @return a String representing the formatted error message.
     */
    public String showErrorMessage(String errorMessage) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append("OOPS WATCH OUT!!! ").append(errorMessage).append("\n");
        showSeparator();
        return errorMessageBuilder.toString();
    }

    /**
     * Generates a message displaying the tasks in the provided list.
     * This method formats the tasks list with their respective indices and adds a separator afterward.
     *
     * @param tasks the list of tasks to display.
     * @return a String representing the formatted message displaying the tasks in the list.
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
     * Generates a message indicating that a task has been added and displays the updated task count.
     * This method formats the added task with a prefix and adds a separator afterward.
     *
     * @param task      the task that has been added.
     * @param taskCount the total number of tasks after adding the new task.
     * @return a String representing the message indicating the added task and the updated task count.
     */
    public String showTaskAdded(Task task, int taskCount) {
        StringBuilder message = new StringBuilder();
        message.append("Gotchu. I've added this task:\n")
                .append("  ").append(task).append("\n")
                .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
        showSeparator();
        return message.toString();
    }

    /**
     * Generates a message indicating that a task has been deleted and displays the number of remaining tasks.
     * This method formats the deleted task with a prefix and adds a separator afterward.
     *
     * @param deletedTask    the task that has been deleted.
     * @param remainingTasks the number of tasks remaining in the list after deletion.
     * @return a String representing the message indicating the deleted task and the number of remaining tasks.
     */
    public String showTaskDeleted(Task deletedTask, int remainingTasks) {
        StringBuilder message = new StringBuilder();
        message.append("Noted with thanks. I've removed this task:\n")
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
     * Generates a message indicating that a task has been marked as done.
     * This method formats the marked task with a prefix and adds a separator afterward.
     *
     * @param markedTask the task that has been marked as done.
     * @return a String representing the message indicating the marked task.
     */
    public String showTaskMarked(Task markedTask) {
        StringBuilder message = new StringBuilder();
        message.append("Nice! I've marked this task as done:\n")
                .append("  ").append(markedTask);
        showSeparator();
        return message.toString();
    }

    /**
     * Generates a message indicating that a task has been marked as not done.
     * This method formats the unmarked task with a prefix and adds a separator afterward.
     *
     * @param unmarkedTask the task that has been marked as not done.
     * @return a String representing the message indicating the unmarked task.
     */
    public String showTaskUnmarked(Task unmarkedTask) {
        StringBuilder message = new StringBuilder();
        message.append("Better do soon, I've marked this task as not done yet:\n")
                .append("  ").append(unmarkedTask).append("\n");
        showSeparator();
        return message.toString();
    }

    /**
     * Generates a message displaying the matching tasks from the provided list.
     * This method formats the matching tasks with appropriate messages and adds a separator afterward.
     *
     * @param matchingTasks the list of matching tasks to display.
     * @return a String representing the message indicating the matching tasks.
     */
    public String showMatchingTasks(List<Task> matchingTasks) {
        StringBuilder responseBuilder = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            responseBuilder.append("No matching tasks found oops.");
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
     * Generates a goodbye message.
     * This method adds a goodbye message and a separator, then returns the formatted message.
     *
     * @return a String representing the goodbye message.
     */
    public String showGoodbye() {
        StringBuilder goodbyeMessage = new StringBuilder();
        addMessage("Toodles! Come to chat again soon :)");
        showSeparator();
        goodbyeMessage.append("Toodles! Come to chat again soon :)\n");
        return goodbyeMessage.toString();
    }
    /**
     * Generates a message indicating that a duplicate task was found and not added to the list.
     * This method formats the duplicate task with appropriate messages and adds a separator afterward.
     *
     * @param duplicateTask the duplicate task found.
     * @return a String representing the message indicating the duplicate task.
     */
    public String showDuplicateTask(Task duplicateTask) {
        StringBuilder message = new StringBuilder();
        message.append("Ohno, you've already added this before!:\n")
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
