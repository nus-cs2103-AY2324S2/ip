package detective.command;

import detective.DukeException;
import detective.TaskList;
import detective.Ui;
import detective.task.Task;

public class DeleteCommand extends Command {
    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task needDelete;
        try {
            needDelete = taskList.get(num);
            taskList.remove(num);
            ui.showDeleteTask(taskList, needDelete);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! I haven't record this detective.task!");
        }
    }
}
