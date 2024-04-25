package Tim.commands;

import Tim.exception.TimException;
import Tim.storage.TaskList;

import java.io.IOException;

/**
 * Represents a command that can be executed
 */
public abstract class Command {

    public abstract String execute(TaskList taskList) throws TimException;
}
