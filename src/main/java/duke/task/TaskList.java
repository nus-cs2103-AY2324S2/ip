package duke.task;

import duke.data.exception.DukeException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private final List<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public TaskList(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public int getNumTasks() {
		return tasks.size();
	}

	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	public Task deleteTask(int deleteIndex) throws DukeException {
		if (deleteIndex > tasks.size() || deleteIndex <= 0) {
			throw new DukeException("\tNumber out of bounds!\n");
		}
		return tasks.remove(deleteIndex - 1);
	}

	public Task markTask(int updateIndex, boolean taskComplete)
			throws DukeException {
		if (updateIndex > tasks.size() || updateIndex <= 0) {
			throw new DukeException("Number Out of Bounds");
		}
		Task updateTask = tasks.get(updateIndex - 1);

		if (taskComplete) {
			updateTask.mark();
		}
		else {
			updateTask.unmark();
		}
		return updateTask;
	}

}
