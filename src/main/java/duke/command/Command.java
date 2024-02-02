package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    private boolean isExit;
    public Command(int i){
        if(i == 1) {
            isExit = true;
        } else {
            isExit = false;
        }
    }
    public abstract void execute(TaskList tL, Ui ui, Storage st);

    public boolean isExit(){
        return isExit;
    }
}
