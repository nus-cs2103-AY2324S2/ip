package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;

public class ListCommand extends Command {
    /**
     * List all the tasks in the task list
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        if (tasksList.getList().isEmpty()) {
            System.out.println("Your task list empty, add something today!");
        } else {
            ui.showTaskList(tasksList);
        }
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
