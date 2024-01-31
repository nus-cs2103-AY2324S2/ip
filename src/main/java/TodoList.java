import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TodoList {
    private final Task[] taskList = new Task[100];
    private int length = 0;

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
}
