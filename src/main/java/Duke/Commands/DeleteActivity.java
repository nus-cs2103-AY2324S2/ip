package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

public class DeleteActivity extends Command{
    private final String INDEX;

    public DeleteActivity(String input) {
        super(input);
        INDEX = input;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        if (!isInteger(input)) {
            throw new CommandException("index to delete has to be an integer");
        } else {
            int deleteIndex = Integer.parseInt(INDEX);
            list.delete(deleteIndex);
        }
    }

    @Override
    public String toString() {
        String output = Dialog.printLine();
        output += "Got it. I've deleted activity number: " + INDEX + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
