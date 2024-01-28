package action;

import util.PrintUtil;

/**
 * Represents an action to greet the user.
 * Implements the Action interface.
 */
public class Greet implements Action {
    /**
     * Constructs a Greet object.
     */
    public Greet() {
    }

    /**
     * Executes the greet action by printing a greeting message.
     */
    @Override
    public void execute() {
        PrintUtil.print("Hello! I'm Naruto, and I'm training to become a ninja!"
                + "\nBelieve it!");
    }
}
