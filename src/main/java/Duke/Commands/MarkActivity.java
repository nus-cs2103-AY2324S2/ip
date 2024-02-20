package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

public class MarkActivity extends Command {
    private final String NAME;
    public MarkActivity(String input) {
        super(input);
        NAME = input;
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        int index = list.findExact(NAME);

        if (index == -1) {
            throw new CommandException("activity to be marked not found");
        } else {
            list.mark(index);
        }
    }
}
