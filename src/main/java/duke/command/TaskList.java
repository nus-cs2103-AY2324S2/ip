package duke.command;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;

/**
 * Contains task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * TaskList constructor.
     *
     * @param tasks     ArrayList of tasks loaded from file.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Print all tasks in task list.
     */
    public void printTaskList() {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Mark task as complete.
     *
     * @param index     Index of task.
     * @return  task    Updated task.
     */
    public Task markTask(int index) {
        tasks.get(index).updateIsDone(true);
        return tasks.get(index);
    }

    /**
     * Mark task as uncompleted.
     *
     * @param index     Index of task.
     * @return  task    Updated task.
     */
    public Task unmarkTask(int index) {
        tasks.get(index).updateIsDone(false);
        return tasks.get(index);
    }

    /**
     * Add new ToDo task.
     *
     * @param name      Task name or description of task.
     * @return task     New ToDo task.
     */
    public Task addTodo(String name) {
        Task task = new ToDo(name);
        tasks.add(task);

        return task;
    }

    /**
     * Add new Deadline task.
     *
     * @param name      Task name or description of task.
     * @param by        Date and time task has to be completed by.
     * @return task     New Deadline task.
     */
    public Task addDeadline(String name, String by) {
        Task task = new Deadline(name, by);
        tasks.add(task);

        return task;
    }

    /**
     * Add new Event task.
     *
     * @param name      Task name or description of task.
     * @param from      Date and time the event starts.
     * @param to        Time the event ends.
     * @return task     New Deadline task.
     */
    public Task addEvent(String name, String from, String to) {
        Task task = new Event(name, from, to);
        tasks.add(task);

        return task;
    }

    /**
     * Delete task from task list.
     *
     * @param index     Index of task.
     * @return task     Deleted task.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);

        return task;
    }

    /**
     * Print all matching tasks.
     *
     * @param match     String to compare with task description.
     */
    public void findMatchingTasks(String match) {
        for (Task t : tasks) {
            if (t.isMatchingDescription(match)) {
                System.out.println(t.toString());
            }
        }
    }

    /**
     * Retrieve the number of tasks in the task list.
     *
     * @return numOfTasks   Number of tasks in the task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }
}