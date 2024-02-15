package duke.action;

import java.util.ArrayList;
import java.util.Iterator;

import duke.task.Task;
import duke.task.ToDo;


/**
 * Represents a list of tasks in the Duke application.
 */

public class TaskList implements Iterable<Task> {

    /**
     * The list containing tasks.
     */
    private final ArrayList<Task> mylist;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.mylist = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return mylist.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index or null if the index is invalid.
     */
    public Task get(int index) {
        if (index >= 0 && index < mylist.size()) {
            return mylist.get(index);
        }
        return null;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < mylist.size()) {
            System.out.println("Noted. I've removed this task:\n" + mylist.get(index).toString()
                    + "\nNow you have " + (mylist.size() - 1) + " tasks in the list.");
            return mylist.remove(index);
        } else {
            return null;
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        mylist.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.size() + " tasks in the list.");
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        if (validateIndex(index)) {
            Task taskToMark = mylist.get(index);
            taskToMark.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToMark);
        }
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        if (validateIndex(index)) {
            Task taskToUnmark = mylist.get(index);
            taskToUnmark.unmark();
            System.out.println("OK, I've marked this task as not done yet:" + taskToUnmark.toString());
            System.out.println(taskToUnmark);
        }
    }

    /**
     * Displays all tasks in the list.
     */
    public void displayTasks() {
        System.out.print("");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < mylist.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + mylist.get(i));
        }
    }

    /**
     * Validates the given index.
     *
     * @param index The index to be validated.
     * @return True if the index is valid, false otherwise.
     */
    public boolean validateIndex(int index) {
        if (index >= 0 && index < mylist.size()) {
            return true;
        } else {
            //System.out.println("Invalid task index. Please provide a valid index.");
            return false;
        }
    }

    /**
     * Prints a goodbye message.
     */
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a welcome message.
     */
    public void hello() {
        System.out.println(" Hello I'm NoisyChatter");
        System.out.println(" What can I do for you?");
    }
    /**
     * Finds the matches.
     */
    public void matches(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : mylist) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }

    /**
     * Provides an iterator over the tasks in the list.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return mylist.iterator();
    }

    public boolean contains(Task task) {
        for (Task t : mylist) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the response message containing the list of tasks.
     *
     * @return A string representing the response message.
     */
    public String response() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < mylist.size(); i++) {
            stringBuilder.append("  ").append(i + 1).append(". ").append(mylist.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

}




