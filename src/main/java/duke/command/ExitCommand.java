package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The type Exit command.
 * Used when user types command "bye" to exit from Duke application
 */
public class ExitCommand extends Command{
    /**
     * Execute.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!");
        ui.setCommandOutput(sb.toString());
    }

    /**
     * Is exit value is set to true.
     * Duke class detects this flag and exits application
     *
     * @return the boolean
     * @throws DukeException the duke exception
     */
    public boolean isExit() throws DukeException {
        return true;
    }
}
