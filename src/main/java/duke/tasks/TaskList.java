package duke.tasks;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidKeyException;
import duke.exceptions.WrongFormatException;
import duke.utils.KeyEnum;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public Integer getNumOfTasks() {
		return this.tasks.size();
	}

	public String getTaskInfileStringFormat(Integer i) {
		return this.tasks.get(i).inFileStringFormat();
	}

	public void listTask() {
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ". " + tasks.get(i));
		}
	}

	public Task addTask(String detail, String from, String to, KeyEnum currentKey) throws WrongFormatException, InvalidDateTimeException {
		Task task = null;
		switch (currentKey) {
		case DEADLINE:
			task = new Deadline(false, detail, to);
			break;
		case TODO:
			task = new Todo(false, detail);
			break;
		case EVENT:
			task = new Event(false, detail, from, to);
			break;
		}
		// Throw empty body exception if the added
		if (task == null || detail.length() == 0) {
			throw new WrongFormatException("The task body can not be empty. Please specify the task you want to add.");
		}
		tasks.add(task);
		return task;
	}

	public Task markTaskById(Integer id, Boolean status) throws IOException, InvalidKeyException {
		// Test if the id is out of bound
		if (id >= this.getNumOfTasks()) {
			throw new InvalidKeyException("Id out of bound");
		}
		this.tasks.get(id).setStatus(status);
		return this.tasks.get(id);
	}

	public Task deleteTaskById(Integer id) throws IOException, InvalidKeyException {
		// Test if the id is out of bound
		if (id >= this.getNumOfTasks()) {
			throw new InvalidKeyException("Id out of bound");
		}
		Task taskToDelete = this.tasks.get(id);
		this.tasks.remove(taskToDelete);
		return taskToDelete;
	}
}
