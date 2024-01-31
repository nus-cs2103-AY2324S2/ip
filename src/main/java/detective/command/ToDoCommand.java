package detective.command;

import detective.DukeException;
import detective.TaskList;
import detective.Ui;
import detective.task.ToDo;

public class ToDoCommand extends Command {
    private ToDo task;

    public ToDoCommand(ToDo task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        ui.showAddTask(taskList, task);
    }
}
