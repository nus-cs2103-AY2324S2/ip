package seiki.ui;

import static seiki.common.Messages.MESSAGE_ADD_SUCCESS;
import static seiki.common.Messages.MESSAGE_DELETE_SUCCESS;
import static seiki.common.Messages.MESSAGE_FAREWELL;
import static seiki.common.Messages.MESSAGE_GREETING;
import static seiki.common.Messages.MESSAGE_LOGO;
import static seiki.common.Messages.MESSAGE_MARK_SUCCESS;
import static seiki.common.Messages.MESSAGE_REMAINING_TASKS;
import static seiki.common.Messages.MESSAGE_TASK;
import static seiki.common.Messages.MESSAGE_UNMARK_SUCCESS;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import seiki.data.TaskList;
import seiki.data.task.Task;

/**
 * User Interface of the chatbot.
 */
public class Ui {

    private static final String DIVIDER = "────────────────────────────────────────────────────────────";

    private final Scanner in;
    private final PrintStream out;
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Initializes the input and output of the chatbot.
     * @param in
     * @param out
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Generates and prints the welcome message upon the start of the chatbot.
     */
    public void showWelcome() {
        showToUser(DIVIDER,
                MESSAGE_LOGO,
                MESSAGE_GREETING,
                DIVIDER);
    }

    /**
     * Shows the message(s) to the user.
     * @param message
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showError(String message) {
        showToUser(message);
    }

    /**
     * Generates and prints out the farewell message upon termination.
     */
    public void showEnd() {
        showToUser(MESSAGE_FAREWELL,
                DIVIDER);
        in.close();
    }

    /**
     * Generates and prints out the newly added task upon success.
     * @param task
     * @param taskList
     */
    public void showAddTask(Task task, TaskList taskList) {
        showToUser(MESSAGE_ADD_SUCCESS,
                String.format(MESSAGE_TASK, task),
                String.format(MESSAGE_REMAINING_TASKS, taskList.getTaskCount()));
    }

    /**
     * Generates and prints out all tasks.
     * @param taskList
     */
    public void showList(TaskList taskList) {
        showToUser(taskList.toString());
    }

    /**
     * Generates and prints out the newly marked task upon success.
     * @param task
     */
    public void showMarkTask(Task task) {
        showToUser(MESSAGE_MARK_SUCCESS,
                String.format(MESSAGE_TASK, task));
    }

    /**
     * Generates and prints out the newly unmarked task upon success.
     * @param task
     */
    public void showUnmarkTask(Task task) {
        showToUser(MESSAGE_UNMARK_SUCCESS,
                String.format(MESSAGE_TASK, task));
    }

    /**
     * Generates and prints out the deleted task upon success.
     * @param task
     * @param taskList
     */
    public void showDeleteTask(Task task, TaskList taskList) {
        showToUser(MESSAGE_DELETE_SUCCESS,
                String.format(MESSAGE_TASK, task),
                String.format(MESSAGE_REMAINING_TASKS, taskList.getTaskCount()));
    }
}
