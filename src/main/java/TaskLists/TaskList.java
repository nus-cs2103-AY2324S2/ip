package TaskLists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Tasks.Task;


/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;
    private final HashSet<String> duplicates = new HashSet<>();

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param list The list of tasks to be included in the TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t The task to be added.
     */
    public int addToList(Task t) {
        boolean isDuplicate = duplicates.contains(t.toString());
        System.out.println(isDuplicate);
        if (isDuplicate) {
            System.out.println("duplcate detected");
            return -1;
        }
        tasks.add(t);
        duplicates.add(t.toString());
        return 0;
    }

    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return A string representation of all tasks in the TaskList.
     */
    public String showLists() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
            System.out.println(tasks.get(i));
        }
        for (String x : duplicates){
            System.out.println(x);
        }
        return sb.toString();
    }
    /**
     * Returns a string representation of tasks with specific keyword
     * @param keyWord the keyword to be found
     * @return A string representation of such tasks.
     */
    public String find(String keyWord) throws IllegalArgumentException {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            if (t.toString().toLowerCase().indexOf(keyWord) != -1) {
                sb.append(i).append('.').append("\t").append(t).append("\n");
                i += 1;
            }
        }
        if (sb.toString().isEmpty()) {
            throw new IllegalArgumentException("There is no such keyword found in the list");
        }
        return sb.toString();
    }

    /**
     * Removes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return A string representation of the removed task.
     * @throws IllegalArgumentException If the index is invalid.
     */
    public String removeTask(int index) throws IllegalArgumentException {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Please input a valid number. "
                                             + "To see the available number(s) of your task, type 'list'.");
        } else {
            Task t = tasks.get(index - 1);
            tasks.remove(index - 1);
            duplicates.remove(t);
            return t.toString();
        }
    }

    /**
     * Marks a task as done in the TaskList.
     *
     * @param taskNumber The number of the task to be marked as done.
     * @return A string representation of the marked task.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    public String showMark(int taskNumber) throws IllegalArgumentException {
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input a valid number. "
                                             + "To see the available number(s) of your task, type 'list'.");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsDone();
            return t.toString();
        }
    }

    /**
     * Marks a task as not done in the TaskList.
     *
     * @param taskNumber The number of the task to be marked as not done.
     * @return A string representation of the unmarked task.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    public String showUnmark(int taskNumber) throws IllegalArgumentException {
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input a valid number. "
                                             + "To see the available number(s) of your task, type 'list'.");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsNotDone();
            return t.toString();
        }
    }

    /**
     * Checks if the given index is valid.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    /**
     * Returns an iterator over the elements in the TaskList.
     *
     * @return An iterator over the elements in the TaskList.
     */
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public void clearTask() {
        tasks.clear();
        duplicates.clear();
    }
}
