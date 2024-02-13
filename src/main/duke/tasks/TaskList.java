package duke.tasks;

import duke.duke.Duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An encapsulation of a task list.
 *
 * @author Lim Zi Jia
 */
public class TaskList {
    /** List of tasks. */
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Prints a list containing the current tasks.
     */
    public String printList() {
        StringBuilder reply = new StringBuilder("Here are the tasks in your list:\n");
        int number = 1;
        for (Task t : this.list) {
            reply.append(String.format("%d. %s", number, t));
            number++;
        }

        return reply.toString();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public Task get(int idx) {
        return this.list.get(idx);
    }

    public int size() {
        return this.list.size();
    }

    public void set(int idx, Task t) {
        this.list.set(idx, t);
    }

    public Task remove(int idx) {
        return this.list.remove(idx);
    }

    public List<Task> getList() {
        return this.list;
    }

    /**
     * Marks a task as done.
     *
     * @param in The index of the task that should be marked as done.
     */
    public String mark(String in) {
        String s;
        try {
            int i = Integer.parseInt(in);
            Task t = Duke.tasks.get(i - 1);
            t.markAsDone();
            Duke.tasks.set(i - 1, t);
            s = "Nice! I've marked this task as done:\n  " + t;

        } catch (NumberFormatException e) {
            s = "Not a valid number!\n";
        } catch (IndexOutOfBoundsException e) {
            s = "Sorry, index out of range!\n";
        }

        return s;
    }

    /**
     * Marks a task as not done.
     *
     * @param in The index of the task that should be marked as not done.
     */
    public String unmark(String in) {
        String s;
        try {
            int i = Integer.parseInt(in);
            Task t = Duke.tasks.get(i - 1);
            t.markAsNotDone();
            Duke.tasks.set(i - 1, t);
            s = "OK, I've marked this task as not done yet:\n  " + t;

        } catch (NumberFormatException e) {
            s = "Not a valid number!\n";
        } catch (IndexOutOfBoundsException e) {
            s = "Sorry, index out of range!\n";
        }

        return s;
    }

    /**
     * Deletes a task from the list.
     *
     * @param in The index of the task that should be deleted.
     */
    public String delete(String in) {
        String s = "";
        try {
            int i = Integer.parseInt(in);
            Task t = Duke.tasks.remove(i - 1);
            s = "Noted. I've removed this task:\n  " + t;
            s += String.format("\nNow you have %d tasks in the list.\n", Duke.tasks.size());

        } catch (NumberFormatException e) {
            s = "Not a valid number! Or perhaps add a ' '\n";
        } catch (IndexOutOfBoundsException e) {
            s = "Sorry, index out of range!\n";
        }

        return s;
    }

    /**
     * Converts a list of tasks to a list of strings that are in the format used for saving.
     *
     * @return A list of strings that will be used for saving.
     */
    public List<String> taskToSavedString() {
        return list.stream()
                .map(Task::toSavedString)
                .collect(Collectors.toList());
    }

    /**
     * Finds if the substring is in the name of th list of tasks and print it if there is.
     *
     * @param in A string in the format of "find x" where x is the substring to find.
     */
    public String find(String in) {
        StringBuilder s = new StringBuilder("Here are the matching tasks in your list:\n");
        int number = 1;
        for (Task t : Duke.tasks.getList()) {
            if (t.has(in)) {
                s.append(String.format("%d. %s", number, t));
                number++;
            }
        }

        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        TaskList tl = (TaskList) obj;

        return Duke.tasks.equals(tl.list);
    }

}
