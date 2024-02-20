package blu.ui;

import static blu.utils.Messages.MESSAGE_EXIT;
import static blu.utils.Messages.MESSAGE_GREET;
import static blu.utils.Messages.MESSAGE_STORAGE_PATH;

import java.util.ArrayList;
import java.util.List;

import blu.task.Task;
import blu.task.TaskList;

/**
 * Handles all user input/output operations for the Blu application.
 */
public class UI {
    private String formatOutput(String... message) {
        return String.join("\n", message);
    }

    /**
     * Displays the welcome message and the storage file path.
     *
     * @param storageFilePath The file path of the storage used.
     */
    public String getWelcomeMessage(String storageFilePath) {
        String storagePathString = String.format(MESSAGE_STORAGE_PATH, storageFilePath);
        String[] messages = {storagePathString, MESSAGE_GREET};
        return formatOutput(messages);
    }

    /**
     * Displays the tasks in the task list to the user.
     *
     * @param taskList The TaskList to be displayed.
     */
    public String showTaskList(TaskList taskList) {
        return formatOutput(taskList.toString());
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param taskList The TaskList after adding the new task.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        String[] messages = {
            "Added task to the list:",
            task.toString(),
            "You have " + taskList.getNumberOfTasks() + " tasks currently."
        };
        return formatOutput(messages);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskList The TaskList after deleting the task.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        String[] messages = {
            "Deleted task from list:",
            task.toString(),
            "You have " + taskList.getNumberOfTasks() + " tasks currently"
        };
        return formatOutput(messages);
    }

    /**
     * Displays a message indicating that a task has been marked successfully.
     *
     * @param task The task that was marked.
     * @param taskIdx The index of the marked task.
     */
    public String showTaskMarked(Task task, int taskIdx) {
        String[] messages = {"Marked task as done:", task.toString()};
        return formatOutput(messages);
    }

    /**
     * Displays a message indicating that a task is already marked.
     *
     * @param taskIdx The index of the marked task.
     */
    public String showTaskAlreadyMarked(int taskIdx) {
        return formatOutput("Task number " + taskIdx + " is already marked as done");
    }

    /**
     * Displays a message indicating that a task is already unmarked.
     *
     * @param taskIdx The index of the unmarked task.
     */
    public String showTaskAlreadyUnmarked(int taskIdx) {
        return formatOutput("Task number " + taskIdx + " is already unmarked as not done");
    }

    /**
     * Displays a message indicating that a task is already unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showTaskUnmarked(Task task) {
        String[] messages = {"Unmarked task as not done:", task.toString()};
        return formatOutput(messages);
    }

    /**
     * Displays tasks containing search string specified by the user.
     *
     * @param tasks The tasks that contain search string inside the task list.
     * @param searchString The search string specified by the user.
     */
    public String showTasksFound(List<Task> tasks, String searchString) {
        if (tasks.isEmpty()) {
            return formatOutput("No tasks found containing " + searchString);
        }
        List<String> messagesList = new ArrayList<>();
        messagesList.add("There are " + tasks.size() + " tasks that contains " + searchString);
        int count = 1;
        for (Task task : tasks) {
            messagesList.add(count + ". " + task.toString());
            count++;
        }
        String[] messages = new String[messagesList.size()];
        messages = messagesList.toArray(messages);
        return formatOutput(messages);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMsg The error message to be displayed.
     */
    public String showErrorMessage(String errorMsg) {
        return formatOutput(errorMsg);
    }

    /**
     * Displays the exit message to the user.
     */
    public String showExitMessage() {
        return formatOutput(MESSAGE_EXIT);
    }
}
