package damon.response;

import damon.exceptions.StorageFileLoadingException;
import damon.task.Task;
import damon.tasklist.TaskList;

public class Response {
    private String responseMessage = "hi";

    public Response() {
        //...
    }
    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        this.responseMessage = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
    }


    /**
     * Prints message of loading error.
     */
    public void showLoadingError() {
        this.responseMessage = new StorageFileLoadingException().getMessage();
    }

    /**
     * Prints message of adding a new Task to the current TaskList.
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
     * Prints message of deleting a Task of a specific index in the current TaskList.
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
     * Prints exiting message.
     */
    public void showExitCommand() {
        this.responseMessage = "Bye. Hope to see you again soon!";
    }

    /**
     * Prints message of marking a Task of a specific index in the current TaskList
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
     * Prints message of un-marking a Task of a specific index in the current TaskList
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
     * Prints the whole TaskList
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
     * Prints message of the threw error.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        this.responseMessage = errorMessage;
    }

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
     * Prints echo.
     *
     * @param inputString User's input String which is to be echoed.
     */
    public void showEchoCommand(String inputString) {
        this.responseMessage = inputString;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public String getWelcomeMessage() {
        return "Hello from\n" + " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
    }

}
