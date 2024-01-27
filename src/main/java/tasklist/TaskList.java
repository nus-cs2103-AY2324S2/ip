package tasklist;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.UI;

import exceptions.InvalidStatusUpdateException;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods
 * to add, retrieve, delete, and fetch tasks from the list.
 */
public class TaskList {

    /** The list of tasks managed by the TaskList. */
    private List<Task> tasks;

    /**
     * Constructs a TaskList based on the data provided.
     * Parses the input data and initializes the list of tasks accordingly.
     * @param data The list of strings representing task data.
     */
    public TaskList(List<String> data) {
        this.tasks = new ArrayList<>();
        for (String line : data) {
            String[] words = line.split(",");
            try {
                if (words[0].equals("todo")) {
                    Todo todo = new Todo(words[1]);
                    if (words[2].equals("true")) {
                        todo.updateStatus(true);
                    }
                    this.tasks.add(todo);
                } else if (words[0].equals("deadline")) {
                    Deadline deadline = new Deadline(words[1], LocalDate.parse(words[2]));
                    if (words[3].equals("true")) {
                        deadline.updateStatus(true);
                    }
                    this.tasks.add(deadline);
                } else if (words[0].equals("event")) {
                    Event event = new Event(words[1], LocalDate.parse(words[2]), LocalDate.parse(words[3]));
                    if (words[4].equals("true")) {
                        event.updateStatus(true);
                    }
                    this.tasks.add(event);
                }
            } catch (InvalidStatusUpdateException e) {
                UI.print(e.getMessage());
            }
        }
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     * @return true if the task is successfully added.
     */
    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    /**
     * Retrieves a task from the TaskList based on the specified index.
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes a task from the TaskList based on the specified index.
     * @param index The index of the task to be deleted.
     * @return The task that was removed from the TaskList.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     * @return The size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Fetches all tasks from the TaskList.
     * @return A List containing all tasks in the TaskList.
     */
    public List<Task> fetchAll() {
        return this.tasks;
    }
}