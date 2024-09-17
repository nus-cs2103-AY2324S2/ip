package duke.command;

import duke.Storage;
import duke.task.TaskList;

/** Command to execute sort instruction. */
public class SortCommand extends Command {

    /**
     * Executes the command, saves to storage and display parting message.
     *
     * @param list TaskList to be saved.
     * @param storage To update storage with updated list.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        list.sort();
        storage.save(list);
        super.setResponse("List has been sorted by dates!\n" + list);
    }
}
