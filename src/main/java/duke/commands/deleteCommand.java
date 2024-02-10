package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class deleteCommand extends Command{
    private int index;

    public deleteCommand(int number) {
        this.index = number;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Please look carefully. This task is not inside the task list.");
            return false;
        }
        String taskStr = tasks.get(index).toString();
        tasks.deleteTask(this.index);
        ui.showLine();
        System.out.println("Noted. I've removed this task:\n  " + taskStr);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        return true;
    }
}
