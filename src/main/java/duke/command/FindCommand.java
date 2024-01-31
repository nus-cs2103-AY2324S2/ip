package duke.command;

import duke.exception.FindInvalidException;
import duke.task.TaskList;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;

/**
 * The class representing the find command.
 * */
public class FindCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    String[] commandList;

    public FindCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws FindInvalidException {
        if (this.commandList.length <= 1) {
            throw new FindInvalidException();
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getDescription().contains(commandList[1])) {
                System.out.println(i + 1 + "." + currentTask);
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
