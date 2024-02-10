package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command which can be run.
 */
public abstract class Command {
    protected final Parser.Cmd type;

    /**
     * Constructor of the Command class
     * @param type type of the Command created.
     */
    protected Command(Parser.Cmd type) {
        this.type = type;
    }

    /**
     * Runs the function on the given taskList which the command represents
     * @param taskList the given taskList to run the function on.
     */
    public abstract void run(TaskList taskList, Ui ui);

    /**
     * Returns the type of the Command
     * @return the type of the Command.
     */
    public Parser.Cmd getType() {
        return this.type;
    }
}
