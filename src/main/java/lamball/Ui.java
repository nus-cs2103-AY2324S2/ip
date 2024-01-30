package lamball;

public class Ui {
    private static String welcomeMessage = "    lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    private static String indent = "    ____________________________________________________________\n";
    public Ui() {

    }

    public void greetingMessage() {
        System.out.println(welcomeMessage);
    }

    public void goodbyeMessage() {
        System.out.println(goodbyeMessage);
    }

    public void displayError(Exception e) {
        System.out.println(indent + "    " + e.getMessage() + "\n" + indent);
    }

    public static void displayAction(String msg) {
        System.out.println(indent + "    " + msg + "\n" + indent);
    }
}
