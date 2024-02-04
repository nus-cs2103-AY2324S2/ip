package duke;

import duke.task.Task;

import java.util.ArrayList;
/**
 * Abstraction of tasks lists.
 * Inherits from ArrayList<Tasks>.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Adds Task to List.
     * @param t Task
     */
    void addToList(Task t) {
        this.add(t);
        System.out.println("Got it. I've added this duke.task:");
        System.out.println(t);
        countTasks();
    }

    /**
     * Displays list in chatbot friendly output format.
     */
    void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i+1 + ":" + this.get(i));
        }
    }

    /**
     * Marks an item in the list as done.
     * @param i task number to mark as done
     */
    void markComplete(int i) {
        System.out.println("Nice! I've marked this duke.task as done:");
        this.get(i-1).markComplete();
        System.out.println(this.get(i-1));
    }

    /**
     * Marks an item in the list as incomplete.
     * @param i task number to mark as incompletec
     */
    void unmarkComplete(int i) {
        System.out.println("OK, I've marked this duke.task as not done yet:");
        this.get(i-1).unmarkComplete();
        System.out.println(this.get(i-1));
    }

    /**
     * Deletes a task from the list.
     * @param i task number to mark as incomplete
     */
    void deleteTask(int i) {
        Task t = this.remove(i-1);
        System.out.println("Noted. I've removed this duke.task:");
        System.out.println(t);
        countTasks();
    }

    /**
     * Counts the number of tasks.
     * Outputs the number in the chatbot.
     */
    void countTasks() {
        System.out.println("Now you have " + this.size() + " tasks in the list.");
    }
}
