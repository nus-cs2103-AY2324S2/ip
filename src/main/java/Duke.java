public class Duke {
    public static String welcome = "Hi, I'm Gronk! \uD83D\uDDFF \n"
            + "What are we up to today?";

    public static String goodbye = "System closing. Goodbye!";

    public static void lines() {
        System.out.println("----------------------------------------");
    }

    public static void printMessage(String message) {
        System.out.println(message);
        lines();
    }

    public static void main(String[] args) {
        lines();
        printMessage(welcome);
        printMessage(goodbye);
    }
}
