package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

import java.util.List;

public class FindActivity extends Command{
    private final String SUBSTR;
    public FindActivity(String input) {
        super(input);
        SUBSTR = input;
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        if (SUBSTR.isEmpty()) {
            throw new CommandException("empty search can't be done");
        } else {
            list.find(SUBSTR);
        }
    }

}
