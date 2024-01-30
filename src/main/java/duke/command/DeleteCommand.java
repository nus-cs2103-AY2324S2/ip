package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int TASK_NUM;
    /**
     * Creates a DeleteCommand class with num parameter
     * @param num A string representation of integer
     */
    public DeleteCommand(String num) {
        this.TASK_NUM = Integer.parseInt(num);
    }

    /**
     * Deletes tasks from task list, generates ui message and then save changes to file
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        Task deletedTask = tasksList.get(TASK_NUM);
        tasksList.deleteFromTaskList(TASK_NUM);
        ui.showDeleteTaskMessage(deletedTask);
        storage.saveToFile(tasksList.getList());
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
