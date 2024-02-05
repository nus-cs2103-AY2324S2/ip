package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

public class MarkCommand implements Command {
    protected int indexNo;
    protected boolean isMark;
    
    public MarkCommand(int indexNo, boolean isMark) {
        this.indexNo = indexNo;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isMark) {
            try {
                taskList.markTask(indexNo);
                ui.showMarkMessage(taskList.getTask(indexNo - 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                taskList.unmarkTask(indexNo);
                ui.showUnmarkMessage(taskList.getTask(indexNo - 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
