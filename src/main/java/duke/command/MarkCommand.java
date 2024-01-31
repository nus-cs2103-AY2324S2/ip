package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MarkInvalidException;
import duke.exception.SaveStorageException;


import java.util.Objects;

/**
 * The abstract class representing a mark or unmark command.
 * */
public class MarkCommand extends Command {
    String[] commandList;
    String type;

    public MarkCommand(String[] commandList, String type) {
        this.commandList = commandList;
        this.type = type;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MarkInvalidException {
        String markMessage;
        if (Objects.equals(this.type, "mark")) {
            markMessage = "Nice! I've marked this task as done:\n  ";
        } else {
            markMessage = "Ok, I've marked this task as not done yet:\n  ";
        }
        try {
            if (this.commandList.length <= 1) {
                throw new MarkInvalidException(this.type);
            }
            int index = Integer.parseInt(commandList[1].replaceAll("\\s", ""));
            if (index < 1 || index > taskList.size()) {
                throw new MarkInvalidException(this.type);
            }
            Task currentTask = taskList.get(index - 1);
            currentTask.changeDone();
            System.out.println(markMessage + currentTask);

            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new MarkInvalidException(this.type);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
