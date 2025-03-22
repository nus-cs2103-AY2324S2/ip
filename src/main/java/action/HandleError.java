package action;

import exception.NarutoException;

/**
 * This class represents an action to handle an error by printing the error message.
 */
public class HandleError implements Action {
    private final NarutoException err;

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
     *
     * @return A string representing the result of the execution.
     */
    @Override
    public String execute() {
        assert err != null : "Error object cannot be null"; // Assertion
        return err.getMessage();
    }
}
