package chingu.command;

import chingu.Storage;
import chingu.task.Task;
import chingu.task.TaskList;
import chingu.Ui;

/**
 * Class that deals with EditCommand by the user
 */
public class EditCommand extends Command {
    private String editType;

    private int index;

    /**
     * Constructor for the EditCommand
     *
     * @param editType type of EditCommand (Mark/Unmark)
     * @param n - index of the task to be edited
     */
    public EditCommand(String editType, String n) {
        this.editType = editType;
        int index = Integer.parseInt(n) - 1;
        this.index = index;
    }

    /**
     * Executes the EditCommand by the user
     *
     * @param tasks that contains current list of tasks
     * @param ui that handles the Ui of the Chingu Program
     * @param storage that deals with loading or saving the file
     * @return the response of the Chingu program from the command
     */
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

    /**
     * Checks if it is last command of the Program (Exit Command - "bye")
     *
     * @return boolean that indicate if it is the last command from user
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
