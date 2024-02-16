package duke.command;

import duke.task.TaskList;
import duke.task.Deadline;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MissingDeadlineException;
import duke.exception.SaveStorageException;

/**
 * The class representing the creation of deadline task command.
 * */
public class DeadlineCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    private final String[] commandList;
    /* The first line of response to the user. */
    public static final String RESPONSE_ONE = "Got it. I've added this task:\n  ";
    /* The second line of response to the user. */
    public static final String RESPONSE_TWO = "\nNow you have ";
    /* The third line of response to the user. */
    public static final String RESPONSE_THREE = " tasks in the list.";

    public DeadlineCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws MissingDeadlineException {
        if (this.commandList.length <= 1) {
            throw new MissingDeadlineException();
        }

        Deadline currentDeadline = new Deadline(commandList[1], commandList[2]);
        taskList.add(currentDeadline);

        String response = RESPONSE_ONE
                + currentDeadline
                + RESPONSE_TWO
                + taskList.size()
                + RESPONSE_THREE;

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }

    public boolean isExit() {
        return false;
    }
}
