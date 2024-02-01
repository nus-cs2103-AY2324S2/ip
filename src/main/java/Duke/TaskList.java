package Duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) throws DukeDataCorruptedException {
        this.tasks = tasks;
    }

    /*public Commands.TaskList(List<String> taskData) throws DukeDataCorruptedException {
        tasks = Commands.TaskListDecoder.decode(taskData);
    } */

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).setDone(true);
    }
    public void markTaskAsNotDone(int index) { tasks.get(index).setDone(false); }

    public List<Task> getTasks() {
        return tasks;
    }
}

