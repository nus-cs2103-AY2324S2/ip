package duke;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList to handle all operations related to tasks.
 */
public class TaskList {
    private List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Lists all current tasks
     * @return String of output to be used if needed.
     */
    public String list() {
        String text = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            text += "\t" + (i + 1) + "." + this.taskList.get(i).toString() + "\n";
        }
        return text;
    }

    /**
     * Marks a specified task as done.
     * @param num Number of task to mark.
     * @return String of output to be used if needed.
     */
    public String mark(int num) {
        Task curr = this.taskList.get(num - 1);
        curr.mark();

        return "\tNice! I've marked this task as done:\n"
                + "\t" + curr + "\n";
    }

    /**
     * Unmarks a specified task.
     * @param num Number of task to unmark.
     * @return String of output to be used if needed.
     */
    public String unmark(int num) {
        Task curr = this.taskList.get(num - 1);
        curr.unmark();

        return "\tOK, I've marked this task as not done yet:\n"
                + "\t" + curr + "\n";
    }

    /**
     * Adds a new task.
     * @param add Task to add.
     * @return String of output to be used if needed.
     */
    public String add(Task add) {
        assert add != null; // ensure that task to be added exists
        this.taskList.add(add);
        String word = " task";
        if (this.taskList.size() != 1) {
            word += "s";
        }

        return "\tGot it. I've added this task:\n"
                + "\t  " + add.toString() + "\n"
                + "\tNow you have " + this.taskList.size() + word + " in the list.\n";
    }

    /**
     * Deletes a specified task.
     * @param num Number of task to delete.
     * @return String of output to be used if needed.
     */
    public String delete(int num) {
        Task curr = this.taskList.get(num - 1);
        this.taskList.remove(curr);

        return "\tOK, I've deleted this task:\n"
                + "\t  " + curr.toString() + "\n";
    }

    /**
     * Prints all task with given text.
     * @param text Text to search for.
     * @return String of output to be used if needed.
     */
    public String find(String text) {
        String found = "";
        int i = 1;
        for (Task t : this.taskList) {
            if (t.getDescription().contains(text)) {
                found = found + "\t" + i + "." + t + "\n";
                i += 1;
            }
        }
        if (i != 1) {
            assert !found.equals(""); // ensure that listing for a task must be added
            return found;
        } else {
            return "\tNo tasks matching \"" + text + "\" found!\n";
        }
    }

    /**
     * Gets the number of tasks.
     * @return total number of tasks.
     */
    protected int size() {
        return this.taskList.size();
    }

    /**
     * Gets the list of tasks.
     * @return taskList
     */
    protected List<Task> getTaskList() {
        return this.taskList;
    }
}
