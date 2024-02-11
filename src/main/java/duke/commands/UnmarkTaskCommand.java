package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnmarkTaskCommand extends Command{
    private int index;
    public UnmarkTaskCommand(int number) {
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



