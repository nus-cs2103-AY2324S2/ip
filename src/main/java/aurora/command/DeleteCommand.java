package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;

/** The DeleteCommand class represents the "delete" command. */
public class DeleteCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String[] splitCommands;

    /**
     * Constructs a DeleteCommand object.
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
     * Validates the delete command input by checking if it is following the format:
     * delete {integer representing the index of the task to be deleted}
     *
     * @throws AuroraException If the delete command is invalid.
     */
    private void validateDeleteCommand() throws AuroraException {
        if (this.splitCommands.length != 2) {
            throw new AuroraException(AuroraException.INVALID_DELETE_FORMAT);
        }
        // Solution adapted from https://www.baeldung.com/java-check-string-number
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
     * Deletes the task specified by the taskIndex from the taskList.
     * Returns a String that alerts the user that the task specified has been deleted.
     *
     * @param taskIndex Index of task to be deleted.
     * @return String that alerts the user that the task has been deleted.
     * @throws AuroraException If there is an error while the task is being deleted.
     */
    private String deleteTask(int taskIndex) throws AuroraException {
        return this.taskList.deleteTaskGui(taskIndex - 1);
    }

    /**
     * Saves the task list after deletion to the storage file.
     *
     * @throws AuroraException If an error occurs while saving the task list to the storage file.
     */
    private void saveTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save deadline to file: " + exception.getMessage());
        }
    }

}
