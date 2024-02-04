package duke.tasklist;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number is out of bounds.");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws DukeException{
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number is out of bounds.");
        }
        return tasks.get(index);
    }

    /**
     * Filters the tasks in the task list based on a lambda expression.
     * @return A new TaskList object containing the filtered tasks.
     */
    public TaskList filterTasks(Predicate<Task> predicate) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (predicate.test(task)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
