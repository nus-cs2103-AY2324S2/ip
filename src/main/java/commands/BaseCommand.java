package commands;

import exceptions.NumeratorException;

public abstract class BaseCommand extends NumeratorException {
    public BaseCommand(String message) {
        super(message);
    }

    public abstract void execute();
}
