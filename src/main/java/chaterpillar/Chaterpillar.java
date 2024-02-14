package chaterpillar;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.commands.Command;
import chaterpillar.parser.Parser;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

import javafx.util.Pair;

import java.io.IOException;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Chaterpillar() throws ChaterpillarException, IOException {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadFromFile();
    }

    /**
     * Returns basic greeting message.
     * @return message
     */
    public static String greet() {
        return "Hello! I'm Chaterpillar!\n"
                + "What can I do for you?";
    }

    /**
     * Returns the appropriate response from a given input, tagged with exit status.
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

    /* Potentially obsolete code
    public void run() {
        ui.greet();
        boolean hasExited = false;

        while (!hasExited) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                hasExited = command.hasExited();
            } catch (ChaterpillarException | IOException e) {
                ui.echo(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) throws ChaterpillarException, IOException {
        new Chaterpillar().run();
    }*/
}
