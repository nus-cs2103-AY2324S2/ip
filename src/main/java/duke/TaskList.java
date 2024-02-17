package duke;

import java.util.ArrayList;

import duke.task.Task;
/**
 * The TaskList class represents a list of tasks in the Duke chatbot.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with tasks from an existing TaskList.
     *
     * @param existing The existing TaskList to copy tasks from.
     */
    public TaskList(TaskList existing) {
        this.taskList = existing.taskList;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (this.taskList.contains(task)) {
            throw new DukeException("this task already exists.");
        }
        this.taskList.add(task);
    }

    /**
     * Adds a task to the TaskList and marks it as done if needed.
     *
     * @param task The task to be added.
     * @param isDone Whether the task to be added is completed.
     */
    public void addTask(Task task, boolean isDone) {
        if (this.taskList.contains(task)) {
            throw new DukeException("this task already exists.");
        }
        if (isDone) {
            task.markAsDone();
        }
        this.taskList.add(task);
    }

    /**
     * Removes a task at the specified index from the TaskList.
     *
     * @param idx The index of the task to be removed.
     * @return The removed task.
     * @throws DukeException If the specified index is out of bounds.
     */
    public Task rmvTask(int idx) {
        try {
            return this.taskList.remove(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("there isn't an available task to delete.");
        }
    }

    /**
     * Retrieves a task at the specified index from the TaskList.
     *
     * @param idx The index of the task to be retrieved.
     * @return The retrieved task.
     * @throws DukeException If the specified index is out of bounds.
     */
    public Task getTask(int idx) {
        try {
            return this.taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("this task is not available/ does not exist.");
        }
    }

    /**
     * Retrieves the entire list of tasks from the TaskList in ArrayList form.
     *
     * @return The list of tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Retrieves the list of tasks from the TaskList that contains the search term.
     *
     * @param searchTerm The search input from the user.
     * @param currTasks The list of tasks the user currently have.
     * @return The list of tasks.
     */
    public TaskList searchTasks(String searchTerm, TaskList currTasks) {
        TaskList searchResults = new TaskList();

        currTasks.getTasks().forEach((task) -> {
            if (task.getDescription().contains(searchTerm)) {
                searchResults.addTask(task);
            }
        });

        return searchResults;
    }

    /**
     * Retrieves the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Prints the tasks in the TaskList with their corresponding indices.
     */
    public String stringifyTasks(boolean isSearch) {
        String tasklist;
        if (isSearch) {
            tasklist = "    Here are the matching tasks in your list:";
        } else {
            tasklist = "    Here are the items in your list: ";
        }

        for (int i = 0; i < this.taskList.size(); i++) {
            String listIdx = i + 1 + ". ";
            Task currTask = this.taskList.get(i);
            tasklist += "\n    " + listIdx + currTask;
        }

        return tasklist;
    }
}
