package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;

/**
 * The UnmarkCommand class handles the "unmark" command.
 */
public class UnmarkCommand extends Command {

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
     * Helper function to validate the command input.
     *
     * @return TaskIndex of task to unmark.
     * @throws AuroraException If the command format was incorrect.
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
     * Helper function to unmark task and save tasklist to file.
     *
     * @param taskIndex Task to be unmark.
     * @return Alert message that task has been unmarked and saved.
     * @throws AuroraException If saving was unsuccessful.
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
