package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;

public class ListCommand extends Command{
    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (taskList.size() == 0){
            throw new ToothlessException("Human list is empty like my tummy right now :/");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.showTask(taskList.getTask(i), i);
        }
        return false;
    }
}
