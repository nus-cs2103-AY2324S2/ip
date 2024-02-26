package reacher.command;

import reacher.Storage;
import reacher.TaskList;

/**
 * Command that when executed ends the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes command by returning message for output.
     *
     * @param tasks   List of tasks.
     * @param storage Local file storage.
     */
    @Override
    public String execute(String input, TaskList tasks, Storage storage){
        return ("Bye!");
    }

    @Override
    public boolean equals(Object object){
        return object instanceof ExitCommand;
    }
}
