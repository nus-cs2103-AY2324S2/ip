public class Duke {
    /**
     * Length of horizontal line in characters.
     * Printed for visual separation.
     */
    private static int lineLength = 30;
    public static void main(String[] args) {
        sendWelcomeMessage();
        sendGoodbyeMessage();
    }

    /**
     * Sends a welcome message upon starting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendWelcomeMessage() {
        printHorizontalLine(lineLength);
        System.out.println("Hello! I'm Nollid.\n"
                + "What can I do for you?");
        printHorizontalLine(lineLength);
    }

    /**
     * Sends a goodbye message upon exiting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendGoodbyeMessage() {

        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine(lineLength);
    }

    /**
     * Prints a horizontal line with unicode character U+2500.
     * @param length Length of line in characters.
     */
    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println();
    }
}
