package util;

import exceptions.DukeException;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) throws DukeException {
        if (index > this.tasks.size() - 1) {
            throw new DukeException("Your index is out of bounds!");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTaskListLength() {
        return tasks.size();
    }

}