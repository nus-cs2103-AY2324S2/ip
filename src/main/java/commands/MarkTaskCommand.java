package commands;


import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Marks task as done command.
 */
public class MarkTaskCommand extends AbstractCommand {
    private int index;
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.markTask(this.index);
            storage.saveTasks(taskList);
            return new UserCommand("\tI have marked this task as done", "\t" + taskList.getTask(this.index - 1));
        } catch (DukeException e) {
            System.out.println("\tList index out of range");
            return new UserCommand("\tList index out of range");
        }
    }
}
