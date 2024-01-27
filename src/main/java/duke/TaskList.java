package duke;

import duke.task.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws TaskNotFound {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound(index, e);
        }
    }

    public void deleteTask(int index) throws TaskNotFound {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound(index, e);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("1").append(". ").append(tasks.get(0));
        for (int i = 0; i < tasks.size(); i++) {
            s.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return s.toString();
    }

    public static class TaskNotFound extends Exception {
        public TaskNotFound(int index, Throwable cause) {
            super("Could not find task " + (index + 1), cause);
        }
    }
}
