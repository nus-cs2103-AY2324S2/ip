public class Ui {
    private static String welcomeMessage = "    Lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";
    public Ui() {

    }

    public static void greetingMessage() {
        System.out.println(welcomeMessage);
    }

    public static void goodbyeMessage() {
        System.out.println(goodbyeMessage);
    }
}
