package dude.tasks;

import java.util.ArrayList;

import dude.exceptions.DudeException;
import dude.exceptions.TaskListFullException;

/**
 * The TaskList class handles keeping track of the list of tasks.
 * It also provides various methods to interact with the list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> list;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Static method to create a TaskList object from an ArrayList of Task objects.
     *
     * @param tasks The ArrayList of Task objects to be converted into a TaskList object.
     * @return The TaskList object created from the ArrayList of Task objects.
     */
    public static TaskList from(ArrayList<Task> tasks) {
        assert (tasks != null);

        TaskList taskList = new TaskList();
        taskList.list.addAll(tasks);
        return taskList;
    }

    /**
     * Static method to create a TaskList object from an arbitrary number of tasks.
     *
     * @param tasks Task objects to be put into a TaskList object.
     * @return The TaskList object created from the Task objects.
     */
    public static TaskList from(Task... tasks) {
        assert (tasks != null);

        TaskList taskList = new TaskList();
        for (Task task : tasks) {
            taskList.list.add(task);
        }
        return taskList;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     * @throws TaskListFullException if the task list is full.
     */
    public void add_task(Task task) throws TaskListFullException {
        assert (task != null);
        if (list.size() >= 100) {
            throw new TaskListFullException("Sorry, the task list is full.");
        }
        list.add(task);
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param taskID The id of the task to be removed from the list of tasks.
     * @throws DudeException if the task id is invalid.
     */
    public void remove_task(int taskID) throws DudeException {
        if (taskID > list.size() || taskID < 1) {
            throw new DudeException("Sorry, the provided id is invalid.");
        }
        Task removed = list.remove(taskID - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param id The id of the task to be marked as done.
     * @throws DudeException if the task id is invalid.
     */
    public void mark_as_done(int id) throws DudeException {
        if (id > list.size() || id < 1) {
            throw new DudeException("Sorry, the provided id is invalid.");
        }
        list.get(id - 1).markAsDone();
    }

    /**
     * Marks a task as undone.
     *
     * @param id The id of the task to be marked as undone.
     * @throws DudeException if the task id is invalid.
     */
    public void mark_as_undone(int id) throws DudeException {
        if (id > list.size() || id < 1) {
            throw new DudeException("Sorry, the provided id is invalid.");
        }
        list.get(id - 1).markAsUndone();
    }

    /**
     * Finds tasks whose string representation matches with the given keyword.
     *
     * @param keyword The keyword to be used to find tasks.
     * @return An ArrayList of Task objects that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        assert (keyword != null);
        ArrayList<Task> result = new ArrayList<>();
        String taskString;
        String keywordLowerCase = keyword.toLowerCase();
        for (Task task : list) {
            taskString = task.toString();
            taskString = taskString.toLowerCase();

            if (taskString.contains(keywordLowerCase)) {
                result.add(task);
            }
        }
        return result;
    }


    /**
     * Returns a deep copy of the list of tasks
     * @return An ArrayList of Task objects
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> copy = new ArrayList<>();
        for (Task task : list) {
            if (task instanceof Deadline) {
                copy.add(new Deadline(task.getDescription(), ((Deadline) task).getBy()));
            } else if (task instanceof Event) {
                copy.add(new Event(task.getDescription(), ((Event) task).getFromTime(), ((Event) task).getToTime()));
            } else if (task instanceof Todo) {
                copy.add(new Todo(task.getDescription()));
            } else {
                copy.add(new Task(task.getDescription()));
            }
        }
        return copy;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task with the given id.
     *
     * @param taskID The id of the task to be returned.
     * @return The task with the given id.
     * @throws IndexOutOfBoundsException if the task id is invalid.
     */
    public Task getTask(int taskID) throws IndexOutOfBoundsException {
        if (taskID <= 0 || taskID > list.size()) {
            throw new IndexOutOfBoundsException("Sorry, the provided id is invalid. "
                    + "Use the list command to see the list of tasks.");
        }
        return list.get(taskID - 1);
    }

    /**
     * Returns a string representation of the list of tasks.
     *
     * @return A string representation of the list of tasks.
     */
    @Override
    public String toString() {
        if (list.size() == 0) {
            return "No tasks in the list! :(";
        }

        String result = "";
        for (int i = 0; i < list.size(); i++) {
            //if it is the last task, do not add a new line
            if (i == list.size() - 1) {
                result += (i + 1) + ". " + list.get(i).toString();
                break;
            }
            result += (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return result;
    }
}
