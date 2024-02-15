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
        ArrayList<Task> filteredTask = new ArrayList<>();
        for (Task task : tasks) {
            if (filter.test(task)) {
                filteredTask.add(task);
            }
        }
        return filteredTask;
    }

    public ArrayList<Task> list() {
        return listWithFilter(task -> true);
    }

    public ArrayList<Task> listOnDate(LocalDate date) {
        return listWithFilter(task -> task.checkOccurringOn(date));
    }

    public ArrayList<Task> listDueIn(int days) {
        return listWithFilter(task -> task.checkDueIn(days));
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
        if (to.isBefore(from)) {
            throw new InvalidEventException();
        }

        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    public Task mark(int taskIndex, boolean isDone) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidTaskIndexException(String.valueOf(taskIndex + 1), getSize());
        }

        Task task = tasks.get(taskIndex);
        task.setDone(isDone);

        return task;
    }

    public Task delete(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidTaskIndexException(String.valueOf(taskIndex + 1), getSize());
        }

        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        return task;
    }

    public void updateStorage(Storage storage) throws SavingException {
        storage.refresh(tasks);
    }
}
