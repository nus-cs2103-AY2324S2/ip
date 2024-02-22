package shon;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import shon.exception.ParameterException;
import shon.task.Deadline;
import shon.task.Event;
import shon.task.Task;
import shon.task.Todo;

/**
 * Represents the todo list of the user.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a new <code>TaskList</code> with a capacity of 100.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds the tasks to the list and returns the results of the command.
     *
     * @param task The task to be added to the list.
     * @return An array of String showing the results of the command.
     */
    private String[] add(Task task) {
        this.tasks.add(task);
        int size = this.tasks.size();
        return new String[]{"Got it. I've added this task:", "  " + task,
                String.format("Now you have %s %s in the list.", size, size < 2 ? "task" : "tasks")};
    }

    /**
     * Adds a <code>Todo</code> task that is initially unmarked to the list.
     *
     * @param description The description of the <code>Todo</code> task.
     * @return An array of String showing the results of the command.
     */
    public String[] addTodo(String description) {
        Task task = new Todo(description, false);
        return add(task);
    }

    /**
     * Adds a <code>Todo</code> task to the list with the mark status as isDone.
     *
     * @param description The description of the <code>Todo</code> task.
     * @param isDone The completion status of the <code>Todo</code> task as a boolean.
     */
    public void addTodo(String description, boolean isDone) {
        Task task = new Todo(description, isDone);
        add(task);
    }

    /**
     * Adds a <code>Deadline</code> task that is initially unmarked to the list.
     *
     * @param description The description of the <code>Deadline</code> task.
     * @param by The by datetime of the <code>Deadline</code> task as a String.
     * @return An array of String showing the results of the command.
     */
    public String[] addDeadline(String description, String by) throws DateTimeParseException {
        Task task = new Deadline(description, by, false);
        return add(task);
    }

    /**
     * Adds a <code>Deadline</code> task to the list with the mark status as isDone.
     *
     * @param description The description of the <code>Deadline</code> task.
     * @param by The by datetime of the <code>Deadline</code> task as a String.
     * @param isDone The completion status of the <code>Deadline</code> task as a boolean.
     */
    public void addDeadline(String description, String by, boolean isDone) {
        Task task = new Deadline(description, by, isDone);
        add(task);
    }

    /**
     * Adds an <code>Event</code> task that is initially unmarked to the list.
     *
     * @param description The description of the <code>Event</code> task.
     * @param from The from datetime of the <code>Event</code> task as a String.
     * @param to The to datetime of the <code>Event</code> task as a String.
     * @return An array of String showing the results of the command.
     */
    public String[] addEvent(String description, String from, String to) throws DateTimeParseException {
        Task task = new Event(description, from, to, false);
        return add(task);
    }

    /**
     * Adds an <code>Event</code> task to the list with the mark status as isDone.
     *
     * @param description The description of the <code>Event</code> task.
     * @param from The from datetime of the <code>Event</code> task as a String.
     * @param to The to datetime of the <code>Event</code> task as a String.
     * @param isDone The completion status of the <code>Event</code> task as a boolean.
     */
    public void addEvent(String description, String from, String to, boolean isDone) {
        Task task = new Event(description, from, to, isDone);
        add(task);
    }

    /**
     * Returns the tasks in the list in a user-appropriate format. Indicates so when the list is empty.
     *
     * @return An array of Strings representing the tasks in the list.
     */
    public String[] getTasks() {
        // check for empty tasklist
        if (this.tasks.size() == 0) {
            return new String[]{"Your list is currently empty."};
        }

        String[] outputs = new String[this.tasks.size() + 1];
        outputs[0] = "Here are the tasks in your list:";
        for (int i = 1; i < this.tasks.size() + 1; i++) {
            outputs[i] = i + "." + this.tasks.get(i - 1);
        }
        return outputs;
    }

    /**
     * Marks the task indicated by idx as done.
     *
     * @param idx The index of the task to be marked as done.
     * @return An array of String representing the results of the command.
     * @throws ParameterException If the index given is larger than the number of tasks in the list, or if
     *     the index is a non-positive number.
     */
    public String[] mark(int idx) throws ParameterException {
        // check invalid index
        if (idx > this.tasks.size() || idx < 1) {
            throw new ParameterException("Please select a valid task number from the list.");
        }

        Task task = this.tasks.get(idx - 1);
        String description = task.markAsDone();
        return new String[]{"Nice! I've marked this task as done:", "  " + description};
    }

    /**
     * Marks the task indicated by idx as not done.
     *
     * @param idx The index of the task to be marked as not done.
     * @return An array of String representing the results of the command.
     * @throws ParameterException If the index given is larger than the number of tasks in the list, or if
     *     the index is a non-positive number.
     */
    public String[] unmark(int idx) throws ParameterException {
        // check invalid index
        if (idx > this.tasks.size() || idx < 1) {
            throw new ParameterException("Please select a valid task number from the list.");
        }

        Task task = this.tasks.get(idx - 1);
        String description = task.markAsNotDone();
        return new String[]{"OK, I've marked this task as not done yet:", "  " + description};
    }

    /**
     * Deletes the task indicated by the idx from the list.
     *
     * @param idx The index of the task to be deleted.
     * @return An array of String representing the results of the command.
     * @throws ParameterException If the list is empty, if the index given is larger than the number of tasks in the
     *     list, or if the index is a non-positive number.
     */
    public String[] deleteTask(int idx) throws ParameterException {
        // check empty list
        if (this.tasks.size() == 0) {
            throw new ParameterException("Your list is empty. Nothing to delete.");
        }

        // check invalid index
        if (idx > this.tasks.size() || idx < 1) {
            throw new ParameterException("Please select a valid task number to delete from the list.");
        }

        Task task = this.tasks.get(idx - 1);
        this.tasks.remove(idx - 1);
        int size = this.tasks.size();
        return new String[]{"Noted. I've removed this task:", "  " + task,
                String.format("Now you have %s %s in the list.", size, size < 2 ? "task" : "tasks")};
    }

    /**
     * Returns the tasks and data in a storage-appropriate format.
     *
     * @return An array of String representing the tasks in the list, in a storage-appropriate format.
     */
    public String[] formatData() {
        return this.tasks.stream().map(Task::formatData).toArray(String[]::new);
    }

    /**
     * Returns the tasks with description containing the given keyword.
     *
     * @param keyword The keyword to look for in task descriptions.
     * @return An array of Strings representing the matching tasks.
     */
    public String[] filterByKeyword(String keyword) {
        List<String> filteredTasks = new ArrayList<>();
        filteredTasks.add("Here are the matching tasks in your list:");
        for (Task task : this.tasks) {
            if (task.hasKeyword(keyword)) {
                filteredTasks.add(filteredTasks.size() + "." + task.toString());
            }
        }
        return filteredTasks.size() == 1
               ? new String[]{"There are no tasks matching the keyword " + keyword + "."}
               : filteredTasks.toArray(new String[0]);
    }
}
