package chimp.core;
import java.time.LocalDate;
import java.util.ArrayList;

import chimp.task.Deadline;
import chimp.task.Event;
import chimp.task.Task;
import chimp.task.TaskStatus;
import chimp.task.Todo;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task>{

    
    /**
     * Represents a list of tasks.
     */
    public TaskList() {
        super();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void add(String task) {
        super.add(new Todo(task, TaskStatus.UNMARKED));
    }

    /**
     * Adds a task with a deadline to the task list.
     * 
     * @param task the description of the task
     * @param by the deadline of the task
     */
    public void add(String task, LocalDate by) {
        super.add(new Deadline(task, TaskStatus.UNMARKED, by));
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param task the description of the task
     * @param from the starting date of the event
     * @param to the ending date of the event
     */
    public void add(String task, LocalDate from, LocalDate to) {
        super.add(new Event(task, TaskStatus.UNMARKED, from, to));
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return a string representation of the TaskList
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            sb.append(i + 1).append(". ").append(this.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Finds tasks in the task list that contain the specified keyword.
     *
     * @param keyword the keyword to search for in the task descriptions
     * @return an array of tasks that contain the specified keyword
     */
    public Task[] find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).toString().contains(keyword)) {
                matches.add(this.get(i));
            }
        }
        return matches.toArray(new Task[0]);
    }

    /**
     * Returns the number of elements in this TaskList.
     *
     * @return the number of elements in this TaskList
     */
    public int size() {
        return super.size();
    }
}