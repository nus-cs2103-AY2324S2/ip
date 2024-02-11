package action;

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
     *
     * @return A string representing the result of the execution.
     */
    @Override
    public String execute() {
        return "Hello! I'm Naruto, and I'm training to become a ninja!"
                + "\nBelieve it!\n Enter `help` if you need a guide on the commands to use.";
    }
}
