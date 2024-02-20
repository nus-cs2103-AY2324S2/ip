package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Holds an array of <code>Task</code> that actively keeps track of stored tasks in runtime.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a <code>TaskList</code> with no tasks.
     */
    protected TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a <code>TaskList</code> with specified task list.
     *
     * @param tasks <code>ArrayList</code> of tasks.
     */
    protected TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds relevant task to <code>TaskList</code> and updates file from <code>Storage</code>.
     *
     * @param st Stores task after update.
     * @param task Task type.
     * @param args Arguments for the task.
     * @return Dialogue for Duke.
     * @throws DukeException If arguments for the task are not in a specific format.
     */
    protected String addTask(Storage st, String task, String... args) throws DukeException {
        Task t = null;
        if (task.equals("todo")) {
            t = createTodoTask(args[0]);
        } else if (task.equals("deadline")) {
            t = createDeadlineTask(args[0], args[1]);
        } else if (task.equals("event")) {
            t = createEventTask(args[0], args[1], args[2]);
        } else {
            throw new InvalidTaskException("Invalid task syntax for " + task + ".");
        }

        tasks.add(t);
        st.saveTasks(tasks);
        return "Got it. I've added this task:\n  "
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private Task createTodoTask(String name) {
        return new Todo(name);
    }

    private Task createDeadlineTask(String name, String deadline) throws InvalidTaskException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate d = LocalDate.parse(deadline, formatter);
            return new Deadline(name, d);
        } catch (DateTimeParseException de) {
            throw new InvalidTaskException("Date not in format: yyyy-MM-dd, please try again.");
        }
    }

    private Task createEventTask(String name, String from, String to) {
        return new Event(name, from, to);
    }

    /**
     * Deletes tasks from <code>TaskList</code> and updates file from <code>Storage</code>.
     *
     * @param st <code>Storage</code> instance to update.
     * @param args Contains index for specifying task.
     * @return Dialogue for Duke.
     */
    protected String deleteTask(Storage st, String... args) throws DukeException {
        Task t = tasks.get(Integer.parseInt(args[0]) - 1);
        tasks.remove(t);
        st.saveTasks(tasks);
        return "Noted. I've removed this task:\n  "
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Lists down all saved tasks in <code>TaskList</code>.
     *
     * @return Dialogue for Duke.
     */
    protected String list() {
        int count = 0;
        String res = "Here are the tasks in your list:";
        for (Task t : tasks) {
            count++;
            res = res.concat("\n" + count + ". " + t);
        }
        return res;
    }

    /**
     * Marks specified task as done.
     *
     * @param st <code>Storage</code> for file update.
     * @param args Index for specified task.
     * @return Dialogue for Duke.
     * @throws DukeException If <code>TaskList</code> index is out of bounds.
     */
    protected String mark(Storage st, String... args) throws DukeException {
        try {
            Task t = tasks.get(Integer.parseInt(args[0]) - 1);
            t.markAsDone();
            st.saveTasks(tasks);
            return "Nice! I've marked this task as done:\n"
                    + t;
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("Index number cannot be out of bounds.");
        }
    }

    /**
     * Marks specified task as undone.
     *
     * @param st <code>Storage</code> for file update.
     * @param args Index for specified task.
     * @return Dialogue for Duke.
     * @throws DukeException If <code>TaskList</code> index is out of bounds.
     */
    protected String unmark(Storage st, String... args) throws DukeException {
        try {
            Task t = tasks.get(Integer.parseInt(args[0]) - 1);
            t.markUndone();
            st.saveTasks(tasks);
            return "OK, I've marked this task as not done yet:\n"
                    + t;
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("Index number cannot be out of bounds.");
        }
    }

    /**
     * Allows one to find tasks in TaskList with specified keyword.
     *
     * @param key keyword to filter <code>TaskList</code>.
     * @return Dialogue for Duke.
     */
    protected String find(String key) {
        int count = 1;
        String res = "Here are the matching tasks in your list:";
        for (Task t : tasks) {
            if (t.getTaskName().contains(key)) {
                res = res.concat("\n" + count + ". " + t);
                count++;
            }
        }
        return res;
    }
}
