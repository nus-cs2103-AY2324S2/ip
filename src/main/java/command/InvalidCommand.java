package command;

import exception.BluException;
import exception.InvalidCommandException;
import storage.Storage;
import task.TaskList;
import ui.UI;

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
