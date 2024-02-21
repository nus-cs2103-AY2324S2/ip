package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

public class Unknow extends Command{
    private final String ERROR_MESSAGE;

    public Unknow(String errorMessage) {
        super(errorMessage);
        ERROR_MESSAGE = errorMessage;
    }

    @Override
    public void execute(ActivityList list) throws CommandException {

    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
