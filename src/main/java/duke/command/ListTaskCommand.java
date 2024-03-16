package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Command for listing the full task list.
 */
public class ListTaskCommand extends Command {

    /**
     * Initializes the listing Command.
     *
     * @param type the type of the Command which is list.
     */
    public ListTaskCommand(Parser.Cmd type) {
        super(type);
    }

    /**
     * Lists the tasks in the list in full details.
     *
     * @param taskList the given taskList to be listed.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        ui.displayFullList(taskList);
    }
}
