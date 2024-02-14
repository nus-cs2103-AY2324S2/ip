package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Encapsulates the list of tasks that the user is currently tracking. Analogous to an ArrayList and supports
 * the relevant operations.
 */
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns a string representation of this list. Intended to be printed as the chatbot's response
     *
     * @return A string containing the tasks in the list, generated with each tasks' describe method.
     */
    public String toDisplayString() {
        // here's your """""effectively final""""" value bro
        var numBox = new Object() {
            int num = 1;
        };
        return this.tasks.stream()
                .reduce(new StringBuilder(),
                        (curr, acc) -> {
                            curr.append(numBox.num).append(".").append(acc.describe());
                            if (numBox.num < tasks.size()) {
                                curr.append("\n");
                            }
                            numBox.num++;
                            return curr;
                        },
                        StringBuilder::append)
                .toString();
    }

    /**
     * Returns a string representation of this list that is intended to be stored as a database.
     *
     * @return A string containing the tasks in the list, using the tasks' toStorageString method.
     */
    public String toStorageString() {
        StringBuilder sb = new StringBuilder();

        for (Task t : this.tasks) {
            sb.append(t.toStorageString()).append('\n');
        }
        return sb.toString();
    }

    /**
     * Retrieves the task at the specified zero-based index. similar to ArrayList::get
     *
     * @param i The index of the task to retrieve.
     * @return The task the index i.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task get(int i) throws IndexOutOfBoundsException {
        return this.tasks.get(i);
    }

    /**
     * Adds a new task to the list. similar to ArrayList::add
     *
     * @param t The task to be added to the list.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Deletes the task at the specified zero-based index. similar to ArrayList::remove, except it does not return
     * the removed task.
     *
     * @param i The index of the task to be removed.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void remove(int i) throws IndexOutOfBoundsException {
        this.tasks.remove(i);
    }

    public String filterSubString(String toFind) {
        return Stream.iterate(0, i -> i < this.tasks.size(), i -> i + 1)
                .filter(i -> this.tasks.get(i).nameContains(toFind))
                .reduce(new StringBuilder(),
                        (sb, curr) -> sb
                                .append(curr + 1).append(".")
                                .append(this.tasks.get(curr).describe())
                                .append(curr < tasks.size() - 1 ? "\n" : ""),
                        StringBuilder::append)
                .toString();
    }
}
