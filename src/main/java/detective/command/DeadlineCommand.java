package detective.command;

import detective.DukeException;
import detective.TaskList;
import detective.Ui;
import detective.task.Deadline;

public class DeadlineCommand extends Command {
    private Deadline task;

    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        ui.showAddTask(taskList, task);
    }
}
