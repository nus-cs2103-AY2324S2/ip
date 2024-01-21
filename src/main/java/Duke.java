import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class running the main chatbot program.
 */
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(100);

        Duke.greet();

        while (true) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                break;
            }

            if (message.equals("list")) {
                Duke.listMessages(list);
            } else {
                Duke.addMessageToList(list, message);
            }
        }

        Duke.exit();
    }

    /**
     * Greet behaviour of chatbot.
     */
    private static void greet() {
        System.out.println("Hello, I'm Ted.");
        System.out.println("What can I do for you today?");
    }

    /**
     * Exit behaviour of chatbot.
     */
    private static void exit() {
        System.out.println("Bye. See you again.");
    }

    /**
     * Stores a message to a list.
     *
     * @param list List to store recorded items.
     * @param message Message to be recorded.
     */
    private static void addMessageToList(ArrayList<String> list, String message) {
        list.add(message);
        System.out.println("    Added: " + message);
    }

    /**
     * List all messages recorded by the chatbot.
     *
     * @param list List of messages that were recorded.
     */
    private static void listMessages(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("    " + index + ". " + list.get(i));
        }
    }
}