package zoe;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores all tasks that is manipulated by zoe
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tl) {
        this.tasks = tl;
    }

    /**
     * Adds a new task to the list, when add(task) is called
     * @param t
     */
    public void add(Task t) {
        this.tasks.add(t);
        Collections.sort(tasks, new PriorityComparator());
    }
    /**
     * Deletes a new task to the list, when delete(task) is called
     * @param i
     */
    public void delete(int i) {
        tasks.remove(Integer.valueOf(i) - 1);
        Collections.sort(tasks, new PriorityComparator());
    }

    /**
     * Marks task at a given index when markAsDone(index) is called
     * @param i
     */
    public void markAsDone(int i) {
        tasks.get(i - 1).markAsDone();
    }

    /**
     * Unmarks the task at a given index when unmark(index) is called
     * @param i
     */
    public void unmark(int i) {
        tasks.get(i - 1).unmark();
    }

    /**
     * Returns tasks for interactions with parser
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns size of task list so that UI can inform user about the number of tasks they have in their list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns task at specified index for marking, unmarking and deletion
     */
    public Task get(int i) {
        return this.tasks.get(i - 1);
    }

}