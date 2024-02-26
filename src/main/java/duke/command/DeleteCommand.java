package duke.command;

import duke.*;
import duke.task.Task;

public class DeleteCommand extends Command{
    private int itemNoToDelete;
    public DeleteCommand(){
    }

    public DeleteCommand(int itemNoToDelete) {
        //this itemNo is what user sees
        //have to decrease by 1 to derive index no in taskList
        this.itemNoToDelete = itemNoToDelete;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int taskIndex = this.itemNoToDelete-1;
        Task taskToDelete = taskList.getItemFromListByIndex(taskIndex);
        taskList.removeItemAtIndex(taskIndex);
        storage.Store(taskList.toString());
        int count = taskList.getTaskList().size();
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:");
        sb.append("\n").append(taskToDelete.printOutput());
        sb.append("\n").append("Now you have "+count+" tasks in the list");
        ui.setCommandOutput(sb.toString());
    }
}
