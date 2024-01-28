package action;

import exception.NarutoException;
import util.PrintUtil;

/**
 * This class represents an action to handle an error by printing the error message.
 */
public class HandleError implements Action {
    private NarutoException err;

    /**
     * Constructs a new HandleError object with the specified error.
     *
     * @param err the error to be handled
     */
    public HandleError(NarutoException err) {
        this.err = err;
    }

    /**
     * Executes the action by printing the error message.
     */
    @Override
    public void execute() {
        PrintUtil.print(this.err.getMessage());
    }
}
