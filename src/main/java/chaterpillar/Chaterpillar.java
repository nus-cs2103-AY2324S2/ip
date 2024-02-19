package chaterpillar;

import chaterpillar.commands.Command;
import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.parser.Parser;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import javafx.util.Pair;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {
    private static final String GREETING_MESSAGE = "Hello! I'm Chaterpillar!\n"
                                                   + "What can I do for you?";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Basic constructor for this class
     *
     * @throws ChaterpillarException custom <code>Exception</code> for this application.
     */
    public Chaterpillar() throws ChaterpillarException {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadFromFile();
    }

    /**
     * Returns basic greeting message.
     *
     * @return message
     */
    public static String greet() {
        return GREETING_MESSAGE;
    }

    /**
     * Returns the appropriate response from a given input, tagged with exit status.
     *
     * @param input command from user.
     * @return response of the chatbot and exit status.
     * @throws ChaterpillarException custom <code>Exception</code> for this application.
     */
    public Pair<String, Boolean> getResponse(String input) throws ChaterpillarException {
        Command command = Parser.parse(input);
        String response = command.execute(tasks, ui, storage);
        boolean hasExited = command.hasExited();
        return new Pair<>(response, hasExited);
    }
}
