package duke.task;

import duke.DukeException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("duke.task.Task number out of range.");
        }
        return tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void listTasks(PrintWriter writer) {
        if (tasks.isEmpty()) {
            writer.println("Your task list is empty.");
        } else {
            String taskWord = tasks.size() == 1 ? "task" : "tasks";
            writer.println("Here are the " + taskWord + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                writer.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public String get(int index) {
        return tasks.get(index).toString();
    }
}
