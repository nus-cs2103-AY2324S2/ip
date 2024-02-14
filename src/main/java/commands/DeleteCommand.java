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
    public String executeCommand() throws DukeException {
        if (i > taskList.getList().size() || i <= 0) {
            Ui.printVLine();
            String failedDeleteMessage = "Hmm...I can't delete something that isn't there :O";
            throw new DukeException(failedDeleteMessage);
        } else {
            Task t = taskList.remove(i-1);
            String successfulDeleteMessage = "Okay! The following task has been removed:\n" + t;
            printMessage(successfulDeleteMessage);
            return successfulDeleteMessage;
        }
    }

    private void printMessage(String successfulDeleteMessage) {
        Ui.printVLine();
        System.out.println(successfulDeleteMessage);
        Ui.printAllTask(taskList.getList());
    }
}
