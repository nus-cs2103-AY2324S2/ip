package Command;

import Utilities.Storage;
import Task.TaskList;
import Utilities.Ui;
import Task.Task;
import Exceptions.DukeException;

public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMarked;

    public MarkCommand(String userInput, boolean isMarked) {
        super(false);
        this.taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        this.isMarked = isMarked;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task taskToMark = taskList.markTask(this.taskIndex, this.isMarked);
            ui.showMarkTask(taskToMark, this.isMarked);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input. Please provide a valid task index.");
        }
        storage.save(taskList);
    }
}
