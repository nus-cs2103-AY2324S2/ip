package task;

import data.exception.DukeException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private final List<Task> store;

	public TaskList() {
		this.store = new ArrayList<>();
	}

	public TaskList(List<Task> store) {
		this.store = store;
	}

	public List<Task> getTasks() {
		return store;
	}

	public int getNumTasks() {
		return store.size();
	}

	public void addTask(Task newTask) {
		store.add(newTask);
	}

	public Task deleteTask(int deleteIndex) throws DukeException {
		if (deleteIndex > store.size() || deleteIndex <= 0) {
			throw new DukeException("\tNumber out of bounds!\n");
		}
		return store.remove(deleteIndex - 1);
	}

	public Task markTask(int updateIndex, boolean taskComplete) throws DukeException {
		if (updateIndex > store.size() || updateIndex <= 0) throw new DukeException("Number Out of Bounds");
		Task updateTask = store.get(updateIndex - 1);

		if (taskComplete) updateTask.mark();
		else updateTask.unmark();
		return updateTask;
	}

}
