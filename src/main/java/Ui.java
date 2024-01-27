public class Ui {
    private static final String H_LINE = "\t__________________________" +
            "__________________________________";
    private static final String WELCOME =  "\tHello! I'm " +
            "TalkingBot\n\tWhat can I do for you?";
    private static final String GOODBYE = "\tBye. Hope to see you again soon!";
    private static final String LOADING_ERR_MSG = "\tUsing new file instead...";


    public Ui() {

    }

    public void printLoadingError(TalkingBotException e) {
        System.out.println("\t" + e);
        System.out.println(LOADING_ERR_MSG);
        System.out.println(H_LINE);
    }

    public void runUi() {
        System.out.println(H_LINE);
        System.out.println(WELCOME);
        System.out.println(H_LINE);
    }
}
