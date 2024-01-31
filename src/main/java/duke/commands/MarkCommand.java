package duke.commands;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
	private int updateIndex;
	private boolean isComplete;

	public MarkCommand(int updateIndex, boolean isComplete) {
		this.updateIndex = updateIndex;
		this.isComplete = isComplete;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		try {
			Task updateTask = tasks.markTask(updateIndex, isComplete);
			ui.mark(updateTask, isComplete);
			storage.saveTasks(tasks);
		} catch (Exception e) {
			throw new DukeException(e.getMessage());
		}
	}
}
