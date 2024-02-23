package commands;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Marks tasks as undone command.
 */
public class UnmarkTaskCommand extends AbstractCommand {
    private int index;
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.unmarkTask(this.index);
            storage.saveTasks(taskList);
            return new UserCommand("\tI have unmarked this task", "\t" + taskList.getTask(this.index - 1));
        } catch (DukeException e) {
            System.out.println("\tList index out of range");
            return new UserCommand("\tList index out of range");
        }
    }
}
