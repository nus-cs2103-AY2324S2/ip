package chatbot;

import java.util.Scanner;

import chatbot.action.Action;
import chatbot.action.ByeAction;
import chatbot.action.exception.ActionException;
import chatbot.parse.InputParser;
import chatbot.storage.LocalStorage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * This encapsulates the behaviour of a chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot {
    /** Stores the scanner instance used to get the console input stream. */
    private static final Scanner SCANNER = new Scanner(System.in);

    /** Stores the name of this. */
    private final String chatBotName;

    /** Stores the user's tasks. */
    private final TaskList userList;

    /**
     * Class constructor.
     *
     * @param chatBotName the name of this chatbot.
     */
    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
        userList = LocalStorage.loadTaskList();
    }

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot("Stratify");
        chatBot.run();
    }

    /**
     * Greets the user when entering a session with this {@link ChatBot}.
     */
    private void greet() {
        Printer.printMessages(
                "Hello! I'm " + chatBotName + "!",
                "What can I do for you?"
        );
    }

    /**
     * Runs the chatbot main loop.
     */
    public void run() {
        greet();

        Action userAction = null;
        do {
            try {
                userAction = InputParser.getParsedInput(SCANNER.nextLine());
                userAction.execute(userList);
            } catch (ActionException e) {
                Printer.printMessages(e.getMessage());
            }
        } while (!(userAction instanceof ByeAction));
    }
}
