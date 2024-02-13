package drew.command;

import drew.storage.Storage;
import drew.storage.TaskList;

public abstract class Command {
    protected String input;

    protected Command(String input) {
        this.input = input;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws IllegalArgumentException;
}
