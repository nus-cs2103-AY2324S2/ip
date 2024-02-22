package blawg;

import commands.Command;
import exceptions.BlawgException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * <p>
 *  Represents a chatBox Ai. A <code>Blawg</code> object allows a User to
 *  * relay instructions and have the instructions be executed. The commands
 *  * that Blawg supports are list, deadline, event, todo, delete, bye, mark, unmark.
 * </p>
 */
public class Blawg {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * initialises a Blawg class
     */
    public Blawg() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.read());
        } catch (BlawgException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        assert !input.trim().isEmpty() : "input should not be empty";
        boolean isExit = false;
        try {
            Command c = new Parser().parse(input);
            String result = c.execute(tasks, ui, storage);

            return result;
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        return "";
    }
}
