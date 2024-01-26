package commands;

import commands.Command;
import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Marks tasks as undone command.
 */
public class UnmarkTaskCommand extends Command {
    int index;
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.unmarkTask(this.index);
            storage.saveTasks(taskList);
        } catch (DukeException e) {
            System.out.println("\tList index out of range");
        }
    }
}
