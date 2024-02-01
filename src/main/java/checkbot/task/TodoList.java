package checkbot.task;

import checkbot.exception.InvalidIndexException;

public class TodoList {
    private final Task[] taskList;
    private int length = 0;

    public TodoList() {
        this.taskList = new Task[100];
    }

    private TodoList(Task[] taskList, int length) {
        this.taskList = taskList;
        this.length = length;
    }

    public void addTask(Task task) {
        taskList[length] = task;
        length++;
    }

    public void markTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= length) {
            throw new InvalidIndexException(i + 1, length);
        }
        taskList[i].mark();
    }

    public void unmarkTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= length) {
            throw new InvalidIndexException(i + 1, length);
        }
        taskList[i].unmark();
    }

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

    public TodoList find(String substr) {
        Task[] subList = new Task[100];
        int newLength = 0;
        for (int i = 0; i < length; i++) {
            if (taskList[i].nameContains(substr)) {
                subList[newLength] = taskList[i];
                newLength++;
            }
        }
        return new TodoList(subList, newLength);
    }
}
