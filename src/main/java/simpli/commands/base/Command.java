package simpli.commands.base;

import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;

public interface Command {
    public CommandResult execute(java.lang.String[] tokens) throws CommandException, TaskException;
}
