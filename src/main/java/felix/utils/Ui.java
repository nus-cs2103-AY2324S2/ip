package felix.utils;

import java.util.Scanner;

import felix.task.Task;

/**
 * Class representing abstraction for user interface
 */
public class Ui {
    private static final int LINE_LENGTH = 55;
    private static final String BOT_NAME = "Felix";
    private static final String LOGO = "___________    .__  .__        \n"
            + "\\_   _____/___ |  | |__|__  ___\n"
            + " |    __)/ __ \\|  | |  \\  \\/  / \n"
            + " |     \\\\  ___/|  |_|  |>    <  \n"
            + " \\___  / \\___  >____/__/__/\\_ \\ \n"
            + "     \\/      \\/              \\/ \n";
    private static final String LINE_SEPARATOR = "_".repeat(LINE_LENGTH);
    private static final String ADD_TASK_MSG = "Got it. I've added this task:";
    private static final String DELETE_TASK_MSG = "Noted. I've removed this task:";
    private static final String FIND_TASK_MSG = "Here are the matching tasks in your list:";
    private static final String MARK_MSG = "Nice! I have marked this task as done:";
    private static final String UNMARK_MSG = "OK, I've marked this task as not done yet:";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UPDATE_MSG = "Task was successfully updated:";
    private final Scanner scanner;

    /**
     * Constructor for Ui class
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns String representation of object.
     */
    public String toString(Object obj) {
        return obj.toString();
    }

    /**
     * Returns horizontal line separator of length 60.
     */
    public String getHorizontalLine() {
        return LINE_SEPARATOR;
    }

    /**
     * Returns Exception message.
     */
    public String getExceptionMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns message to be displayed and corresponding Exception if an error occurs when loading from hard disk file.
     */
    public String getLoadingError(Exception e) {
        return String.format("Error occurred when loading file:\n%s\n%s",
                this.getExceptionMessage(e), this.getHorizontalLine());
    }

    /**
     * Returns chatbot logo.
     */
    public String getLogo() {
        return String.format("%s\n%s", LOGO, this.getHorizontalLine());
    }

    /**
     * Returns chatbot introduction.
     */
    public String getIntroduction() {
        return String.format("%s\nHello! I'm %s\nWhat can I do for you?\n%s",
                this.getHorizontalLine(), BOT_NAME, this.getHorizontalLine());
    }

    /**
     * Returns goodbye message.
     */
    public String getExitMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns message to be displayed when a new task is added.
     * @param task Task to be added.
     * @param taskCount Number of tasks in task list.
     */
    public String getAddTaskMessage(Task task, int taskCount) {
        return String.format("%s\n%s\nNow you have %d tasks in the list.", ADD_TASK_MSG,
                task.toString(), taskCount);
    }

    /**
     * Returns message to be displayed when a task is successfully deleted.
     * @param task Task to be added.
     * @param taskCount Number of tasks in task list.
     */
    public String getDeleteTaskMessage(Task task, int taskCount) {
        return String.format("%s\n%s\nNow you have %d tasks in the list.", DELETE_TASK_MSG,
                task.toString(), taskCount);
    }

    /**
     * Returns message to be displayed when a find command is issued.
     * @param matchingTasks String representation of list of tasks matching keyword.
     */
    public String getFindMessage(String matchingTasks) {
        return String.format("%s\n%s", FIND_TASK_MSG, matchingTasks);
    }

    /**
     * Returns message to be displayed when a task is marked as done.
     * @param task Task to be marked as done.
     */
    public String getMarkMessage(Task task) {
        return String.format("%s\n%s", MARK_MSG, task.toString());
    }

    /**
     * Returns message to be displayed when a task is unmarked as done.
     * @param task Task to be unmarked as done.
     */
    public String getUnmarkMessage(Task task) {
        return String.format("%s\n%s", UNMARK_MSG, task.toString());
    }

    /**
     * Returns message to be displayed when task is updated.
     * @param oldTask Previous task.
     * @param newTask Updated task.
     */
    public String getUpdateMessage(Task oldTask, Task newTask) {
        return String.format("%s\nOld task: %s\n%s\nNew task: %s",
                UPDATE_MSG, oldTask.toString(),
                this.getHorizontalLine(), newTask.toString());
    }

    /**
     * Returns next line from Scanner object.
     */
    public String getNextLine() {
        return this.scanner.nextLine();
    }
}
