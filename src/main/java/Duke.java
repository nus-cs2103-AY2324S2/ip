import java.util.Scanner; // For reading user input
import java.util.ArrayList; // For storing to-do tasks
public class Duke {
    private static ArrayList<String> todoList = new ArrayList<>(100);
    public static void main(String[] args) {
        sendWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (true) {
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                // List items in to-do list
                String response = "";
                for (int i = 0; i < todoList.size(); i++) {
                    if (i < todoList.size() - 1) {
                        response += i + 1 + ". " + todoList.get(i) + "\n";
                    } else {
                        response += i + 1 + ". " + todoList.get(i);
                        botSays(response);
                    }
                }
            }
            else {
                addToList(userInput);
            }
            userInput = scanner.nextLine();
        }
        sendGoodbyeMessage();
    }

    /**
     * Sends a welcome message upon starting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nollid.\n"
                + "What can I do for you?";
        botSays (welcomeMessage);
    }

    /**
     * Sends a goodbye message upon exiting the bot, with horizontal lines
     * printed for visual separation.
     */
    public static void sendGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        botSays(goodbyeMessage);
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

    /**
     * Stores a message in the list.
     * @param message Text to store
     */
    public static void addToList(String message) {
            todoList.add(message);
            botSays("added: " + message);
    }

    /**
     * Formats message that the bot will send.
     * @param message The message for the bot to send.
     */
    public static void botSays(String message) {
        // Change message colour to cyan
        // https://www.w3docs.com/snippets/java/how-to-print-color-in-console-using-system-out-println.html
        System.out.print("\u001B[36m");

        // Length of line to be printed.
        int lineLength = 30;

        printHorizontalLine(lineLength);
        System.out.println(message);
        printHorizontalLine(lineLength);

        // Change message colour back to white
        // https://www.w3docs.com/snippets/java/how-to-print-color-in-console-using-system-out-println.html
        System.out.print("\u001B[37m");
    }
}