package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class markTaskCommand extends Command{
    private int index;

    public markTaskCommand(int number) {
        this.index = number;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Please look carefully. This task is not inside the task list.");
            return false;
        }
        tasks.get(index).markDone(ui);
        return true;
    }
}
