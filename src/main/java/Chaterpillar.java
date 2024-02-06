import exceptions.ChaterpillarException;
import commands.Command;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {

    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    public Chaterpillar() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
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

    public static void main(String[] args) {
        new Chaterpillar().run();
    }
}
