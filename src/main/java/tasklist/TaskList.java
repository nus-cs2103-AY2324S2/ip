package tasklist;

import java.util.List;
import java.util.stream.Collectors;

import exceptions.CalException;
import tasks.Event;
import tasks.Task;

/**
 * Manages the list of {@link Task tasks}.
 * Contains methods to add, delete, mark, unmark and find tasks.
 * Contains method to get task list and number of tasks in task list
 */
public class TaskList {
    protected List<Task> tasks;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @throws CalException
     */
    public void add(Task task) throws CalException {
        Task duplicateTaskFound = isDuplicateTask(task);
        if (duplicateTaskFound != null) {
            throw new CalException(
                    String.format("Duplicate task found:\n %s \n", duplicateTaskFound));
        }
        if (task instanceof Event) {
            Task overlapEvtFound = isOverlappingEvent((Event) task);
            if (overlapEvtFound != null) {
                throw new CalException("This event clashes with:\n" + overlapEvtFound);
            }
        }

        tasks.add(task);
        return;
    }

    private Task isDuplicateTask(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return t;
            }
        }
        return null;
    }

    private Task isOverlappingEvent(Event newEvent) {
        for (Task t : tasks) {
            if (t instanceof Event) {
                Event event = (Event) t;
                if (newEvent.overlaps(event)) {
                    return event;
                }
            }
        }
        return null;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum The task number of the task to be deleted. (not index)
     * @return The deleted task.
     */
    public Task delete(int taskNum) {
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        return t;
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param taskNum The task number of the task to be marked as done. (not index)
     * @return The task marked as done.
     * @throws CalException if the task number is invalid.
     */
    public Task mark(int taskNum) throws CalException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new CalException("Invalid task number!");
        }
        Task t = tasks.get(taskNum - 1);
        assert t != null : "Task should not be null";
        t.setStatus(true);
        return t;
    }

    /**
     * Unmarks a "completed" task in the task list.
     *
     * @param taskNum The task number of the task to be unmarked. (not index)
     * @return The task unmarked.
     * @throws CalException if the task number is invalid.
     */
    public Task unmark(int taskNum) throws CalException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new CalException("Invalid task number!");
        }
        Task t = tasks.get(taskNum - 1);
        assert t != null : "Task should not be null";
        t.setStatus(false);
        return t;
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Find a task from the task list by keyword.
     *
     * @param keyword The search keyword.
     * @return The list of tasks containing the keyword.
     */
    public List<Task> find(String keyword) {
        List<Task> tasksContainingKeyword = this.tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return tasksContainingKeyword;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
