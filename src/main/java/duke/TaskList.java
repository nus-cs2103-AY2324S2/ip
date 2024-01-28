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
     */
    public void list() {
        String text;
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            text = "\t" + (i + 1) + "." + this.taskList.get(i).toString();
            System.out.println(text);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    /**
     * Marks a specified task as done.
     *
     * @param num Number of task to mark.
     */
    public void mark(int num) {
        Task curr = this.taskList.get(num - 1);
        curr.mark();

        String text = "\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n"
                + "\t" + curr + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    /**
     * Unmarks a specified task.
     *
     * @param num Number of task to unmark.
     */
    public void unmark(int num) {
        Task curr = this.taskList.get(num - 1);
        curr.unmark();

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've marked this task as not done yet:\n"
                + "\t" + curr + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    /**
     * Adds a new task.
     *
     * @param add Task to add.
     */
    public void add(Task add) {
        this.taskList.add(add);
        String word = " task";
        if (this.taskList.size() != 1) {
            word += "s";
        }

        String text = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t  " + add.toString() + "\n"
                + "\tNow you have " + this.taskList.size() + word + " in the list.\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    /**
     * Deletes a specified task.
     *
     * @param num Number of task to delete.
     */
    public void delete(int num) {
        Task curr = this.taskList.get(num - 1);
        this.taskList.remove(curr);

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've deleted this task:\n"
                + "\t  " + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    /**
     * Prints all task with given text
     *
     * @param text Text to search for
     */
    public void find(String text) {
        String found = "";
        int i = 1;
        System.out.println("\t____________________________________________________________");
        for (Task t : this.taskList) {
            if (t.toString().contains(text)) {
                found = found + "\t" + i + "." + t + "\n";
                i += 1;
            }
        }
        if (i != 1) {
            System.out.print(found);
        } else {
            System.out.println("\tNo tasks matching " + text + " found!");
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public int size() {
        return this.taskList.size();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}
