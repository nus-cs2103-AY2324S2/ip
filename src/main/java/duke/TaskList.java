package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
        this.tasks = new ArrayList<>();
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
     * @param ui Display ui elements.
     * @param st Stores task after update.
     * @param task Task type.
     * @param args Arguments for the task.
     * @throws InvalidTaskException
     */
    protected String addTask(Ui ui, Storage st, String task, String[] args) throws InvalidTaskException {
        Task t = null;
        if (task.equals("todo")) {
            t = new Todo(args[0]);
        } else if (task.equals("deadline")) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            try {
                LocalDate d = LocalDate.parse(args[1], formatter);
                t = new Deadline(args[0], d);
            } catch (DateTimeParseException de) {
                throw new InvalidTaskException("Date not in format: yyyy-MM-dd, please try again.");
            }
        } else if (task.equals("event")) {
            t = new Event(args[0], args[1], args[2]);
        } else {
            throw new InvalidTaskException("Invalid task syntax for " + task + ".");
        }

        if (t != null) {
            tasks.add(t);
            st.save(this.tasks);
            return "Got it. I've added this task:\n  "
                    + t + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        }
        return "";
    }

    /**
     * Deletes tasks from <code>TaskList</code> and updates file from <code>Storage</code>.
     *
     * @param ui Display ui elements.
     * @param st <code>Storage</code> instance to update.
     * @param args Contains index for specifying task.
     */
    protected String deleteTask(Ui ui, Storage st, String[] args) {
        Task t = tasks.get(Integer.parseInt(args[0]) - 1);
        tasks.remove(t);
        st.save(this.tasks);
        return "Noted. I've removed this task:\n  "
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Lists down all saved tasks in <code>TaskList</code>.
     *
     * @param ui Display ui elements.
     */
    protected String list(Ui ui) {
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
     * @param ui Display ui elements.
     * @param st <code>Storage</code> for file update.
     * @param args Index for specified task.
     */
    protected String mark(Ui ui, Storage st, String[] args) {
        try {
            Task t = tasks.get(Integer.parseInt(args[0]) - 1);
            t.markAsDone();
            st.save(this.tasks);
            return "Nice! I've marked this task as done:\n"
                    + t;
        } catch (IndexOutOfBoundsException ie) {
            throw new IllegalArgumentException("Index number cannot be out of bounds.");
        }
    }

    /**
     * Marks specified task as undone.
     *
     * @param ui Display ui elements.
     * @param st <code>Storage</code> for file update.
     * @param args Index for specified task.
     */
    protected String unmark(Ui ui, Storage st, String[] args) {
        try {
            Task t = tasks.get(Integer.parseInt(args[0]) - 1);
            t.markUndone();
            st.save(this.tasks);
            return "OK, I've marked this task as not done yet:\n"
                    + t;
        } catch (IndexOutOfBoundsException ie) {
            throw new IllegalArgumentException("Index number cannot be out of bounds.");
        }
    }

    /**
     * Allows one to find tasks in tasklist with specified keyword.
     *
     * @param ui Displays ui elements.
     * @param key keyword to filter <code>TaskList</code>.
     */
    protected String find(Ui ui, String key) {
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
