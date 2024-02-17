package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private Storage storage;
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    /**
     * Constructs a new TaskList object with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with a given task list and storage.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage to use.
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Constructs a new TaskList object with a given storage and loads tasks from
     * the storage.
     *
     * @param storage The storage to use.
     * @throws DukeException If an error occurs while loading tasks.
     */
    public TaskList(Storage storage) throws DukeException {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            throw new DukeException("Error loading tasks\n");
        }
    }

    /**
     * Adds a task to the list and updates the storage.
     *
     * @param task The task to add.
     * @throws DukeException If an error occurs while updating the storage.
     */
    public String addTask(String task) throws DukeException {
        String command = parser.parseCommand(task);
        Task newTask = null;
        if (command.equals("todo")) {
            String description = parser.parseDescription(task);
            if (description.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            } else {
                newTask = new ToDo(description);
            }
        } else if (command.equals("deadline")) {
            String[] deadline = parser.parseDeadline(task);
            newTask = new Deadline(deadline[0], deadline[1]);
        } else if (command.equals("event")) {
            String[] event = parser.parseEvent(task);
            newTask = new Event(event[0], event[1], event[2]);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
        }

        tasks.add(newTask);
        updateStorage();
        return ("Got it. I've added this task: \n" + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * Displays all tasks in the list.
     */
    public String displayTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Marks a task as done and updates the storage.
     *
     * @param index The index of the task to mark as done.
     * @throws DukeException If an error occurs while updating the storage.
     */
    public String markTaskAsDone(int index) throws DukeException {
        String output = tasks.get(index - 1).markAsDone();
        try {
            updateStorage();
            return output;
        } catch (DukeException e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    /**
     * Unmarks a task as done and updates the storage.
     *
     * @param index The index of the task to unmark as done.
     * @throws DukeException If an error occurs while updating the storage.
     */
    public String unmarkTaskAsDone(int index) throws DukeException {
        String output = tasks.get(index - 1).unmarkDone();
        try {
            updateStorage();
            return output;
        } catch (DukeException e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    /**
     * Deletes a task from the list and updates the storage.
     *
     * @param index The index of the task to delete.
     * @throws DukeException If an error occurs while updating the storage.
     */
    public String deleteTask(int index) throws DukeException {
        Task removed = tasks.remove(index - 1);
        String output = ("Noted. I've removed this task: \n" + removed.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.\n");
        try {
            updateStorage();
            return output;
        } catch (DukeException e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    /**
     * Updates the storage with the current list of tasks.
     *
     * @throws DukeException If an error occurs while updating the storage.
     */
    public void updateStorage() throws DukeException {
        try {
            storage.save(tasks);
            ui.printMessage("Tasks updated in storage\n");
        } catch (Exception e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    public List<Task> findTasksFromKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}