import java.util.Scanner;

/**
 * ChatBot encapsulates the behaviour of a Chatbot.
 *
 * @author Titus Chew
 */
public class ChatBot {
    private final String chatBotName;
    private final Scanner scanner = new Scanner(System.in);
    private static final String COMMAND_BYE = "bye";
    private static final String INDENT = "    ";

    /**
     * Class constructor.
     *
     * @param chatBotName the name of the chatbot
     */
    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * Greets the user when entering the application.
     */
    private void greet() {
        printMessage(String.format(
                INDENT + "Hello! I'm %s!\n" +
                INDENT + "What can I do for you?",
                chatBotName));
    }

    /**
     * Greets the user when exiting the application.
     */
    private void exit() {
        printMessage(INDENT + "Bye! Hope to see you again soon!");
    }

    /**
     * Inserts a horizontal line.
     */
    private void insertLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Prints a message to the console.
     *
     * @param message the message to print in the console
     */
    private void printMessage(String message) {
        insertLine();
        System.out.println(message);
        insertLine();
    }

    /**
     * Reads and parses user input for commands.
     */
    public void run() {
        greet();
        String userInput = scanner.nextLine();
        while (!userInput.equals(COMMAND_BYE)) {
            printMessage(INDENT + userInput);
            userInput = scanner.nextLine();
        }
        exit();
    }
}
