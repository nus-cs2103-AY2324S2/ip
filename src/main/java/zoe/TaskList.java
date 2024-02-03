package zoe;

import java.util.ArrayList;

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
        System.out.println("Got it. I've added this task:");
        System.out.println(t.getStatus());
        System.out.println(String.format("You now have %d tasks in the list", tasks.size()));
    }
    /**
     * Deletes a new task to the list, when delete(task) is called
     * @param i
     */
    public void delete(int i) {
            System.out.println("Noted! this task gon like pentagon:");
            System.out.println(tasks.get(i - 1).getStatus());
            tasks.remove(Integer.valueOf(i) - 1);
            System.out.println(String.format("There are %d tasks left in the list", tasks.size()));
    }

    /**
     * Marks task at a given index when markAsDone(index) is called
     * @param i
     */
    public void markAsDone(int i) {
        tasks.get(i - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(i - 1).getStatus());
    }

    /**
     * Unmarks the task at a given index when unmark(index) is called
     * @param i
     */
    public void unmark(int i) {
        tasks.get(i - 1).unmark();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(tasks.get(i - 1).getStatus());
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Checks if a task exists in the list given an index when isValid(index) is called.
     * @param i
     * @return
     */
    public boolean isValid(int i) {
        return (i < tasks.size() && i > 0);
    }
}
