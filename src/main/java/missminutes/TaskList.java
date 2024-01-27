package missminutes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Container class to store the tasks and provides helper methods.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object with no tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Given the user input, creates the respective Task subclass object.
     *
     * @param input The user input, for e.g, "todo borrow books"
     * @return The created Task object
     * @throws MissMinutesException If the user input is invalid
     */
    public Task createTask(String input) throws MissMinutesException {
        String[] split = input.split(" ", 2);
        String desc = split.length > 1 ? split[1] : "";

        try {
            if (split[0].equalsIgnoreCase("TODO")) {
                return Todo.fromStr(desc);
            } else if (split[0].equalsIgnoreCase("DEADLINE")) {
                return Deadline.fromStr(desc);
            } else if (split[0].equalsIgnoreCase("EVENT")) {
                return Event.fromStr(desc);
            } else {
                throw new MissMinutesException("Invalid command name, please try again!");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new MissMinutesException("Incomplete command, please try again!");
        }
    }

    /**
     * Adds a task to the ArrayList container and notifies the Ui.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        String reply = "Got it. I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.";
        Ui.sendMsg(reply);
    }

    /**
     * Deletes the respective task from the ArrayList container and notifies the Ui
     *
     * @param input The user input command. For e.g, `delete 1`
     * @throws MissMinutesException If the user input is in invalid format or if the idx is out of bounds.
     */
    public void deleteTask(String input) throws MissMinutesException {
        String[] split = input.split(" ");
        int idx;
        try {
            idx = Integer.parseInt(split[1]) - 1; // 0 indexed
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
            throw new MissMinutesException("Please enter a valid index. For e.g, a correct usage is: delete 2");
        }
        try {
            Task curr = this.tasks.get(idx);
            this.tasks.remove(idx);
            String reply = "Noted. I've removed this task:\n"
                    + curr + "\n"
                    + "Now you have " + this.tasks.size() + " tasks in the list.";
            Ui.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    /**
     * Marks the Task object at idx as done.
     *
     * @param idx The index of the task to be marked in the ArrayList
     * @throws MissMinutesException If the task doesn't exist or already marked as done
     */
    public void markTask(int idx) throws MissMinutesException {
        try {
            Task curr = this.tasks.get(idx);
            curr.markAsDone();
            String reply = "Nice! I've marked this task as done: \n" + curr;
            Ui.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    /**
     * Unmarks the Task object at idx
     *
     * @param idx The index of the task to be unmarked in the ArrayList
     * @throws MissMinutesException If the task doesn't exist or already marked as done
     */
    public void unmarkTask(int idx) throws MissMinutesException {
        try {
            Task curr = this.tasks.get(idx);
            curr.unmark();
            String reply = "OK, I've marked this task as not done yet: \n" + curr;
            Ui.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void findTask(String input) throws MissMinutesException {
        String[] split = input.split(" ", 2);
        if (split.length <= 1) {
            throw new MissMinutesException("Invalid find command format. Please enter a keyword, like `find book`");
        }
        String keyword = split[1];

        ArrayList<Task> results = this.tasks
                .stream()
                .filter(task -> task.description.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (results.isEmpty()) {
            throw new MissMinutesException("No tasks found =(");
        }

        StringBuilder reply = new StringBuilder("Here are the matching tasks in your list: ");
        for (int i = 0; i < results.size(); i++) {
            reply.append("\n")
                    .append((i + 1))
                    .append(". ")
                    .append(results.get(i));
        }
        Ui.sendMsg(reply.toString());
    }

    /**
     * Returns the string representation of the task to be visualized by the Ui
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.";
        }

        StringBuilder reply = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            reply.append("\n")
                    .append((i + 1))
                    .append(". ")
                    .append(tasks.get(i));
        }
        return reply.toString();
    }
}
