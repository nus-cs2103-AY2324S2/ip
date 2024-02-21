package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.util.List;

public class ListActivity extends Command {
    private ActivityList list;
    public ListActivity(String input) {
        super(input);
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        this.list = list;
    }

    public String toString() {
        String output = Dialog.printLine();
        output += "Here are the tasks in your list:\n";
        output += list.printActivities() + "\n";
        output += Dialog.printLine();
        return output;
    }
}
