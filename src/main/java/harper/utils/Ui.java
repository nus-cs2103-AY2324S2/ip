package harper.utils;

import java.util.Scanner;

import harper.exceptions.HarperException;
import harper.tasks.Task;


/**
 * The Ui class interacts with user. It read user's input and show different messages to user.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user.
     *
     * @return Greeting message.
     */
    public String greet() {
        return "\nHello! I am Harper.\n"
                + "What can I do for you?\n";
    }

    /**
     * Exits the chat.
     *
     * @return Exit message.
     */
    public String exit() {
        return "\nHope to see you again soon! Peace out!\n";
    }

    /**
     * Prints out the task list.
     *
     * @param tasksString task.Task list in string form.
     * @return TaskList if it is not empty.
     */
    public String printTasks(String tasksString) {
        if (tasksString.isEmpty()) {
            return "\nNothing is in your list :-(\n";
        } else {
            return "\nHere are the tasks in your list:\n"
                    + tasksString;
        }
    }

    /**
     * Prints out the matching task list.
     *
     * @param tasksString Matching task.Task list in string form.
     * @return Matching tasks.
     */
    public String printMatchingTasks(String tasksString) {
        if (tasksString.isEmpty()) {
            return "\nNo matching task is found :-(\n";
        } else {
            return "\nHere are the matching tasks in your list:\n"
                    + tasksString;
        }
    }

    /**
     * Prints out message for successful add.
     *
     * @param taskList task.Task list that being added.
     * @param task     task.Task that being added.
     * @return Message of adding task.
     */
    public String printSuccessfulAdd(TaskList taskList, Task task) {
        assert taskList.size() > 0 : "the task should be added into the taskList before printing this message";
        int taskListSize = taskList.size();
        return "\nGot it. I've added this task into your list:\n"
                + task.toString() + "\n"
                + "Now you have " + taskListSize + (taskListSize > 1 ? " tasks " : " task ")
                + "in the list.\n";
    }

    /**
     * Prints out message for successful delete.
     *
     * @param taskList task.Task list that being deleted.
     * @param task     task.Task that being deleted.
     * @return Message of deleting task.
     */
    public String printSuccessfulDelete(TaskList taskList, Task task) {
        int taskListSize = taskList.size();
        return "\nOk! I've removed this task for you:\n"
                + task.toString() + "\n"
                + "Now you have " + taskListSize + (taskListSize > 1 ? " tasks " : " task ") + "in the list.\n";
    }

    /**
     * Prints out the error message.
     *
     * @param exception Exception that contains the error message.
     * @return Error message.
     */
    public String showError(HarperException exception) {
        String errorMessage = exception.getMessage();
        return errorMessage;
    }

    /**
     * Prints out message for successful mark.
     *
     * @param task     task.Task to be marked.
     * @param isMarked Indicates whether to mark as done or not done.
     * @return Message of marking task.
     */
    public String printSuccessfulMark(Task task, boolean isMarked) {
        if (isMarked) {
            return "\nNice! I've marked this task as done:\n"
                    + task.toString() + "\n";
        } else {
            return "\nOK, I've marked this task as not done yet:\n"
                    + task.toString() + "\n";
        }
    }

    /**
     * Prints out message for successful update.
     *
     * @param task Task that is updated.
     * @return Message of updating task.
     */
    public String printSuccessfulUpdate(Task task) {
        return "\nNice! I've updated this task for you:\n"
                + task.toString() + "\n";
    }
}
