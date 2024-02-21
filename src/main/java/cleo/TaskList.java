package cleo;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Represents a collection of Task objects. Provides methods for managing, searching, and
 * retrieving tasks within the list.
 */
public class TaskList {
    /**
     *  The internal ArrayList used to store Task objects.
     */
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList with an optionally provided initial list of tasks.
     *
     * @param tasks An optional initial ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a new task to the TaskList.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the TaskList based on its index.
     *
     * @param index The zero-based index of the task to remove.
     * @return The Task object that was removed.
     * @throws AssertionError if the index is out of bounds.
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for removal";
        return tasks.remove(index);
    }

    /**
     * Returns the current number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Determines if the TaskList is empty.
     *
     * @return {@code true} if the list contains no tasks, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a sorted copy of all tasks in the TaskList.
     *
     * @return A new ArrayList of Task objects, sorted according to the TaskComparator.
     */
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> sortedTasks = new ArrayList<>(this.tasks);
        Collections.sort(sortedTasks, new TaskComparator());
        return sortedTasks;
    }

    /**
     * Retrieves a Task object at a specified index (without sorting).
     *
     * @param index The zero-based index of the task to retrieve
     * @return The Task object at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns tasks scheduled for a particular date.
     *
     * @param dateString Date in the format "d/M/yyyy"
     * @return An ArrayList of Tasks matching the specified date
     * @throws DukeException if the provided date format is invalid
     */
    public ArrayList<Task> getTasksOnDate(String dateString) throws DukeException {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use d/M/yyyy.");
        }

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate deadlineDate = deadline.getBy().toLocalDate();
                if (deadlineDate.equals(date)) {
                    filteredTasks.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().isAfter(date) && !event.getTo().isBefore(date)) {
                    filteredTasks.add(event);
                }
            }
        }
        Collections.sort(filteredTasks, new TaskComparator());
        return filteredTasks;
    }

    /**
     * Finds tasks in the list containing one or more specified keywords.
     *
     * @param keywords Variable-length list of keywords to search for
     * @return An ArrayList of Tasks that contain at least one of the keywords
     */
    public ArrayList<Task> findTasks(String... keywords) {

        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(task -> Arrays.stream(keywords)
                        .anyMatch(keyword -> task.getDescription().contains(keyword)))
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(filteredTasks, new TaskComparator());
        return filteredTasks;
    }

}
