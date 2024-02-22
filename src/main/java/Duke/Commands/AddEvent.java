package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to add an Event activity to an {@link ActivityList}.
 * Extends the {@link Command} class and implements the {@link AddActivity} interface.
 */
public class AddEvent extends Command implements AddActivity {

    /**
     * The name of the Event activity.
     */
    private final String NAME;

    /**
     * The start date of the Event activity.
     */
    private final LocalDate STARTDATE;

    /**
     * The end date of the Event activity.
     */
    private final LocalDate ENDDATE;

    /**
     * Constructor to initialize the AddEvent command by parsing the input.
     *
     * @param input The input string containing the name, start date, and end date of the Event activity.
     * @throws CommandException If the input format is incorrect or if the start date is after the end date.
     */
    public AddEvent(String input) throws CommandException {
        super(input);
        String[] phrased = phrases(input);
        NAME = phrased[0];
        STARTDATE = DateFormat.format(phrased[1]);
        ENDDATE = DateFormat.format(phrased[2]);
    }

    /**
     * Parses the input string to extract the name, start date, and end date of the Event activity.
     *
     * @param input The input string containing the name, start date, and end date.
     * @return An array containing the name at index 0, start date at index 1, and end date at index 2.
     * @throws CommandException If the input format is incorrect.
     */
    public String[] phrases(String input) throws CommandException {
        String patternRegex = "^[^/]+ /from \\d{4}-\\d{1,2}-\\d{1,2} /to \\d{4}-\\d{1,2}-\\d{1,2}$";
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String[] substr = input.split(" /from ");
            String name = substr[0];
            String[] dateStr = substr[1].split(" /to ");
            String fromDate = dateStr[0];
            String toDate = dateStr[1];
            return new String[]{name, fromDate, toDate};
        } else {
            throw new CommandException("The input has to be in the format: \nname /from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }

    /**
     * Adds the Event activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the Event activity should be added.
     * @return The added Event activity.
     */
    @Override
    public Activity addToList(ActivityList list) {
        return list.add(NAME, STARTDATE, ENDDATE);
    }

    /**
     * Executes the command by adding the Event activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the Event activity should be added.
     * @throws CommandException If an error occurs during execution, such as the start date being after the end date.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        if (DateFormat.compareDate(STARTDATE, ENDDATE)) {
            addToList(list);
        } else {
            throw new CommandException("Start date is after end date");
        }
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the successful addition of the Event activity.
     */
    @Override
    public String toString() {
        String output = Dialog.printLine();
        output += "Got it. I've added this task: " + NAME + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
