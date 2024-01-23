import action.Action;
import action.ByeAction;
import action.exception.ActionException;
import print.Printer;
import task.TaskList;

/**
 * ChatBot encapsulates the behaviour of a Chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot {
    /**
     * Stores the name of this chatbot.
     */
    private final String chatBotName;

    /**
     * Stores the user's tasks
     */
    private final TaskList userList = new TaskList();

    /**
     * Class constructor.
     *
     * @param chatBotName the name of this chatbot.
     */
    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * Greets the user when entering a session with this chatbot.
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

        Action userAction = null;
        do {
            try {
                userAction = InputParser.getParsedInput();
                userAction.execute(userList);
            } catch (ActionException e) {
                Printer.printMessages(e.getMessage());
            }
        } while (!(userAction instanceof ByeAction));
    }
}
