package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

/**
 * Represents a command to unmark an activity as not done in an {@link ActivityList}.
 * Extends the {@link Command} class.
 */
public class UnmarkActivity extends Command {

    /**
     * The name of the activity to be unmarked.
     */
    private final String NAME;

    /**
     * Constructor to initialize the UnmarkActivity command with the specified input.
     *
     * @param input The input string containing the name of the activity to be unmarked.
     */
    public UnmarkActivity(String input) {
        super(input);
        NAME = input;
    }

    /**
     * Executes the command by unmarking the specified activity as not done in the {@link ActivityList}.
     *
     * @param list The activity list on which the command should be executed.
     * @throws CommandException If an error occurs during execution, such as the activity not being found.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        int index = list.findExact(NAME);

        if (index == -1) {
            throw new CommandException("Activity to be unmarked not found.");
        } else {
            list.unmark(index);
        }
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the successful unmarking of the specified activity as not done.
     */
    @Override
    public String toString() {
        String output = "";
        output += "I've unmarked this task as not done: " + NAME + ".\n";
        return output;
    }
}
