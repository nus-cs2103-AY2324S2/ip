package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Command for exiting the program.
 */
public class ByeCommand extends Command {

    /**
     * Initializes the exiting Command.
     *
     * @param type the type of the Command which is bye.
     */
    public ByeCommand(Parser.Cmd type) {
        super(type);
    }

    /**
     * Ends the program.
     *
     * @param taskList the given taskList to run the function on.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        ui.leave();
    }
}
