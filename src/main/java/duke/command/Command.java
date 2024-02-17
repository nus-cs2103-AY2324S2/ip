package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstact class for command.
 *
 */
public abstract class Command {
    private String out;
    private boolean isExit;
    /**
     * Constructor for Command.
     *
     * @param i exit status in int format.
     */
    public Command(int i) {
        if (i == 1) {
            isExit = true;
        } else {
            isExit = false;
        }
    }

    /**
     * Executes the command using TaskList,
     * Ui, and Storage given.
     *
     * @param tL TaskList for executing command.
     * @param ui Ui for printing string.
     * @param st Storage for saving/loading in file.
     */
    public abstract void execute(TaskList tL, Ui ui, Storage st);

    /**
     * Returns exit status in boolean format.
     *
     * @return boolean for exit status
     */
    public boolean isExit() {
        return isExit;
    }

    public void setOut(String s) {
        out = s;
    }
    public String getOut() {
        return out;
    }
}
