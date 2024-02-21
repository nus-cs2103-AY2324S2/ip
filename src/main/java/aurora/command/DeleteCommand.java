package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;

/**
 * The DeleteCommand class handles the "unmark" command.
 */
public class DeleteCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String[] splitCommands;

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param taskList      TaskList to edit.
     * @param storage       Storage to interact with.
     * @param splitCommands Full command input.
     */
    public DeleteCommand(TaskList taskList, Storage storage, String[] splitCommands) {
        this.taskList = taskList;
        this.storage = storage;
        this.splitCommands = splitCommands;
    }

    @Override
    public String handle() throws AuroraException {
        String message = "Command not executed.";
        validateDeleteCommand();

        int taskIndex = Integer.parseInt(splitCommands[1]);
        message = deleteTask(taskIndex);

        saveTasks();
        assert !message.equals("Command not executed.") : "Delete command not executed.";
        return message;
    }

    /**
     * Helper method that validates the input.
     * @throws AuroraException If the inputs are invalid.
     */
    private void validateDeleteCommand() throws AuroraException {
        if (this.splitCommands.length != 2) {
            throw new AuroraException(AuroraException.INVALID_DELETE_FORMAT);
        }
        if (!this.splitCommands[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new AuroraException("Please enter an integer as the second input.");
        }
        int taskNumber = Integer.parseInt(this.splitCommands[1]);
        if (taskNumber <= 0) {
            throw new AuroraException("Please enter an integer greater than 0 as the second input.");
        }
        if (taskNumber > this.taskList.getTaskList().size()) {
            throw new AuroraException("Please enter an integer representing a task within the list.");
        }
    }

    /**
     * Helper method that performs the deletion.
     *
     * @param taskIndex Index of task to be deleted
     * @return String that alerts the user that the task has been deleted.
     * @throws AuroraException If there is an error with deleting the task.
     */
    private String deleteTask(int taskIndex) throws AuroraException {
        return this.taskList.deleteTaskGui(taskIndex - 1);
    }

    /**
     * Helper method to save the task.
     *
     * @throws AuroraException If the taskList was not saved successfully.
     */
    private void saveTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("Unable to save edits: " + exception.getMessage());
        }
    }

}
