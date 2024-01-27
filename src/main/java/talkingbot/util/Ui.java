package talkingbot.util;

import talkingbot.exception.TalkingBotException;
import talkingbot.task.Task;

/**
 * Class that represents the user interface.
 */
public class Ui {
    private static final String H_LINE = "\t__________________________" +
            "__________________________________";
    private static final String WELCOME =  "\tHello! I'm " +
            "TalkingBot\n\tWhat can I do for you?";
    private static final String GOODBYE = "\tBye. Hope to see you again soon!";
    private static final String LOADING_ERR_MSG = "\tUsing new file instead...";
    private static final String TASK_SET_DONE_MSG = "\tNice! I've marked this talkingbot.task as done:";
    private static final String TASK_SET_UNDONE_MSG = "\tAlright, I've marked this talkingbot.task as undone.";
    private static final String INVALID_CMD_MSG = "\tERROR! Unknown talkingbot.command detected.";
    private static final String SAVE_ONGOING_MSG = "\tSaving tasks to file: ";
    private static final String SAVE_DONE_MSG = "\tSave done!";
    private static final String ADD_TASK_MSG = "\tAlright, I've added this talkingbot.task to your list:";
    private static final String TASK_NUM_MSG = "\tYou now have %d tasks in the list.";
    private static final String REMOVE_TASK_MSG = "\tRemoving talkingbot.task:";

    private boolean continueIter = true;


    /**
     * Constructor for the Ui class.
     */
    public Ui() {}

    /**
     * Prints a loading error and the exception that caused it.
     *
     * @param e The exception causing the loading error.
     */
    public void printLoadingError(TalkingBotException e) {
        System.out.println("\t" + e);
        System.out.println(LOADING_ERR_MSG);
        System.out.println(H_LINE);
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param modifiedTask Task that has been modified.
     */
    public void printTaskSetDone(Task modifiedTask) {
        System.out.println(TASK_SET_DONE_MSG);
        System.out.println("\t" + modifiedTask);
    }

    /**
     * Prints a message when a task is marked as undone.
     *
     * @param modifiedTask Task that has been modified.
     */
    public void printTaskSetUndone(Task modifiedTask) {
        System.out.println(TASK_SET_UNDONE_MSG);
        System.out.println("\t" + modifiedTask);
    }

    /**
     * Prints a message when an invalid command is entered.
     */
    public void printInvalidCmdMsg() {
        System.out.println(INVALID_CMD_MSG);
    }

    /**
     * Prints a welcome message when the application starts.
     */
    public void printWelcomeMsg() {
        System.out.println(H_LINE);
        System.out.println(WELCOME);
        System.out.println(H_LINE);
    }

    /**
     * Prints a generic error message and the exception that caused it.
     *
     * @param e Exception to be printed.
     */
    public void printGenericError(TalkingBotException e) {
        System.out.println("\t" + e);
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println(H_LINE);
    }

    /**
     * Prints a save that is ongoing and the file path of the save.
     *
     * @param filePath File path of the save file.
     */
    public void printSaveOngoingMsg(String filePath) {
        System.out.println(SAVE_ONGOING_MSG + filePath);
    }

    /**
     * Prints a message indicating that the saving has been done.
     */
    public void printSaveDoneMsg() {
        System.out.println(SAVE_DONE_MSG);
    }

    /**
     * Prints a message when tasks are added.
     *
     * @param curTask Task that is added.
     * @param taskListSize Current size of the task list.
     */
    public void printAddTask(Task curTask, int taskListSize) {
        System.out.println(ADD_TASK_MSG);
        System.out.println("\t\t" + curTask);
        System.out.println(String.format(TASK_NUM_MSG, taskListSize));
    }

    /**
     * Prints a message when tasks are deleted.
     *
     * @param removedTask Task that is removed.
     * @param taskListSize Current size of the task list.
     */
    public void printDeleteTask(Task removedTask, int taskListSize) {
        System.out.println(REMOVE_TASK_MSG);
        System.out.println("\t\t" + removedTask);
        System.out.println(String.format(TASK_NUM_MSG, taskListSize));
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMsg() {
        System.out.println(GOODBYE);
    }

    /**
     * Sets whether the application should continue running.
     *
     * @param continueIter Boolean value setting the application's status.
     */
    public void setContinueIter(boolean continueIter) {
        this.continueIter = continueIter;
    }

    /**
     * Returns a boolean indicating whether the application should continue running.
     *
     * @return Boolean value indicating the application's status.
     */
    public boolean getContinueIter() {
        return this.continueIter;
    }
}
