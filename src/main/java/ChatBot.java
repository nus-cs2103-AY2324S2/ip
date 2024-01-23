import action.Action;
import action.ByeAction;
import print.Printer;
import task.TaskList;

import java.util.Scanner;

/**
 * ChatBot encapsulates the behaviour of a Chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot {
    /**
     * Stores the name of the ChatBot.
     */
    private final String chatBotName;

    /**
     * Stores the scanner instance used to get the console input stream.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Stores the user's tasks
     */
    private final TaskList userList = new TaskList();

    /**
     * Class constructor.
     * @param chatBotName The name of the chatbot.
     */
    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * Greets the user when entering the application.
     */
    private void greet() {
        Printer.printMessages(
                "Hello! I'm " + chatBotName + "!",
                "What can I do for you?"
        );
    }

    /**
     * Reads and parses user input for commands.
     */
    public void run() {
        greet();

        Action userAction;
        do {
            userAction = InputParser.parseInput(scanner.nextLine());
            userAction.execute(userList);
        } while (!(userAction instanceof ByeAction));
    }
}
