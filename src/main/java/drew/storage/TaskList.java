package drew.storage;

import java.util.ArrayList;

import drew.task.Deadline;
import drew.task.Task;

/**
 * This class represents the task list structure.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for creating an empty task list;
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for creating a task list from an existing list.
     * @param ls
     */
    public TaskList(ArrayList<Task> ls) {
        this.tasks = ls;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
    public String getTaskList() {
        int listLength = tasks.size();
        String reply = "";
        for (int i = 0; i < listLength; i++) {
            reply = reply + Integer.toString(i + 1) + ". "
                    + tasks.get(i).toStatusString() + "\n";
        }
        return reply;
    }

    /**
     * Adds the task to the tasklist.
     * @param task Task object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Lists all deadlines that are due today or after today.
     * @return String containing all deadlines due soon.
     */
    public String getUpcoming() {
        String reply = "";
        TaskList deadlines = new TaskList();
        for (Task task : tasks) {
            if (!(task instanceof Deadline)) {
                continue;
            }
            Deadline deadline = (Deadline) task;
            if (deadline.isUpcoming()) {
                deadlines.add(deadline);
            }
        }
        reply += deadlines.getTaskList();
        return reply;
    }

    /**
     * Checks if the task has been created before.
     * @param task New task to be added to the list.
     * @return Boolean indicating whether new task already exists.
     */
    public boolean isDuplicate(Task task) {
        boolean duplicate = false;
        for (Task existingTask : tasks) {
            if (existingTask.isEqual(task)) {
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
}
