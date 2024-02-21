package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

import java.util.List;

public abstract class Command {
    final String input;

    Command(String input) {
        this.input = input;
    }

    public abstract void execute(ActivityList list) throws CommandException;

    @Override
    public abstract String toString();
}
