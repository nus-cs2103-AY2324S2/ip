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

public class Ui {
    private StringBuilder responseBuilder = new StringBuilder();

    // Use this method to add messages to the responseBuilder with a newline
    private void addMessage(String message) {
        responseBuilder.append(message).append("\n");
    }

    public void showWelcome() {
        addMessage("Hello! I'm Aether!");
        addMessage("What can I do for you?");
    }

    public void showLoadingError() {
        addMessage("Error loading tasks from file.");
    }

    public void showSeparator() {
        addMessage("_____________________________");
    }

    public void showErrorMessage(String errorMessage) {
        addMessage("OOPS!!! " + errorMessage);
        addMessage("_____________________________");
    }

    public void showTaskList(TaskList taskList) {
        StringBuilder tasksMessage = new StringBuilder();
        tasksMessage.append("Here are the tasks in your list:\n");
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            tasksMessage.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        tasksMessage.append("_____________________________");
        addMessage(tasksMessage.toString());
    }

    public void showTaskAdded(Task task, int taskCount) {
        addMessage("Got it. I've added this task:");
        addMessage("  " + task);
        addMessage("Now you have " + taskCount + " tasks in the list.");
        addMessage("_____________________________");
    }

    public void showTaskDeleted(Task deletedTask, int remainingTasks) {
        addMessage("Noted. I've removed this task:");
        addMessage("  " + deletedTask);
        addMessage("Now you have " + remainingTasks + " tasks in the list.");
        addMessage("_____________________________");
    }

    public void showInvalidTaskIndex() {
        addMessage("Invalid task index. Please provide a valid task index.");
    }

    public void showTaskMarked(Task markedTask) {
        addMessage("Nice! I've marked this task as done:");
        addMessage("  " + markedTask);
        addMessage("_____________________________");
    }

    public void showTaskUnmarked(Task unmarkedTask) {
        addMessage("OK, I've marked this task as not done yet:");
        addMessage("  " + unmarkedTask);
        addMessage("_____________________________");
    }

    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            addMessage("No matching tasks found.");
        } else {
            StringBuilder matchingTasksMessage = new StringBuilder();
            matchingTasksMessage.append("Here are the matching tasks in your list:\n");
            for (Task task : matchingTasks) {
                matchingTasksMessage.append(task).append("\n");
            }
            addMessage(matchingTasksMessage.toString());
        }
        addMessage("_____________________________");
    }

    public void showGoodbye() {
        addMessage("Goodbye. Hope to see you again soon!");
        addMessage("_____________________________");
    }

    public String getResponseAndClear() {
        String response = responseBuilder.toString();
        responseBuilder.setLength(0); // Clear the builder after retrieving the response
        return response;
    }
}
