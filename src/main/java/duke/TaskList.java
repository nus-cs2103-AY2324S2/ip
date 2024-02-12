package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList Class to manage and perform actions to Tasks
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Adds a Task to the TaskList.
     *
     * @param t Task to be added.
     * @return String
     */
    String addToList(Task t) {
        this.add(t);
        return "Got it. I've added this duke.task:" + "\n" + t + "\n" + countTasks();

    }

    /**
     * Displays the tasks in the task list.
     * Tasks are numbered and each begin on a new line
     */
    void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i + 1 + ":" + this.get(i));
        }
    }

    /**
     * Returns the tasks in the task list as a String.
     * Tasks are numbered and each begin on a new line
     *
     * @return String
     */
    String getTasks() {
        if (this.isEmpty()) {
            return "There are no tasks in your list.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.size(); i++) {
            sb.append(i + 1 + ":" + this.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Marks a Task on the TaskList as complete.
     *
     * @param i index of the task to be marked as complete.
     * @return String
     */
    String markComplete(int i) {
        this.get(i - 1).markComplete();
        return "Nice! I've marked this duke.task as done:" + "\n" + this.get(i - 1);
    }

    /**
     * Marks a Task on the TaskList as incomplete.
     *
     * @param i index of the task to be marked as incomplete.
     * @return String
     */
    String unmarkComplete(int i) {
        this.get(i - 1).unmarkComplete();
        return "OK, I've marked this duke.task as not done yet:" + "\n" + this.get(i - 1);
    }

    /**
     * Deletes a Task on the TaskList.
     *
     * @param i index of the task to be deleted.
     * @return String
     */
    String deleteTask(int i) {
        Task t = this.remove(i - 1);
        return "Noted. I've removed this duke.task:" + "\n" + t + "\n" + countTasks();
    }

    /**
     * Counts the number of Tasks on the TaskList.
     * @return String
     */
    String countTasks() {
        return "Now you have " + this.size() + " tasks in the list.";
    }
}
