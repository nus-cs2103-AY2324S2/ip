import java.util.Scanner;

public class Ui {

    /**
     * Sends a welcome message upon starting the bot, with horizontal lines
     * printed for visual separation.
     */
    public void sendWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nollid.\n" + "What can I do for you?";
        sendMessage(welcomeMessage);
    }

    /**
     * Formats message that the bot will send.
     *
     * @param message The message for the bot to send.
     */
    public void sendMessage(String message) {
        // Length of line to be printed.
        int lineLength = 30;

        printHorizontalLine(lineLength);
        System.out.println(message);
        printHorizontalLine(lineLength);
    }

    /**
     * Sends a goodbye message upon exiting the bot, with horizontal lines
     * printed for visual separation.
     */
    public void sendGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        sendMessage(goodbyeMessage);
    }

    /**
     * Prints a horizontal line with unicode character U+2500.
     *
     * @param length Length of line in characters.
     */
    public void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    /**
     * Returns a String of the user's input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().strip();
    }
}
