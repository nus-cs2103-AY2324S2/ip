package zhen;
import java.util.ArrayList;

import zhen.task.*;

/**
 * A system to store a list of task with additional functionality
 * such as inserting, deleting, marking, unmarkinng, etc.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    /**
     * Construct TaskList using an ArrayList of tasks
     *
     * @param tasks ArrayList of tasks
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskCount = this.tasks.size();
    }

    /**
     * Accessor to access the number of tasks stored in TaskList
     *
     * @return the number of tasks stored in the TaskList
     */
    public int accessNumberTask() {
        return taskCount;
    }

    /**
     * Access the ArrayList of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> accessList() {
        return tasks;
    }

    /**
     * Inserting a task into the TaskList
     *
     * @param task the task to be added
     */
    public void insert(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * delete a task from the TaskList with specified index
     *
     * @param index the index of task to be deleted
     * @return the string representation of the deleted task
     */
    public String delete(int index) {
        assert index > 0 && index < taskCount : "Delete index out of bound.";
        String deletedTaskDescription = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        taskCount--;
        assert taskCount >= 0 : "The number of tasks can't be negative.";
        return deletedTaskDescription;
    }

    /**
     * mark the task with specified index as done
     *
     * @param index the index of tasks to be marked as done
     * @return the string representation fo the marked task
     */
    public String mark(int index) {
        assert index > 0 && index < taskCount : "Mark index out of bound.";
        tasks.get(index - 1).mark();
        return tasks.get(index - 1).toString();
    }

    /**
     * unmark the task with specified index as not done
     *
     * @param index  the index of task to be resumed as not done
     * @return the string representation of the unmarked task
     */
    public String unmark(int index) {
        assert index > 0 && index < taskCount : "Unmark index out of bound.";
        tasks.get(index - 1).unmark();
        return tasks.get(index - 1).toString();
    }

    /**
     * Returns the string representation of the task list
     *
     * @return a string listing all the tasks with each prefixed with its index number
     */
    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                taskListString = taskListString + (i + 1) + ". " + tasks.get(i);
                break;
            }
            taskListString = taskListString + (i + 1) + ". " + tasks.get(i) + "\n ";
        }
        return taskListString;
    }
}
