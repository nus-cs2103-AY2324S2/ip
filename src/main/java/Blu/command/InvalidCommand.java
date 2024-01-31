package blu.command;

import blu.exception.BluException;
import blu.exception.InvalidCommandException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

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
