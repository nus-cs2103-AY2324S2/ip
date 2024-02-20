package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.util.List;

public class Terminate extends Command {
    public Terminate(String input) {
        super(input);
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        Dialog.fairwellUser();
        System.exit(0);
    }

}
