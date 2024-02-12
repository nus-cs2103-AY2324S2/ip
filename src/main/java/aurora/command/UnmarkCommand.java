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
        String message = "Command not executed.";
        if (this.splitCommands.length != 2) {
            throw new AuroraException("Invalid number of arguments!\n" +
                    "Make sure to enter unmark, then the number of the task you want to unmark.");
            // Solution adapted from https://www.baeldung.com/java-check-string-number
        } else if (!this.splitCommands[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new AuroraException("Please enter an integer as the second input.");
        } else if (Integer.parseInt(this.splitCommands[1]) <= 0) {
            throw new AuroraException("Please enter an integer greater than 0 as the second input.");
        } else if (Integer.parseInt(this.splitCommands[1]) > this.taskList.getTaskList().size()) {
            throw new AuroraException("Please enter an integer representing a task within the list.");
        } else if (!this.taskList.getTaskList().get(Integer.parseInt(splitCommands[1]) - 1).getStatus()) {
            throw new AuroraException("Task already unmarked.");
        } else {
            int taskIndex = Integer.parseInt(splitCommands[1]);
            message = this.taskList.unmarkTaskGui(taskIndex - 1);
        }
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            message = "Unable to save edits: " + exception.getMessage();
        }
        return message;
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
