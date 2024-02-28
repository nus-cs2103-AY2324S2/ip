package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.Ui;

/**
 * Represents a unmark command. Unmarks a task in the tasklist
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Construct a Command to have invalid command type and assign its default error default
     */
    public UnmarkCommand(int index) {
        super(Command.CommandType.UNMARK);
        this.index = index;
    }

    /**
     * Unmarks a task as done in the tasklist, settting its check to false.
     * Prompts an InvalidCommand if the referenced index is invalid
     * Then shows the list
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.unmark(index);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
        ui.showList(tl);
    }
}
