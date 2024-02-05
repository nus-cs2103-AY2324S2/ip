package handler;

import action.task.Task;
import exception.IndexOutOfBoundsException;

import java.util.ArrayList;

public class DataHandler {
    public static final DataHandler instance = new DataHandler();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private DataHandler() {};

    public void handleData(Task task) {
        tasks.add(task);
        // Todo: Exception handling
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.get(index - 1).mark();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.get(index - 1).unmark();
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.remove(index - 1);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getData() {
        return tasks;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        return tasks.get(index - 1);
    }
}
