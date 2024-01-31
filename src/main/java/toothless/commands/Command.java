package toothless.commands;

import toothless.Ui;
import toothless.TaskList;
import toothless.Storage;
import toothless.ToothlessException;

public abstract class Command {
    public abstract boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException;

    public int getTaskIndex(String detail) throws ToothlessException{
        try {
            int taskIndex = Integer.valueOf(detail);
            return taskIndex - 1;
        } catch (NumberFormatException e){
            throw new toothless.ToothlessException("Number put is not number.\nPlease put real number ._.");
        }
    }
}
