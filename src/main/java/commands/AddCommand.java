package commands;

import data.exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class AddCommand extends Command {

	private Task newTask;

	public AddCommand(Task newTask) {
		this.newTask = newTask;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		try {
			tasks.addTask(newTask);
			ui.addTask(newTask, tasks.getNumTasks());
			storage.saveTasks(tasks);
		} catch (Exception e) {
			throw new DukeException(e.getMessage());
		}
	}
}