package ganAnWo.command;

import ganAnWo.Storage;
import ganAnWo.TaskList;
import ganAnWo.Ui;

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
     * @return boolean for exit status.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * A setter for out.
     *
     * @param s string to be set to out.
     */
    public void setOut(String s) {
        out = s;
    }

    /**
     * A getter for out.
     *
     * @return string represent out.
     */
    public String getOut() {
        return out;
    }
}
