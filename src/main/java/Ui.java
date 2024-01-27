public class Ui {
    private static final String H_LINE = "\t__________________________" +
            "__________________________________";
    private static final String WELCOME =  "\tHello! I'm " +
            "TalkingBot\n\tWhat can I do for you?";
    private static final String GOODBYE = "\tBye. Hope to see you again soon!";
    private static final String LOADING_ERR_MSG = "\tUsing new file instead...";
    private static final String TASK_SET_DONE_MSG = "\tNice! I've marked this task as done:";
    private static final String TASK_SET_UNDONE_MSG = "\tAlright, I've marked this task as undone.";
    private static final String INVALID_CMD_MSG = "\tERROR! Unknown command detected."



    public Ui() {

    }

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

    public void startUi() {
        System.out.println(H_LINE);
        System.out.println(WELCOME);
        System.out.println(H_LINE);
    }

    public void printGenericError(TalkingBotException e) {
        System.out.println(e);
    }
}
