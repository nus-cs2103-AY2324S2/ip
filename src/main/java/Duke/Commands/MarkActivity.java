package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
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
    public String toString() {
        String output = Dialog.printLine();
        output += "Nice! I've marked this task as done: " + NAME + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
