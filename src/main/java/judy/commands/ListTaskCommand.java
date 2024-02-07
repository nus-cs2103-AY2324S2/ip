package judy.commands;

import judy.storage.Storage;
import judy.type.TaskList;
import judy.ui.Ui;

public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final TaskList taskList;

    public ListTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showTaskList(this.taskList);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
