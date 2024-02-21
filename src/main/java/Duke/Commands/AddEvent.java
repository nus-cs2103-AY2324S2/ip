package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEvent extends Command implements AddActivity{
    private final String NAME;
    private final LocalDate STARTDATE;
    private final LocalDate ENDDATE;
    public AddEvent(String input) throws CommandException {
        super(input);
        String[] phrased = phrases(input);
        NAME = phrased[0];
        STARTDATE = DateFormat.format(phrased[1]);
        ENDDATE = DateFormat.format(phrased[2]);
    }
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
            throw new CommandException("The input has to be in prompt: \nname /from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }

    @Override
    public Activity addToList(ActivityList list) {
        return list.add(NAME, STARTDATE, ENDDATE);
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        if (DateFormat.compareDate(STARTDATE, ENDDATE)) {
            addToList(list);
        } else {
            throw new CommandException("Start date is after end date");
        }
    }

    @Override
    public String toString() {
        String output = Dialog.printLine();
        output += "Got it. I've added this task: " + NAME + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
