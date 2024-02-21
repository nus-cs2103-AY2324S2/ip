package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Activities.Todo;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.util.ArrayList;
import java.util.List;

public class AddTodo extends Command implements AddActivity {
    private final String NAME;
    public AddTodo(String input) {
        super(input);
        NAME = phrases(input);
    }

    public String phrases(String input) {
        return input;
    }

    @Override
    public Activity addToList(ActivityList list) {
        return list.add(NAME);
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        if (NAME.isEmpty()) {
            throw new CommandException("The activity name of a todo cannot be empty.");
        } else {
            addToList(list);
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
