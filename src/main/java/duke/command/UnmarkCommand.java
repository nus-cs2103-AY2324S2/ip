package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;

/**
 * Marks a task as undone upon execution
 */

public class UnmarkCommand extends Command {
    private final int TASK_NUM;

    /**
     * Constructs a UnmarkCommand class with given parameter
     * @param taskNum A string representation of task number
     */
    public UnmarkCommand(String taskNum) {
        this.TASK_NUM = Integer.parseInt(taskNum);
    }

    /**
     * Unmarks the task based on given input and display marked message
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    @Override
    public String execute(TaskList tasksList, TextUi ui, Storage storage)
            throws DukeException {
        if (TASK_NUM <= 0 | TASK_NUM > tasksList.getList().size()) {
            throw new DukeException("Invalid task number -.-!");
        }
        tasksList.unMark(TASK_NUM);
        Task unMarkedTask = tasksList.get(TASK_NUM);
        storage.saveToFile(tasksList.getList());
        return ui.showUnmarkTaskMessage(unMarkedTask);
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return a boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
