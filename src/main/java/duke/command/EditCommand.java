package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class EditCommand extends Command {
    private String editType;

    private int index;

    public EditCommand(String editType, String n) {
        this.editType = editType;
        int index = Integer.parseInt(n) - 1;
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        if (editType.equals("mark")) {
            task.markAsDone();
            return ui.markedDone(task);
        } else {
            task.unmark();
            return ui.markedUndone(task);
        }
    }

    public boolean isExit() {
        return false;
    }
}
