package seiki.ui;

import static seiki.common.Messages.MESSAGE_ADD_SUCCESS;
import static seiki.common.Messages.MESSAGE_DELETE_SUCCESS;
import static seiki.common.Messages.MESSAGE_FAREWELL;
import static seiki.common.Messages.MESSAGE_FIND_SUCCESS;
import static seiki.common.Messages.MESSAGE_GREETING;
import static seiki.common.Messages.MESSAGE_LIST_SUCCESS;
import static seiki.common.Messages.MESSAGE_LOGO;
import static seiki.common.Messages.MESSAGE_MARK_SUCCESS;
import static seiki.common.Messages.MESSAGE_REMAINING_TASKS;
import static seiki.common.Messages.MESSAGE_TASK;
import static seiki.common.Messages.MESSAGE_UNMARK_SUCCESS;

import java.util.Scanner;

import seiki.data.TaskList;
import seiki.data.task.Task;

/**
 * User Interface of the chatbot.
 */
public class Ui {

    private final Scanner in;
    private StringBuilder out;

    /**
     * Initializes the input and output of the chatbot.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = new StringBuilder();
    }

    /**
     * Generates and prints the welcome message upon the start of the chatbot.
     */
    public void showWelcome() {
        showToUser(MESSAGE_LOGO,
                MESSAGE_GREETING);
    }

    /**
     * Shows the message(s) to the user.
     * @param message
     */
    public String showToUser(String... message) {
        for (String m : message) {
            out.append(m).append("\n");
        }
        String temp = out.toString();
        assert !temp.isEmpty() : "Output should not be empty";
        out = new StringBuilder();
        return temp;
    }

    /**
     * Generates and prints out the error message.
     * @param message
     */
    public String showError(String message) {
        return showToUser(message);
    }

    /**
     * Generates and prints out the farewell message upon termination.
     */
    public String showEnd() {
        in.close();
        return showToUser(MESSAGE_FAREWELL);
    }

    /**
     * Generates and returns the newly added task upon success.
     * @param task
     * @param taskList
     */
    public String showAddTask(Task task, TaskList taskList) {
        return showToUser(MESSAGE_ADD_SUCCESS,
                String.format(MESSAGE_TASK, task),
                String.format(MESSAGE_REMAINING_TASKS, taskList.getTaskCount()));
    }

    /**
     * Generates and returns all tasks.
     * @param taskList
     */
    public String showList(TaskList taskList) {
        assert taskList.getTaskCount() > 0 : "Task list should contain tasks";
        return showToUser(MESSAGE_LIST_SUCCESS,
                taskList.toString());
    }

    /**
     * Generates and returns the newly marked task upon success.
     * @param task
     */
    public String showMarkTask(Task task) {
        return showToUser(MESSAGE_MARK_SUCCESS,
                String.format(MESSAGE_TASK, task));
    }

    /**
     * Generates and returns the newly unmarked task upon success.
     * @param task
     */
    public String showUnmarkTask(Task task) {
        return showToUser(MESSAGE_UNMARK_SUCCESS,
                String.format(MESSAGE_TASK, task));
    }

    /**
     * Generates and returns the deleted task upon success.
     * @param task
     * @param taskList
     */
    public String showDeleteTask(Task task, TaskList taskList) {
        return showToUser(MESSAGE_DELETE_SUCCESS,
                String.format(MESSAGE_TASK, task),
                String.format(MESSAGE_REMAINING_TASKS, taskList.getTaskCount()));
    }

    /**
     * Generates and returns the found tasks upon success.
     * @param keyword
     * @param resultList
     */
    public String showFindTask(String keyword, TaskList resultList) {
        return showToUser(String.format(MESSAGE_FIND_SUCCESS, keyword),
                resultList.toString());
    }
}
