package commands;

import exceptions.DukeException;
import task.Task;
import ui.Ui;

/**
 * Encapsulates a delete command.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND = "delete";
    private int i;

    public DeleteCommand (int i) {
        this.i = i;
    }

    /**
     * Deletes the particular task from TaskList based on the given index.
     *
     * @return
     * @throws DukeException Throws an exception when index is out of range.
     */
    @Override
    public String execute() throws DukeException {
        if (i > taskList.getList().size() || i <= 0) {
            Ui.printVLine();
            throw new DukeException("Hmm...I can't delete something that isn't there :O");
        } else {
            Task t = taskList.remove(i-1);
            Ui.printVLine();
            System.out.println("Okay! The following task has been removed:\n" + t);
            Ui.printAllTask(taskList.getList());
            return "Okay! The following task has been removed:\n" + t;
        }
    }
}
