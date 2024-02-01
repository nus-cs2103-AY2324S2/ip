package sleepy;

import sleepy.datastorage.ItemList;
import sleepy.tools.LinePrinter;

/**
 * This class is the main class for the Sleepy AI Chatbot.
 *
 * @author kjw142857
 */
import java.util.Scanner;
public class Sleepy {
    public static void main(String[] args) {
        // Initialise chatbot
        ItemList itemList = new ItemList();
        String name = "Sleepy";
        String welcomeLine = "Hello! I'm " + name;
        String questionLine = "What can I do for you?";
        LinePrinter.printLine(welcomeLine);
        LinePrinter.printLine(questionLine);
        // Await next command from user
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String nextUserCommand = userInput.nextLine();
            if (nextUserCommand.equals("bye")) {
                LinePrinter.printExit();
                break;
            }
            itemList.access(nextUserCommand);
        }
    }
}
