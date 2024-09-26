package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;

/** The UnmarkCommand class represents the "unmark" command.*/
public class UnmarkCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String[] splitCommands;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param taskList      TaskList to edit.
     * @param storage       Storage to interact with.
     * @param splitCommands Full command input.
     */
    public UnmarkCommand(TaskList taskList, Storage storage, String[] splitCommands) {
        this.taskList = taskList;
        this.storage = storage;
        this.splitCommands = splitCommands;
    }

    @Override
    public String handle() throws AuroraException {
        int taskIndex = validateAndParseTaskIndexForUnmark();
        String message = unmarkTaskAndSave(taskIndex);
        assert !message.equals("Command not executed.") : "Unmark command not executed.";
        return message;
    }

    /**
     * Returns an integer representing the index of the task to be unmarked.
     * Validates that the unmark command input format follows the below convention:
     * unmark {integer within the task list}
     *
     * @return Index of the task to be unmarked.
     * @throws AuroraException If the unmark command is of incorrect format.
     */
    private int validateAndParseTaskIndexForUnmark() throws AuroraException {
        if (this.splitCommands.length != 2) {
            throw new AuroraException(AuroraException.INVALID_UNMARK_FORMAT);
        }
        if (!this.splitCommands[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new AuroraException("Please enter an integer as the second input.");
        }

        int taskIndex = Integer.parseInt(this.splitCommands[1]);
        if (taskIndex <= 0) {
            throw new AuroraException("Please enter an integer greater than 0 as the second input.");
        }
        if (taskIndex > this.taskList.getTaskList().size()) {
            throw new AuroraException("Please enter an integer representing a task within the list.");
        }
        if (!this.taskList.getTaskList().get(taskIndex - 1).getStatus()) {
            throw new AuroraException("Task already unmarked.");
        }

        return taskIndex;
    }

    /**
     * Returns a String that alerts the user that the required task has been unmarked.
     * Saves the task list to the storage file after unmarking the task.
     *
     * @param taskIndex Index of the task to be unmarked.
     * @return Returns a String that alerts the user that the required task has been unmarked and the task list
     *         saved to the storage file.
     * @throws AuroraException If an error occurs during unmarking of the task.
     */
    private String unmarkTaskAndSave(int taskIndex) throws AuroraException {
        String message;
        try {
            message = this.taskList.unmarkTaskGui(taskIndex - 1);
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            message = "I'm unable to save edits: " + exception.getMessage();
        }
        return message;
    }

}
