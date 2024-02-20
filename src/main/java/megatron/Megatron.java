package megatron;

import megatron.commands.Command;
import megatron.commands.ExitCommand;
import megatron.data.exception.DukeException;
import megatron.parser.Parser;
import megatron.storage.Storage;
import megatron.task.TaskList;
import megatron.ui.Ui;

/**
 * Duke class as main
 */
public class Megatron {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private Command lastCommand;

    /**
     * Constructor
     * @param filePath for path to store txt file
     */
    public Megatron(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList tempTasks = new TaskList();
        try {
            tempTasks = new TaskList(storage.load());
        } catch (Exception e) {
            tempTasks = new TaskList();
        } finally {
            this.tasks = tempTasks;
        }
        this.lastCommand = new ExitCommand(); // dummy
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand, lastCommand);
            if (c.getIsUndoable()) {
                this.lastCommand = c;
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.showError("Invalid Number");
        }
    }
}



