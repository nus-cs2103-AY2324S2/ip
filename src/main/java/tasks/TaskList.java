package tasks;

import java.util.ArrayList;
import java.util.List;


/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList {

    private static List<Task> taskList;


    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Obtain List object with the specified list of tasks.
     * @return the list of tasks in the TaskList
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list of tasks..
     * @param task the task to be added to the TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Constructs a TaskList object with the specified list of tasks.
     * @return the number of tasks in the TaskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Constructs a representation of the TaskList.
     * @return a String representation of the tasks in the TaskList
     */
    public String showList() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(i++).append(". ").append(task).append("\n");
        }
        return sb.toString();
    }


    /**
     * Gets a task at a specific index
     * @param index the index of the task to be retrieved
     * @return the task at the specified index
     */
    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a task at a specific index
     * @param index the index of the task to be deleted
     */
    public void deleteAtIndex(int index) {
            taskList.remove(index);
    }

    /**
     * Marks a task at a specific index as done
     * @param index the index of the task to be marked as done
     */
    public void markTask(int index) {
        taskList.get(index).mark();
    }

    /**
     * Unmarks a task at a specific index
     * @param index the index of the task to be unmarked
     */
    public void unmarkTask(int index) {
        taskList.get(index).unmark();
    }

    
    /**
     * Adds a deadline task to the TaskList
     * @param desc description of the task
     * @param start start time of the task
     */
    public void addDeadlineTask(String desc, String start) {
        Task deadlineTask = new Task(desc, false, start);
        taskList.add(deadlineTask);
    }

    /**
     * Adds an event task to the TaskList
     * @param desc description of the task
     * @param start start time of the task
     * @param end end time of the task
     */
    public void addEventTask(String desc, String start, String end) {
        Task eventTask = new Task(desc, false, start, end);
        taskList.add(eventTask);
    }

    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : taskList) {
            if (task.getDesc().contains(keyword)) {
                sb.append(i++).append(". ").append(task).append("\n");
            }
        }
        return sb.toString();
    }
}
