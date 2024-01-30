package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;

public class FindCommand extends Command {
    private final String DETAILS;

    public FindCommand(String details) {
        this.DETAILS = details;
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        ui.showFindMessage();
        for (Task task: tasksList.getList()) {
            if (task.toString().contains(this.DETAILS)) {
                System.out.println(task);
            }
        }
        ui.showBorder();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
