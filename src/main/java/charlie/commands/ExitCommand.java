package charlie.commands;

import charlie.storage.Storage;
import charlie.storage.TaskList;

public class ExitCommand extends Command {

    /**
     * turns isExit to true, which effectively terminates the program
     * @param taskList - task list loaded at the start of the program.
     * @param storage  - class responsible for adding and loading tasks from and into the file
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        isExit = true;
        return "See you later!";
    }
}
