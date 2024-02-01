package taskList.commands;

import taskList.Storage;
import taskList.TaskList;
import taskList.Ui;

public class MarkCommand implements Command{

    protected int indexNo;
    protected boolean isMark;

    public MarkCommand(int indexNo, boolean isMark) {
        this.indexNo = indexNo;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isMark) {
            taskList.markTask(indexNo); 
            ui.showMarkMessage(taskList.getTask(indexNo-1));
        } else {
            taskList.unmarkTask(indexNo);
            ui.showUnmarkMessage(taskList.getTask(indexNo-1));
        }
    }
    
}