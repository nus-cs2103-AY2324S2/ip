package james.commands;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasks.Task;
import james.tasklist.TaskList;
import james.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int indexToMark;

    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToMark = tasks.getTask(indexToMark);
            taskToMark.markAsDone();
            ui.showMarkedTask(taskToMark);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index provided is invalid.");
        } catch (IOException e) {
            ui.showError("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
