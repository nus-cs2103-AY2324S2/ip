package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.DeleteInvalidException;
import duke.exception.SaveStorageException;

/**
 * The class representing a delete task command.
 * */
public class DeleteCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    String[] commandList;

    public DeleteCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DeleteInvalidException {
        String response = "";
        String deleteMessage = "Noted. I've removed this task:\n  ";
        try {
            if (this.commandList.length <= 1) {
                throw new DeleteInvalidException();
            }
            int deleteIndex = Integer.parseInt(commandList[1].replaceAll("\\s", ""));
            if (deleteIndex < 1 || deleteIndex > taskList.size()) {
                throw new DeleteInvalidException();
            }
            Task deleteTask = taskList.get(deleteIndex - 1);
            taskList.remove(deleteTask);
            response += deleteMessage + deleteTask;

            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new DeleteInvalidException();
        } catch (SaveStorageException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }

    public boolean isExit() {
        return false;
    }
}