package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

import java.util.List;

public class ListActivity extends Command {
    public ListActivity(String input) {
        super(input);
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        System.out.println(list.printActivities());
    }
}
