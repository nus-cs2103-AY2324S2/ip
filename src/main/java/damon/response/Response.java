package damon.response;

import damon.exceptions.StorageFileLoadingException;
import damon.task.Task;
import damon.tasklist.TaskList;

/**
 * Creates a Response object to generate welcome message
 * and other corresponding message to each user's input.
 */
public class Response {
    private String responseMessage;

    public Response() {
        //...
    }

    /**
     * Shows message of loading error.
     */
    public void showLoadingError() {
        this.responseMessage = new StorageFileLoadingException().getMessage();
    }

    /**
     * Shows message of adding a new Task to the current TaskList.
     *
     * @param newTask New Task to be added.
     * @param tasks Current TaskList.
     */
    public void showAddCommand(Task newTask, TaskList tasks) {
        this.responseMessage = "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Shows message of deleting a Task of a specific index in the current TaskList.
     *
     * @param index Index of the Task which is to be deleted in the current TaskList.
     * @param tasks Current TaskList.
     */
    public void showDeleteCommand(int index, TaskList tasks) {
        this.responseMessage = "Noted. I've removed this task:\n"
                + tasks.getTask(index).toString() + "\n"
                + "Now you have " + (tasks.getSize() - 1) + " tasks in the list";
    }

    /**
     * Shows exiting message.
     */
    public void showExitCommand() {
        this.responseMessage = "Bye. Hope to see you again soon!";
    }

    /**
     * Shows message of marking a Task of a specific index in the current TaskList
     * as done status.
     *
     * @param index Index of the Task to be marked
     *              as done status in the current TaskList.
     * @param tasks Current TaskList.
     */
    public void showMarkCommand(TaskList tasks, int index) {
        this.responseMessage = "Nice! I've marked this task as done:\n"
                + tasks.getTask(index).toString();
    }

    /**
     * Shows message of un-marking a Task of a specific index in the current TaskList
     * to not done status.
     *
     * @param index Index of the Task to be un-marked
     *              to not done status in the current TaskList.
     * @param tasks Current TaskList.
     */
    public void showUnMarkCommand(TaskList tasks, int index) {
        this.responseMessage = "OK, I've marked this task as not done yet:\n"
                + tasks.getTask(index).toString();
    }

    /**
     * Shows the whole TaskList
     *
     * @param tasks Current TaskList (the TaskList to be printed).
     */
    public void showShowTaskListCommand(TaskList tasks) {
        StringBuilder newResponseMessage = new StringBuilder();
        int n = tasks.getSize();
        for (int i = 0; i < n; i++) {
            Task currentTask = tasks.getTask(i);
            newResponseMessage.append(i + 1).append(".").append(currentTask.toString()).append("\n");
        }
        this.responseMessage = newResponseMessage.toString();
    }

    /**
     * Shows message of the threw error.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        this.responseMessage = errorMessage;
    }

    /**
     * Shows message of finding target Tasks.
     *
     * @param tasks Current TaskList which to be found target Tasks in.
     */
    public void showFindCommand(TaskList tasks) {
        StringBuilder newResponseMessage = new StringBuilder();
        int n = tasks.getSize();
        for (int i = 0; i < n; i++) {
            Task currentTask = tasks.getTask(i);
            newResponseMessage.append(i + 1).append(".").append(currentTask.toString()).append("\n");
        }
        this.responseMessage = newResponseMessage.toString();
    }

    /**
     * Shows echo.
     *
     * @param inputString User's input String which is to be echoed.
     */
    public void showEchoCommand(String inputString) {
        this.responseMessage = inputString;
    }

    /**
     * Returns Response object's response message.
     *
     * @return Response's responseMessage.
     */
    public String getResponseMessage() {
        return this.responseMessage;
    }

    /**
     * Shows welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello from\n" + " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
    }
}
