package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

/**
 * Represents a command to list and display all activities from an {@link ActivityList}.
 * Extends the {@link Command} class.
 */
public class ListActivity extends Command {

    /**
     * The activity list to be listed and displayed.
     */
    private ActivityList list;

    /**
     * Constructor to initialize the ListActivity command with the specified input.
     *
     * @param input The input string (not used in this case).
     */
    public ListActivity(String input) {
        super(input);
    }

    /**
     * Executes the command by setting the activity list to be listed and displayed.
     *
     * @param list The activity list on which the command should be executed.
     * @throws CommandException If an error occurs during execution (not expected for this command).
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        this.list = list;
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string listing and displaying all tasks in the specified activity list.
     */
    @Override
    public String toString() {
        String output = "";
        output += "Here are the tasks in your list:\n";
        output += list.printActivities() + "\n";
        return output;
    }
}
