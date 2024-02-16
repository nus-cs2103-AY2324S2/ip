package reacher.command;

import reacher.ReacherException;
import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;

public abstract class Command {
    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ReacherException {
    }
}
