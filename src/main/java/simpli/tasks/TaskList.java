package simpli.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * To manage and manipulate tasks and task actions.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes a task list without any tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list with predefined tasks.
     *
     * @param tasks An array list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a specific as completed.
     *
     * @param taskNum A specific task number.
     */
    public void mark(int taskNum) {
        int itemIndex = taskNum - 1;
        tasks.get(itemIndex).done();
    }

    /**
     * Marks a specific task as completed.
     *
     * @param taskNum int representing the task number.
     */
    public void unmark(int taskNum) {
        int itemIndex = taskNum - 1;
        tasks.get(itemIndex).undone();
    }

    /**
     * Adds a new todo task to the task list and returns it.
     *
     * @param tokens An array of tokens.
     * @return The todo task that has been added.
     */
    public Task addTodo(String[] tokens) {
        Todo todo = new Todo(tokens[1].equals("1"), tokens[2]);
        tasks.add(todo);
        return todo;
    }

    /**
     * Adds a new deadline task to the task list and returns it.
     *
     * @param tokens An array of tokens.
     * @param dateTimes An array of LocalDateTime.
     * @return The deadline task that has been added.
     */
    public Task addDeadline(String[] tokens, LocalDateTime[] dateTimes) {
        Deadline deadline = new Deadline(tokens[1].equals("1"), tokens[2], dateTimes[0]);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Adds a new event task to the task list and returns it.
     *
     * @param tokens An array of tokens.
     * @param dateTimes An array of LocalDateTime.
     * @return The event task that has been added.
     */
    public Task addEvent(String[] tokens, LocalDateTime[] dateTimes) {
        Event event = new Event(tokens[1].equals("1"), tokens[2], dateTimes[0], dateTimes[1]);
        tasks.add(event);
        return event;
    }

    /**
     * Deletes a task from the task list and returns the deleted task.
     *
     * @param taskNum Task number.
     * @return The deleted task.
     */
    public Task deleteTask(int taskNum) {
        Task removedTask = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        return removedTask;
    }

    public void sort() {
        tasks.sort(Task::compareTo);
    }

    /**
     * Returns an ArrayList of tasks with names that matches the keyword.
     *
     * @param keyword String.
     * @return An ArrayList of task.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns specified task number.
     *
     * @param taskNum The task number to be returned.
     * @return The specified task number.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Returns the number of tasks stored in the task list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the tasks that is being stored.
     *
     * @return An ArrayList of task.
     */
    public ArrayList<Task> tasks() {
        return this.tasks;
    }

    /**
     * Returns the task list String representation.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder strItems = new StringBuilder();
        for (int i = 0; i < tasks.size() - 1; i++) {
            strItems.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        if (!tasks.isEmpty()) {
            strItems.append(String.format("%d. %s", tasks.size(), tasks.get(tasks.size() - 1)));
        }

        return strItems.toString();
    }
}
