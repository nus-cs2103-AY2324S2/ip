package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

/**
 * Represents a command to delete an activity from an {@link ActivityList} based on its index.
 * Extends the {@link Command} class.
 */
public class DeleteActivity extends Command {

    /**
     * The index of the activity to be deleted.
     */
    private final String INDEX;

    /**
     * Constructor to initialize the DeleteActivity command with the specified input.
     *
     * @param input The input string containing the index of the activity to be deleted.
     */
    public DeleteActivity(String input) {
        super(input);
        INDEX = input;
    }

    /**
     * Checks if a given string represents an integer.
     *
     * @param s The string to be checked.
     * @return True if the string represents an integer, false otherwise.
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Executes the command by deleting the activity from the specified {@link ActivityList}.
     *
     * @param list The activity list from which the activity should be deleted.
     * @throws CommandException If an error occurs during execution, such as the input not being a valid integer.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        if (!isInteger(input)) {
            throw new CommandException("Index to delete has to be an integer.");
        } else {
            int deleteIndex = Integer.parseInt(INDEX);
            list.delete(deleteIndex);
        }
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the successful deletion of the activity at the specified index.
     */
    @Override
    public String toString() {
        String output = "";
        output += "Got it. I've deleted activity number: " + INDEX + ".\n";
        return output;
    }
}
