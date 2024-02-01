package taskList.commands;

import taskList.Storage;
import taskList.TaskList;
import taskList.Ui;

public class HelpCommand implements Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }
    
}