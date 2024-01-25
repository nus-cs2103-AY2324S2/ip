public abstract class Ui {
    private static void horizontalLines() {
        System.out.println("----------------------------------------------------------------");
    }
    public static void start() {
        Ui.horizontalLines();
        final String greetings = String.format( "Hello! I'm %s\n"
                + "What can I do for you?\n", Duke.botName);
        System.out.print(greetings);
        Ui.horizontalLines();
    }

    public static void end() {
        final String endings = "Bye. Hope to see you again soon!";
        System.out.println(endings);
        Ui.horizontalLines();
    }
}
