package duke.command;

import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Contains task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
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
     * Prints all tasks in task list.
     * @return allTasks     String of all tasks in the task list.
     */
    public String printTaskList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            stringBuilder.append(i + 1);
            stringBuilder.append(".");
            stringBuilder.append(tasks.get(i).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Marks task as complete.
     *
     * @param index     Index of task.
     * @return  task    Updated task.
     */
    public Task markTask(int index) {
        tasks.get(index).updateIsDone(true);
        return tasks.get(index);
    }

    /**
     * Marks task as uncompleted.
     *
     * @param index     Index of task.
     * @return  task    Updated task.
     */
    public Task unmarkTask(int index) {
        tasks.get(index).updateIsDone(false);
        return tasks.get(index);
    }

    /**
     * Adds new ToDo task.
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
     * Adds new Deadline task.
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
     * Adds new Event task.
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
     * Deletes task from task list.
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
     * Prints all matching tasks.
     *
     * @param match                 String to compare with task description.
     * @return allMatchingTasks     String of all tasks that contain/match the task description.
     */
    public String findMatchingTasks(String match) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task t : tasks) {
            if (t.isMatchingDescription(match)) {
                stringBuilder.append(t);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return numOfTasks   Number of tasks in the task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }
}
