package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(String input, int taskNum) {
        super(input);
        this.taskNum = taskNum;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        Task[] taskArr = tasks.getTasks();
        int counter = tasks.getCounter();

        if (taskNum >= 1 && taskNum <= counter) {
            String deleteMsg = ui.showRemoveTaskMessage(taskArr[taskNum - 1], counter);
            tasks.deleteTask(taskNum);

            return deleteMsg;
        } else {
            throw new DukeException("UH OH! Invalid task number, please provide a valid task number!");
        }
    }
}
