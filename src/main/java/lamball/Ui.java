package lamball;

public class Ui {
    private static final String WELCOME_MESSAGE = "    lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static final String GOODBYE_MESSAGE = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    private static final String INDENT = "    ____________________________________________________________\n";
    public Ui() {

    }

    public void greetingMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void goodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void displayError(Exception e) {
        System.out.println(INDENT + "    " + e.getMessage() + "\n" + INDENT);
    }

    public static void displayAction(String msg) {
        System.out.println(INDENT + "    " + msg + "\n" + INDENT);
    }
}
