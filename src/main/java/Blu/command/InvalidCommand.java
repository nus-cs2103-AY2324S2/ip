package Blu.command;

import Blu.exception.BluException;
import Blu.exception.InvalidCommandException;
import Blu.storage.Storage;
import Blu.task.TaskList;
import Blu.ui.UI;

public class InvalidCommand extends Command {
    private final String invalidCmd;

    public InvalidCommand(String invalidCmd) {
        this.invalidCmd = invalidCmd;
    }
    
     @Override 
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        throw new InvalidCommandException(this.invalidCmd);
    }
}
