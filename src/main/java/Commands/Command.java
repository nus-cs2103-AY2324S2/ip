package Commands;

import Exceptions.CommandException;
import Storage.TaskList;
import UI.UserInterface;

public interface Command {
    boolean execute(TaskList list, UserInterface ui);
}
