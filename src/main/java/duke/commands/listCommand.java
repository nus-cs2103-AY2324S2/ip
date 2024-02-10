package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;


public class listCommand extends Command{
    public listCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        tasks.displayTasks();
        ui.showLine();
        return true;
    }
}
