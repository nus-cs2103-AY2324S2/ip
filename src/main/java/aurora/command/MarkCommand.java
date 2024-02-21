package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;

/**
 * The MarkCommand class handles the "mark" command.
 */
public class MarkCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String[] splitCommands;

    /**
     * Constructor for the MarkCommand class.
     *
     * @param taskList      TaskList to edit.
     * @param storage       Storage to interact with.
     * @param splitCommands Full command input.
     */
    public MarkCommand(TaskList taskList, Storage storage, String[] splitCommands) {
        this.taskList = taskList;
        this.storage = storage;
        this.splitCommands = splitCommands;
    }

    @Override
    public String handle() throws AuroraException {
        int taskIndex = validateInputAndGetTaskIndex();
        String message = markTaskAndSave(taskIndex);
        assert !message.equals("Command not executed.") : "Mark command not executed.";
        return message;
    }

    /**
     * Helper function to validate the input and parse the taskIndex.
     *
     * @return taskNumber to be marked
     * @throws AuroraException If the command is of incorrect format.
     */
    private int validateInputAndGetTaskIndex() throws AuroraException {
        if (this.splitCommands.length != 2) {
            throw new AuroraException(AuroraException.INVALID_MARK_FORMAT);
        }
        // Solution adapted from https://www.baeldung.com/java-check-string-number
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
        if (this.taskList.getTaskList().get(taskIndex - 1).getStatus()) {
            throw new AuroraException("Task already marked as done.");
        }
        return taskIndex;
    }

    /**
     * Helper method to mark the task and save edits.
     *
     * @param taskIndex Task to be marked.
     * @return String to alert user if it has been marked and saved.
     * @throws AuroraException If the edit cannot be saved.
     */
    private String markTaskAndSave(int taskIndex) throws AuroraException {
        String message;
        try {
            message = this.taskList.markTaskGui(taskIndex - 1);
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            message = "I'm unable to save edits: " + exception.getMessage();
        }
        return message;
    }

}
