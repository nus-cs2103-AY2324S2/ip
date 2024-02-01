package checkbot.task;

import checkbot.exception.InvalidIndexException;

/**
 * Represents a list of tasks.
 */
public class TodoList {
    private final Task[] taskList = new Task[100];
    /**
     * The number of tasks in the list.
     */
    private int length = 0;

    /**
     * Adds a task to the list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList[length] = task;
        length++;
    }

    /**
     * Marks the task at the specified index as done.
     * 
     * @param i The index of the task to be marked as done.
     * @throws InvalidIndexException If the index is invalid.
     */
    public Task markTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= length) {
            throw new InvalidIndexException(i + 1, length);
        }
        taskList[i].mark();
        return taskList[i];
    }

    /**
     * Marks the task at the specified index as incomplete.
     * 
     * @param i The index of the task to be unmarked.
     * @throws InvalidIndexException If the index is invalid.
     */
    public Task unmarkTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= length) {
            throw new InvalidIndexException(i + 1, length);
        }
        taskList[i].unmark();
        return taskList[i];
    }

    /**
     * Deletes the task at the specified index.
     * 
     * @param i The index of the task to be deleted.
     * @return The deleted task.
     * @throws InvalidIndexException If the index is invalid.
     */
    public Task deleteTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= length) {
            throw new InvalidIndexException(i + 1, length);
        }
        Task deletedTask = taskList[i];
        while (i < length - 1) {
            taskList[i] = taskList[i + 1];
            i++;
        }
        length--;
        return deletedTask;
    }

    public Task getTask(int i) {
        return taskList[i];
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (int i = 0; i < length; i++) {
            txt.append(i + 1)
                    .append(". ")
                    .append(taskList[i])
                    .append(i < length - 1 ? "\n" : "");
        }
        return txt.toString();
    }

    /**
     * Formats the list of tasks for saving to a file.
     * 
     * @return The formatted list of tasks.
     */
    public String formatForFile() {
        StringBuilder txt = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            txt.append(taskList[i].formatForFile());
            if (i < this.length - 1) {
                txt.append("\n");
            }
        }
        return txt.toString();
    }
}
