package reacher.command;

import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.print("Bye!");
    }
    @Override
    public boolean isExit(){
        return true;
    }
    @Override
    public boolean equals(Object object){
        return object instanceof ExitCommand;
    }
}
