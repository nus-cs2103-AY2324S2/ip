package commands;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class unmarkTaskCommand extends Command{
    private int index;
    public unmarkTaskCommand(int number) {
        this.index = number;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Please look carefully. This task is not inside the task list.");
            return false;
        }
        tasks.get(index).unmark(ui);
        return true;
    }
}



