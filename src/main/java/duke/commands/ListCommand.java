package duke.commands;

import duke.tasks.TaskList;
import duke.core.Ui;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        Ui.printMessage(this.taskList.toString());
    }
}
