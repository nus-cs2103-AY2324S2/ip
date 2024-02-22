package talkingbot.util;

import talkingbot.exception.TalkingBotException;
import talkingbot.task.Task;

/**
 * Class that represents the user interface.
 */
public class Ui {
    private static final String H_LINE = "\t__________________________"
            + "__________________________________";
    private static final String WELCOME = "\tHello! I'm "
            + "TalkingBot\n\tWhat can I do for you?";
    private static final String GOODBYE = "\tBye. Hope to see you again soon!";
    private static final String LOADING_ERR_MSG = "\tUsing new file instead...";
    private static final String FIND_CMD_ERR_MSG = "\tERROR! find needs to have a parameter.";
    private static final String TASK_SET_DONE_MSG = "\tNice! I've marked this task as done:";
    private static final String TASK_SET_UNDONE_MSG = "\tAlright, I've marked this task as undone.";
    private static final String INVALID_CMD_MSG = "\tERROR! Unknown command detected.";
    private static final String SAVE_ONGOING_MSG = "\tSaving tasks to file: ";
    private static final String SAVE_DONE_MSG = "\tSave done!";
    private static final String ADD_TASK_MSG = "\tAlright, I've added this task to your list:";
    private static final String TASK_NUM_MSG_1 = "\tYou now have";
    private static final String TASK_NUM_MSG_2 = "tasks in the list.";
    private static final String REMOVE_TASK_MSG = "\tRemoving task:";

    /**
     * Creates a new Ui instance.
     */
    public Ui() {}

    /**
     * Returns a loading error and the exception that caused it.
     *
     * @param e The exception causing the loading error.
     * @return The loading error string.
     */
    public String getLoadingErrorMsg(TalkingBotException e) {
        return String.format("\t%s\n%s\n%s\n", e, LOADING_ERR_MSG, H_LINE);
    }

    /**
     * Returns a message when a task is marked as done.
     *
     * @param modifiedTask Task that has been modified.
     * @return String indicating the task is set as done.
     */
    public String getTaskDoneMsg(Task modifiedTask) {
        return String.format("%s\n\t%s\n", TASK_SET_DONE_MSG, modifiedTask);
    }

    /**
     * Returns a message when a task is marked as undone.
     *
     * @param modifiedTask Task that has been modified.
     * @return String indicating the task is set as undone.
     */
    public String getTaskSetUndoneMsg(Task modifiedTask) {
        return String.format("%s\n\t%s\n", TASK_SET_UNDONE_MSG, modifiedTask);
    }

    /**
     * Returns a message when an invalid command is entered.
     *
     * @return A formatted String.
     */
    public String getInvalidCmdMsg() {
        return String.format("%s\n", INVALID_CMD_MSG);
    }

    /**
     * Returns a welcome message when the application starts.
     *
     * @return A formatted String.
     */
    public String getWelcomeMsg() {
        return String.format("%s\n", WELCOME);
    }

    /**
     * Returns a generic error message and the exception that caused it.
     *
     * @param e Exception to be printed.
     */
    public String getGenericErrorMsg(TalkingBotException e) {
        return String.format("\t%s\n", e.getMessage());
    }


    /**
     * Returns a save that is ongoing and the file path of the save.
     *
     * @param filePath File path of the save file.
     */
    public String getSaveOngoingMsg(String filePath) {
        return String.format("%s%s\n", SAVE_ONGOING_MSG, filePath);
    }

    /**
     * Returns a message indicating that the saving has been done.
     *
     * @return A formatted String.
     */
    public String getSaveDoneMsg() {
        return String.format("%s\n", SAVE_DONE_MSG);
    }

    /**
     * Returns a message when tasks are added.
     *
     * @param curTask Task that is added.
     * @param numTasks Current size of the task list.
     */
    public String getAddTaskMsg(Task curTask, int numTasks) {
        return String.format("%s\n\t\t%s\n%s %d %s\n",
                ADD_TASK_MSG, curTask, TASK_NUM_MSG_1, numTasks, TASK_NUM_MSG_2);
    }

    /**
     * Returns a message when tasks are deleted.
     *
     * @param removedTask Task that is removed.
     * @param numTasks Current size of the task list.
     */
    public String getDeleteTaskMsg(Task removedTask, int numTasks) {
        return String.format("%s\n\t\t%s\n%s %d %s\n",
                REMOVE_TASK_MSG, removedTask, TASK_NUM_MSG_1, numTasks,
                TASK_NUM_MSG_2);
    }

    /**
     * Returns an error message when a find command does not execute correctly.
     *
     * @return A formatted String.
     */
    public String getFindCmdErrMsg() {
        return String.format("%s\n", FIND_CMD_ERR_MSG);
    }

    /**
     * Returns a goodbye message.
     *
     * @return A formatted String.
     */
    public String getGoodbyeMsg() {
        return String.format("%s\n", GOODBYE);
    }
}
