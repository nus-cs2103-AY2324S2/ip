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
    String[] commandList;

    public DeadlineCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws MissingDeadlineException {
        String response = "";
        if (this.commandList.length <= 1) {
            throw new MissingDeadlineException();
        }
        Deadline currentDeadline = new Deadline(commandList[1], commandList[2]);
        taskList.add(currentDeadline);

        response += "Got it. I've added this task:\n  " + currentDeadline;
        response += "\nNow you have " + taskList.size() + " tasks in the list.";

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
