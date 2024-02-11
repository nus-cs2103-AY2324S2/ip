package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Abstract class representing a handler of a specific command.
 */
public abstract class Handler {

    protected final String args;

    /** Class constructor. */
    public Handler(String args) {
        this.args = args;
    }

    /**
     * Executes an action based on the input command.
     * <p>
     * May modify tasks and interact with the user through the {@code TaskList}
     * and {@code Ui} arguments.
     *
     * @param tasks           a {@code TaskList} object
     * @param ui              a {@code Ui} object
     * @throws EarlException  if the user's command is incomprehensible
     */
    public abstract void handle(TaskList tasks, Ui ui) throws EarlException;
}
