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


public class Ui {

    private static final String DIVIDER = "────────────────────────────────────────────────────────────";

    private final Scanner in;
    private final PrintStream out;
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        showToUser(DIVIDER,
                MESSAGE_LOGO,
                MESSAGE_GREETING,
                DIVIDER);
    }

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

    public void showEnd() {
        showToUser(MESSAGE_FAREWELL,
                DIVIDER);
        in.close();
    }

    public void showAddTask(Task task, TaskList taskList) {
        showToUser(MESSAGE_ADD_SUCCESS,
                String.format(MESSAGE_TASK, task),
                String.format(MESSAGE_REMAINING_TASKS, taskList.getTaskCount()));
    }

    public void showList(TaskList taskList) {
        showToUser(taskList.toString());
    }

    public void showMarkTask(Task task) {
        showToUser(MESSAGE_MARK_SUCCESS,
                String.format(MESSAGE_TASK, task));
    }

    public void showUnmarkTask(Task task) {
        showToUser(MESSAGE_UNMARK_SUCCESS,
                String.format(MESSAGE_TASK, task));
    }

    public void showDeleteTask(Task task, TaskList taskList) {
        showToUser(MESSAGE_DELETE_SUCCESS,
                String.format(MESSAGE_TASK, task),
                String.format(MESSAGE_REMAINING_TASKS, taskList.getTaskCount()));
    }
}
