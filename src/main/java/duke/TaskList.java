package duke;

import duke.task.Task;

import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {
    void addToList(String s) {
        this.add(new Task(s));
        System.out.println("added: " + s);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param t Task to be added.
     */
    void addToList(Task t) {
        this.add(t);
        System.out.println("Got it. I've added this duke.task:");
        System.out.println(t);
        countTasks();
    }

    /**
     * Displays the tasks in the task list.
     * Tasks are numbered and each begin on a new line
     */
    void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i+1 + ":" + this.get(i));
        }
    }

    /**
     * Marks a Task on the TaskList as complete.
     *
     * @param i index of the task to be marked as complete.
     */
    void markComplete(int i) {
        System.out.println("Nice! I've marked this duke.task as done:");
        this.get(i-1).markComplete();
        System.out.println(this.get(i-1));
    }

    /**
     * Marks a Task on the TaskList as incomplete.
     *
     * @param i index of the task to be marked as incomplete.
     */
    void unmarkComplete(int i) {
        System.out.println("OK, I've marked this duke.task as not done yet:");
        this.get(i-1).unmarkComplete();
        System.out.println(this.get(i-1));
    }

    /**
     * Deletes a Task on the TaskList.
     *
     * @param i index of the task to be deleted.
     */
    void deleteTask(int i) {
        Task t = this.remove(i-1);
        System.out.println("Noted. I've removed this duke.task:");
        System.out.println(t);
        countTasks();
    }

    /**
     * Counts the number of Tasks on the TaskList.
     */
    void countTasks() {
        System.out.println("Now you have " + this.size() + " tasks in the list.");
    }
}
