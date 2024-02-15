package bob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

import bob.exception.InvalidEventException;
import bob.exception.InvalidTaskIndexException;
import bob.exception.SavingException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    private ArrayList<Task> listWithFilter(Predicate<Task> filter) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (filter.test(task)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public ArrayList<Task> list() {
        return listWithFilter(task -> true);
    }

    public ArrayList<Task> listOnDate(LocalDate date) {
        return listWithFilter(task -> task.isOccurringOn(date));
    }

    public ArrayList<Task> listDueIn(int days) {
        return listWithFilter(task -> task.isDueIn(days));
    }

    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    public Task addDeadline(String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    public Task addEvent(String description, LocalDateTime from, LocalDateTime to) throws InvalidEventException {
        // Check whether event is invalid
        if (to.isBefore(from)) {
            throw new InvalidEventException();
        }

        // Add and return the event
        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    public Task mark(int taskIndex, boolean isDone) throws InvalidTaskIndexException {
        // Check whether task index is out of bounds
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidTaskIndexException(String.valueOf(taskIndex + 1), getSize());
        }

        // Mark the task using isDone and return it
        Task task = tasks.get(taskIndex);
        task.setDone(isDone);
        return task;
    }

    public Task delete(int taskIndex) throws InvalidTaskIndexException {
        // Check whether task index is out of bounds
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidTaskIndexException(String.valueOf(taskIndex + 1), getSize());
        }

        // Delete and return the task
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task;
    }

    public void updateStorage(Storage storage) throws SavingException {
        storage.refresh(tasks);
    }
}
