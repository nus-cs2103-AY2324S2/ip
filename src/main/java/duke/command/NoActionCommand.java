package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Command for doing nothing.
 * Use as a placeholder when there is invalid Command.
 */
public class NoActionCommand extends Command {

    /**
     * Initializes the Command that does nothing.
     *
     * @param type the type of the Command which is none.
     */
    public NoActionCommand(Parser.Cmd type) {
        super(type);
    }
    /**
     * Does nothing.
     *
     * @param taskList the given taskList where nothing is done.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
    }
}
