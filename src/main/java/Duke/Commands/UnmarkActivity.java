package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;


public class UnmarkActivity extends Command {
    private final String NAME;
    public UnmarkActivity(String input) {
        super(input);
        NAME = input;
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        int index = list.findExact(NAME);

        if (index == -1) {
            throw new CommandException("activity to be marked not found");
        } else {
            list.unmark(index);
        }
    }

    public String toString() {
        String output = Dialog.printLine();
        output += "I've unmarked this task as not done: " + NAME + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
