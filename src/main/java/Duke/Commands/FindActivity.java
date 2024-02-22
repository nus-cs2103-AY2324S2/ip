package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.util.ArrayList;

/**
 * Represents a command to find and display activities from an {@link ActivityList} based on a substring.
 * Extends the {@link Command} class.
 */
public class FindActivity extends Command {

    /**
     * The substring to search for in activity names.
     */
    private final String SUBSTR;

    /**
     * A list to store the found activities.
     */
    private ArrayList<Activity> foundList;

    /**
     * Constructor to initialize the FindActivity command with the specified input.
     *
     * @param input The input string containing the substring to search for.
     */
    public FindActivity(String input) {
        super(input);
        SUBSTR = input;
    }

    /**
     * Executes the command by finding and storing activities from the specified {@link ActivityList} based on a substring.
     *
     * @param list The activity list on which the command should be executed.
     * @throws CommandException If an error occurs during execution, such as an empty search string.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        if (SUBSTR.isEmpty()) {
            throw new CommandException("Empty search can't be done.");
        } else {
            foundList = list.find(SUBSTR);
        }
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the matching activities found or no matching activities.
     */
    @Override
    public String toString() {
        String output = Dialog.printLine();
        if (foundList.isEmpty()) {
            output += "No matching activities found\n";
        } else {
            output += "Here are the matching activities in your list:\n";
            for (int i = 0; i < foundList.size(); i++) {
                output += foundList.get(i).toString() + "\n";
            }
        }
        output += Dialog.printLine();
        return output;
    }
}
