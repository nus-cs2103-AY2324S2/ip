package chingu.command;

import chingu.Storage;
import chingu.task.Task;
import chingu.task.TaskList;
import chingu.Ui;

public class EditCommand extends Command {
    private String editType;

    private int index;

    public EditCommand(String editType, String n) {
        this.editType = editType;
        int index = Integer.parseInt(n) - 1;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(index >= tasks.getSizeNumber());
        assert (index >= 0 && index < tasks.getSizeNumber()) : "this task doesn't exist!";
        Task task = tasks.getTask(index);
        if (editType.equals("mark")) {
            task.markAsDone();
            return ui.markedDone(task);
        } else {
            task.unmark();
            return ui.markedUndone(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
