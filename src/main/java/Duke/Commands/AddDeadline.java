package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to add a Deadline activity to an {@link ActivityList}.
 * Extends the {@link Command} class and implements the {@link AddActivity} interface.
 */
public class AddDeadline extends Command implements AddActivity {

    /**
     * The name of the Deadline activity.
     */
    private final String NAME;

    /**
     * The due date of the Deadline activity.
     */
    private final LocalDate DATE;

    /**
     * Constructor to initialize the AddDeadline command by parsing the input.
     *
     * @param input The input string containing the name and due date of the Deadline activity.
     * @throws CommandException If the input format is incorrect.
     */
    public AddDeadline(String input) throws CommandException {
        super(input);
        String[] phrased = phrases(input);
        NAME = phrased[0];
        DATE = DateFormat.format(phrased[1]);
    }

    /**
     * Parses the input string to extract the name and due date of the Deadline activity.
     *
     * @param input The input string containing the name and due date.
     * @return An array containing the name at index 0 and due date at index 1.
     * @throws CommandException If the input format is incorrect.
     */
    public String[] phrases(String input) throws CommandException {
        String patternRegex = "^[^/]+ /by \\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new CommandException("The input has to be in the format: \nname /by yyyy-mm-dd");
        }
        return input.split(" /by ");
    }

    /**
     * Adds the Deadline activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the Deadline activity should be added.
     * @return The added Deadline activity.
     */
    @Override
    public Activity addToList(ActivityList list) {
        return list.add(NAME, DATE);
    }

    /**
     * Executes the command by adding the Deadline activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the Deadline activity should be added.
     * @throws CommandException If an error occurs during execution.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        addToList(list);
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the successful addition of the Deadline activity.
     */
    @Override
    public String toString() {
        String output = Dialog.printLine();
        output += "Got it. I've added this task: " + NAME + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
