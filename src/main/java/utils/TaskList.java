package utils;

import java.util.ArrayList;

import exceptions.ConvoBotException;
import tasks.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    private Storage db;
    
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(Storage db) {
        this.db = db;
        taskList = db.read();
    }

    public void add(Task task) {
        taskList.add(task);
        db.write(taskList);
    }

    public void mark(int i, boolean isDone) throws ConvoBotException {
        if (i < 0 || i >= taskList.size()) {
            throw new ConvoBotException("Invalid input. You must specify a valid index.");
        }
        Task task = taskList.get(i);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    public String getTaskString(int i) throws ConvoBotException {
        if (i < 0 || i >= taskList.size()) {
            throw new ConvoBotException("Invalid input. You must specify a valid index.");
        }
        return taskList.get(i).toString();
    }

    public void delete(int i) throws ConvoBotException {
        if (i < 0 || i >= taskList.size()) {
            throw new ConvoBotException("Invalid input. You must specify a valid index.");
        }
        taskList.remove(i);
        db.write(taskList);
    }

    public int size() {
        return taskList.size();
    }
}
