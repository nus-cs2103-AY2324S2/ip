package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        if (tasksList.getList().isEmpty()) {
            System.out.println("Your task list empty, add something today!");
        } else {
            ui.showTaskList(tasksList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
