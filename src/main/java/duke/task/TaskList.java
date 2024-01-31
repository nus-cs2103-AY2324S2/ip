package duke.task;

import duke.data.exception.DukeException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
	/** List of tasks */
	private final List<Task> store;

	public TaskList() {
		this.store = new ArrayList<>();
	}

	public TaskList(List<Task> store) {
		this.store = store;
	}

	/**
	 * Returns list of tasks
	 *
	 * @return List<Task> of tasks
	 */
	public List<Task> getTasks() {
		return store;
	}

	/**
	 * Returns number of tasks
	 *
	 * @return Number of tasks in the list
	 */
	public int getNumTasks() {
		return store.size();
	}

	/**
	 * Adds new task to the list
	 *
	 * @param newTask task to be added
	 */
	public void addTask(Task newTask) {
		store.add(newTask);
	}

	/**
	 * Deletes task at given index from the list
	 *
	 * @param deleteIndex index of task to be deleted
	 * @return Task that was deleted
	 * @throws DukeException if number given is out of bounds
	 */
	public Task deleteTask(int deleteIndex) throws DukeException {
		if (deleteIndex > store.size() || deleteIndex <= 0) {
			throw new DukeException("\tNumber out of bounds!\n");
		}
		return store.remove(deleteIndex - 1);
	}

	/**
	 * Mark or unmark task of given update index
	 *
	 * @param updateIndex index of task to be updated
	 * @param taskComplete to mark or unmark the task
	 * @return Task that was updated
	 * @throws DukeException if number given is out of bounds
	 */
	public Task markTask(int updateIndex, boolean taskComplete) throws DukeException {
		if (updateIndex > store.size() || updateIndex <= 0) throw new DukeException("Number Out of Bounds");
		Task updateTask = store.get(updateIndex - 1);

		if (taskComplete) updateTask.mark();
		else updateTask.unmark();
		return updateTask;
	}

}
