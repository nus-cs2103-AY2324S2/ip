package reacher.command;

import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;


/**
 * Command that when executed ends the program.
 */
public class ExitCommand extends Command{
    /**
     * Executes command by printing message.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Local file storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.print("Bye!");
    }

    /**
     * returns if this command is an exit command.
     */
    @Override
    public boolean isExit(){
        return true;
    }
    @Override
    public boolean equals(Object object){
        return object instanceof ExitCommand;
    }
}
