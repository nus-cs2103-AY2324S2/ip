package chaterpillar;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.commands.Command;
import chaterpillar.parser.Parser;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

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
    }
}
