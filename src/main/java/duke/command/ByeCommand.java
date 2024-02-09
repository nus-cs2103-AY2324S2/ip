package duke.command;

import duke.Storage;
import duke.task.TaskList;

/** Command to execute bye instruction. */
public class ByeCommand extends Command {

    /**
     * Executes the command, saves to storage and display parting message.
     *
     * @param list TaskList to be saved.
     * @param storage To update storage with updated list.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        storage.save(list);
        super.setResponse("Bye bye! Thanks for using me! You are shrek-elicious "
                + "I will remember the tasks you have to do!");
    }
}
