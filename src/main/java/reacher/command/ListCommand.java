package reacher.command;

import reacher.Storage;
import reacher.TaskList;

/**
 * Command that shows user all tasks in list.
 */
public class ListCommand extends Command {
    /**
     * Execute command by returning String of list for output.
     *
     * @param tasks   List of tasks.
     * @param storage Local file storage.
     */
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        return tasks.toString();

    }
    @Override
    public boolean equals(Object object){
        return object instanceof ListCommand;
    }
}
