package commands;

import storage.TaskList;
import ui.UserInterface;

public interface Command {
    boolean execute(TaskList list, UserInterface ui);
}
