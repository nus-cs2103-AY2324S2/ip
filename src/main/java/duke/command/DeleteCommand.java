package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.DeleteInvalidException;
import duke.exception.SaveStorageException;

public class DeleteCommand extends Command {
    String[] commandList;

    public DeleteCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DeleteInvalidException {
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
            System.out.println(deleteMessage + deleteTask);

            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new DeleteInvalidException();
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}