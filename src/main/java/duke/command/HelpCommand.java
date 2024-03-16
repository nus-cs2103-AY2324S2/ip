package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command of getting help in commands for running the app.
 */
public class HelpCommand extends Command {
    /**
     * Initializes the exiting Command.
     *
     * @param type the type of the Command which is bye.
     */
    public HelpCommand(Parser.Cmd type) {
        super(type);
    }

    /**
     * Displays the vlid commands
     * @param taskList the given taskList to run the function on.
     * @param ui
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        ui.informWrongInputFormat();
    }
}
