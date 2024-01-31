package duke.command;

import duke.task.TaskList;
import duke.task.Deadline;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MissingDeadlineException;
import duke.exception.SaveStorageException;

public class DeadlineCommand extends Command {
    String[] commandList;

    public DeadlineCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingDeadlineException {
        if (this.commandList.length <= 1) {
            throw new MissingDeadlineException();
        }
        Deadline currentDeadline = new Deadline(commandList[1], commandList[2]);
        taskList.add(currentDeadline);
        System.out.println("Got it. I've added this task:\n  " + currentDeadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
