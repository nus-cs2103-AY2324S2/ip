package wei;

import java.io.IOException;

import commands.Command;
import exceptions.WeiException;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Represents a chatbot that can keep track of your tasks.
 */
public class Wei {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a Wei chatbot.
     */
    public Wei() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage("./data/history.txt");
            tasks = storage.read();
        } catch (IOException | WeiException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Greets the user.
     *
     * @return reply message.
     */
    public String greet() {
        return ui.greet();
    }

    /**
     * Generates response to the user.
     *
     * @param input user input.
     * @return reply message.
     */
    public String run(String input) {
        String reply = "";
        try {
            Command userCommand = parser.parse(input);
            reply = userCommand.execute(tasks, ui, storage);
        } catch (WeiException e) {
            reply = ui.showError(e.getMessage());
        }
        return reply;
    }
}
