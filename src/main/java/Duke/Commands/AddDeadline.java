package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadline extends Command implements AddActivity{
    private final String NAME;
    private final LocalDate DATE;
    public AddDeadline(String input) {
        super(input);
        String[] phrased = phrases(input);
        NAME = phrased[0];
        DATE = DateFormat.format(phrased[1]);
    }

    public String[] phrases(String input) {
        return input.split(" /by ");
    }

    @Override
    public Activity addToList(ActivityList list) {
        return list.add(NAME, DATE);
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        String patternRegex = "^[^/]+ /by \\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new CommandException("The input has to be in prompt name /by yyyy-mm-dd");
        } else {
            addToList(list);
        }
    }
}
