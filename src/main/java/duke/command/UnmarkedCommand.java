package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;

class UnmarkCommand extends Command {
    private final int TASK_NUM;

    public UnmarkCommand(String taskNum) {
        this.TASK_NUM = Integer.parseInt(taskNum);
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) throws DukeException {
        if (TASK_NUM <= 0 | TASK_NUM > tasksList.getList().size()) {
            throw new DukeException("Invalid task number -.-!");
        }
        tasksList.unMark(TASK_NUM);
        Task unMarkedTask = tasksList.get(TASK_NUM);
        ui.showUnmarkTaskMessage(unMarkedTask);
        storage.saveToFile(tasksList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}