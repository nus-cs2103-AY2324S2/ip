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
     * @param input The index of the task that should be marked as done.
     */
    public String mark(String[] input) {
        StringBuilder s = new StringBuilder();
        String forPrintingError = "";
        try {
            // Start at 1 beacuse indexes start at 1. Index 0 is "mark"
            for (int i = 1; i < input.length; i++) {
                forPrintingError = input[i];
                int idx = Integer.parseInt(input[i]);
                Task t = Duke.tasks.get(idx - 1);
                t.markAsDone();
                Duke.tasks.set(idx - 1, t);
                s.append("Nice! I've marked this task as done:\n  ").append(t).append('\n');
            }
        } catch (NumberFormatException e) {
            s = new StringBuilder(forPrintingError + " is not a valid number!\n");
        } catch (IndexOutOfBoundsException e) {
            s = new StringBuilder(forPrintingError + "--> index out of range!\n");
        }

        return s.toString();
    }

    /**
     * Marks a task as not done.
     *
     * @param input The index of the task that should be marked as not done.
     */
    public String unmark(String[] input) {
        StringBuilder s = new StringBuilder();
        String forPrintingError = "";
        try {
            // Start at 1 beacuse indexes start at 1. Index 0 is "unmark"
            for (int i = 1; i < input.length; i++) {
                forPrintingError = input[i];
                int idx = Integer.parseInt(input[i]);
                Task t = Duke.tasks.get(idx - 1);
                t.markAsNotDone();
                Duke.tasks.set(idx - 1, t);
                s.append("OK, I've marked this task as not done yet:\n  ").append(t).append('\n');
            }
        } catch (NumberFormatException e) {
            s = new StringBuilder(forPrintingError + " is not a valid number!\n");
        } catch (IndexOutOfBoundsException e) {
            s = new StringBuilder(forPrintingError + "--> index out of range!\n");
        }

        return s.toString();
    }

    /**
     * Deletes a task from the list.
     *
     * @param input The index of the task that should be deleted.
     */
    public String delete(String[] input) {
        StringBuilder s = new StringBuilder();
        String forPrintingError = "";
        try {
            // Start at 1 beacuse indexes start at 1. Index 0 is "delete"
            for (int i = 1; i < input.length; i++) {
                forPrintingError = input[i];
                int idx = Integer.parseInt(input[i]);
                Task t = Duke.tasks.remove(idx - 1);
                s.append("Noted. I've removed this task:\n  ").append(t);
                s.append(String.format("Now you have %d tasks in the list.\n\n", Duke.tasks.size()));
            }
        } catch (NumberFormatException e) {
            s = new StringBuilder(forPrintingError + " is not a valid number! Or perhaps add a ' '\n");
        } catch (IndexOutOfBoundsException e) {
            s = new StringBuilder(forPrintingError + "--> index out of range!\n");
        }

        return s.toString();
    }

    /**
     * Converts a list of tasks to a list of strings that are in the format used for saving.
     *
     * @return A list of strings that will be used for saving.
     */
    public List<String> taskToSavedString() {
        return list.stream()
                .map(Task::taskToSavedString)
                .collect(Collectors.toList());
    }

    /**
     * Finds if the substring is in the name of th list of tasks and print it if there is.
     *
     * @param input A string in the format of "find x" where x is the substring to find.
     */
    public String find(String input) {
        StringBuilder s = new StringBuilder("Here are the matching tasks in your list:\n");
        int number = 1;
        for (Task t : Duke.tasks.getList()) {
            if (t.has(input)) {
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
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }

        TaskList tl = (TaskList) obj;
        return this.list.equals(tl.list);
    }

}
