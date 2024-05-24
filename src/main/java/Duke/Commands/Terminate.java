package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

/**
 * Represents a command to terminate the application and bid farewell to the user.
 * Extends the {@link Command} class.
 */
public class Terminate extends Command {

    /**
     * Constructor to initialize the Terminate command with the specified input.
     *
     * @param input The input string (not used in this case).
     */
    public Terminate(String input) {
        super(input);
    }

    /**
     * Executes the command by terminating the application (no specific action in this case).
     *
     * @param list The activity list (not used in this case).
     * @throws CommandException If an error occurs during execution (not expected for this command).
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        // No specific action for termination
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A farewell message to the user.
     */
    @Override
    public String toString() {
        return Dialog.fairwellUser();
    }
}
