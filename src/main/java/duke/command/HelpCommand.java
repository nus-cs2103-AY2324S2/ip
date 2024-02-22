package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command{
    /**
     * Initializes the exiting Command.
     *
     * @param type the type of the Command which is bye.
     */
    public HelpCommand(Parser.Cmd type) {
        super(type);
    }

    /**
     * Ends the program.
     *
     * @param taskList the given taskList to run the function on.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        ui.informWrongInputFormat();
    }
}
