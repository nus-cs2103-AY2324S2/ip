package chingu.command;

import chingu.Storage;
import chingu.task.Task;
import chingu.task.TaskList;
import chingu.Ui;


/**
 * Class that deals with DeleteCommand by the user
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for the DeleteCommand
     *
     * @param n - index of the task to be deleted
     */
    public DeleteCommand(String n) {
        int index = Integer.parseInt(n) - 1;
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by the user
     *
     * @param tasks that contains current list of tasks
     * @param ui that handles the Ui of the Chingu Program
     * @param storage that deals with loading or saving the file
     * @return the response of the Chingu program from the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.tasks.remove(index);
        String Response = "Noted. I've removed this task:\n";
        Response = Response + removed.toString() + "\n" +
                tasks.getSize();
        return Response;
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
