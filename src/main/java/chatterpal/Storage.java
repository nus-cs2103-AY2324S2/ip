package chatterpal;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.ChatterExceptions;


/**
 * The Storage class represents a storage facility for tasks.
 */
public class Storage {

    /**
     * The list to store tasks.
     */
    private ArrayList<Task> storage;

    /**
     * Constructs a new Storage instance with an empty task list.
     */

    public Storage() {
        this.storage = new ArrayList<Task>();
    }

    /**
     * Adds a task to the storage.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.storage.add(t);
    }

    /**
     * Retrieves the number of tasks in the storage.
     *
     * @return The number of tasks in the storage.
     */
    public int size() {
        return this.storage.size();
    }

    /**
     * Retrieves a task from the storage at the specified index.
     *
     * @param i The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.storage.get(i);
    }

    /**
     * Retrieves the index of a task in the storage based on its description.
     *
     * @param s The description of the task.
     * @return The index of the task in the storage.
     */
    public int indexOf(String s) {
        return this.storage.indexOf(s);
    }


    /**
     * Removes and retrieves a task from the storage at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task pop(int index) {
        Task t = this.storage.get(index);
        this.storage.remove(index);
        return t;
    }

    /**
     * Prints the tasks matching a search query, surrounded by a separator line.
     *
     * @param s The string representing the matching tasks.
     */
    public String find(String s) {
        StringBuilder output = new StringBuilder();
        try {
            for (int i = 0; i < this.storage.size(); i++) {
                if (this.storage.get(i).description.contains(s)) {
                    output.append(String.format("%d. ", i + 1));
                    output.append(this.storage.get(i).toString()).append("\n");
                }
            }
            if (output.length() == 0) {
                throw new ChatterExceptions();
            }
            return output.toString();

        } catch (ChatterExceptions d) {
            return "Nothing was found";
        }
    }

    /**
     * Creates a formatted string representing the list of tasks in the storage.
     *
     * @return The formatted string representing the list of tasks.
     */
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.storage.size(); i++) {
            output.append(String.format("%d. ", i + 1));
            output.append(this.storage.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Creates a formatted string representing the output when a task is added to the storage.
     *
     * @param t The task that was added.
     * @return The formatted string representing the output.
     */
    public String addToListOutput(Task t) {
        return "Got it. I've added this task:\n"
                + String.format("  %s\nNow you have %d tasks in the list.", t.toString(), this.size());
    }


    /**
     * Generates a string representation of all scheduled deadlines and events for a given date.
     * This method iterates through a storage list, checking each stored item to see if it matches
     * the target date. For deadlines, it compares the deadline date directly with the target.
     * For events, it checks if the target date falls within the event's duration (inclusive of start and end dates).
     * The descriptions of all matching items are appended to the output.
     *
     * @param target The {@link LocalDate} representing the date for which the schedule is to be viewed.
     * @return A string containing a newline-separated list of descriptions for all deadlines and
     *         events occurring on the target date. If no items are scheduled for that date or an exception occurs,
     *         an empty string is returned.
     */
    public String viewSchedule(LocalDate target) {
        StringBuilder output = new StringBuilder();
        try {
            for (int i = 0; i < this.storage.size(); i++) {
                if (this.storage.get(i) instanceof Deadline) {
                    LocalDate deadlineDate = ((Deadline) this.storage.get(i)).getDeadline().toLocalDate();
                    if (deadlineDate.equals(target)) {
                        output.append(this.storage.get(i).description + "\n");
                    }
                } else if (this.storage.get(i) instanceof Event) {
                    LocalDate toDate = ((Event) this.storage.get(i)).getTo().toLocalDate();
                    LocalDate fromDate = ((Event) this.storage.get(i)).getFrom().toLocalDate();
                    if (target.isAfter(fromDate) && target.isBefore(toDate)
                            || target.equals(toDate) || target.equals(fromDate)) {
                        output.append(this.storage.get(i).description + "\n");
                    }
                }
            }
            return output.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
