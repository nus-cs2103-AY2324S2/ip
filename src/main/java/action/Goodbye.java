package action;

/**
 * Represents an action that prints a goodbye message and exits the program.
 */
public class Goodbye implements Action {
    /**
     * Constructs a Goodbye object.
     */
    public Goodbye() {
    }

    /**
     * Executes the action by printing a goodbye message and exiting the program.
     *
     * @return A string representing the result of the execution.
     */
    @Override
    public String execute() {
        return "Bye bye! ヾ( ˃ᴗ˂ )◞ • *✰";
    }
}
