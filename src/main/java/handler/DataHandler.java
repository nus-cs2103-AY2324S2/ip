package handler;

import action.task.Task;
import exception.InvalidCommandException;

import java.util.Arrays;

public class DataHandler {
    public static final DataHandler instance = new DataHandler();
    private static Task[] tasks = new Task[100];
    private static int index = 0; // always equals to the index of last valid element plus 1
    private DataHandler() {};

    public void handleData(Task task) {
        if (index >= 100) return;
        tasks[index] = task;
        index++;
        // Todo: Exception handling
    }

    public void markTask(int index) throws InvalidCommandException {
        if (index > DataHandler.index || index <= 0) {
            throw new InvalidCommandException("Index out of bound");
        }
        tasks[index - 1].mark();
    }

    public void unmarkTask(int index) throws InvalidCommandException {
        if (index > DataHandler.index || index <= 0) {
            throw new InvalidCommandException("Index out of bound");
        }
        tasks[index - 1].unmark();
    }

    public int length() {
        return index;
    }

    public Task[] getData() {
        return Arrays.copyOfRange(tasks, 0, index);
    }

    public Task getTask(int index) {
        return tasks[index - 1];
    }
}
