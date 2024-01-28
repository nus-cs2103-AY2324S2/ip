package action;

import util.PrintUtil;

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
     */
    @Override
    public void execute() {
        PrintUtil.print("Bye bye! ヾ( ˃ᴗ˂ )◞ • *✰");
        System.exit(0);
    }
}
