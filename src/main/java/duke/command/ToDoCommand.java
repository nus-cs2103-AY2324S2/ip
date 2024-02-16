package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in todo command.
 */
public class ToDoCommand extends Command {
    public String message;

    /**
     * Constructs the class ToDoCommand.
     *
     * @param message The command line that the user types in.
     */
    public ToDoCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            taskList.createToDo(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showToDoFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
