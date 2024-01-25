public class Duke {
    private static final String NAME = "Fatnom";
    private static final int lineLength = 60;

    public static void printLine() {
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void printMessage(String message) {
        Duke.printLine();
        System.out.println(message);
        Duke.printLine();
    }

    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm " + NAME + "\nWhat can I do for you?";
        Duke.printMessage(welcomeMessage);
        System.out.println("Bye. Hope to see you again soon!");
        Duke.printLine();
    }
}
