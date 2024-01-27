public class Ui {
    private static final String H_LINE = "\t__________________________" +
            "__________________________________";
    private static final String WELCOME =  "\tHello! I'm " +
            "TalkingBot\n\tWhat can I do for you?";
    private static final String GOODBYE = "\tBye. Hope to see you again soon!";
    private static final String LOADING_ERR_MSG = "\tUsing new file instead...";
    private static final String TASK_SET_DONE_MSG = "\tNice! I've marked this task as done:";
    private static final String TASK_SET_UNDONE_MSG = "\tAlright, I've marked this task as undone.";
    private static final String INVALID_CMD_MSG = "\tERROR! Unknown command detected.";
    private static final String SAVE_ONGOING_MSG = "\tSaving tasks to file: ";
    private static final String SAVE_DONE_MSG = "\tSave done!";
    private static final String ADD_TASK_MSG = "\tAlright, I've added this task to your list:";
    private static final String TASK_NUM_MSG = "\tYou now have %d tasks in the list.";
    private static final String REMOVE_TASK_MSG = "\tRemoving task:";

    private boolean continueIter = true;



    public Ui() {}

    public void printLoadingError(TalkingBotException e) {
        System.out.println("\t" + e);
        System.out.println(LOADING_ERR_MSG);
        System.out.println(H_LINE);
    }

    public void printTaskSetDone(Task modifiedTask) {
        System.out.println(TASK_SET_DONE_MSG);
        System.out.println("\t" + modifiedTask);
    }

    public void printTaskSetUndone(Task modifiedTask) {
        System.out.println(TASK_SET_UNDONE_MSG);
        System.out.println("\t" + modifiedTask);
    }

    public void printInvalidCmdMsg() {
        System.out.println(INVALID_CMD_MSG);
    }

    public void printWelcomeMsg() {
        System.out.println(H_LINE);
        System.out.println(WELCOME);
        System.out.println(H_LINE);
    }

    public void printGenericError(TalkingBotException e) {
        System.out.println("\t" + e);
    }

    public void printLine() {
        System.out.println(H_LINE);
    }

    public void printSaveOngoingMsg(String filePath) {
        System.out.println(SAVE_ONGOING_MSG + filePath);
    }

    public void printSaveDoneMsg() {
        System.out.println(SAVE_DONE_MSG);
    }

    public void printAddTask(Task curTask, int taskListSize) {
        System.out.println(ADD_TASK_MSG);
        System.out.println("\t\t" + curTask);
        System.out.println(String.format(TASK_NUM_MSG, taskListSize));
    }

    public void printDeleteTask(Task removedTask, int taskListSize) {
        System.out.println(REMOVE_TASK_MSG);
        System.out.println("\t\t" + removedTask);
        System.out.println(String.format(TASK_NUM_MSG, taskListSize));
    }

    public void printGoodbyeMsg() {
        System.out.println(GOODBYE);
    }

    public void setContinueIter(boolean continueIter) {
        this.continueIter = continueIter;
    }

    public boolean getContinueIter() {
        return this.continueIter;
    }
}
