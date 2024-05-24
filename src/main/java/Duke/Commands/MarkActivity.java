package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

/**
 * Represents a command to mark an activity as done in an {@link ActivityList}.
 * Extends the {@link Command} class.
 */
public class MarkActivity extends Command {

    /**
     * The name of the activity to be marked as done.
     */
    private final String NAME;

    /**
     * Constructor to initialize the MarkActivity command with the specified input.
     *
     * @param input The input string containing the name of the activity to be marked.
     */
    public MarkActivity(String input) {
        super(input);
        NAME = input;
    }

    /**
     * Executes the command by marking the specified activity as done in the {@link ActivityList}.
     *
     * @param list The activity list on which the command should be executed.
     * @throws CommandException If an error occurs during execution, such as the activity not being found.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        int index = list.findExact(NAME);

        if (index == -1) {
            throw new CommandException("Activity to be marked not found.");
        } else {
            list.mark(index);
        }
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the successful marking of the specified activity as done.
     */
    @Override
    public String toString() {
        String output = "";
        output += "Nice! I've marked this task as done: " + NAME + ".\n";
        return output;
    }
}
