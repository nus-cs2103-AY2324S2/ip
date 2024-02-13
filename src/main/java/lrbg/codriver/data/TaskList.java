package lrbg.codriver.data;

import java.time.LocalDate;
import java.util.ArrayList;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.parser.Parser;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** The list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param taskStrings The array of strings representing tasks.
     */
    public TaskList(String[] taskStrings) {
        this.tasks = new ArrayList<>();
        for (String s : taskStrings) {
            String[] arguments = s.split("\\|");
            String type = arguments[0];
            boolean isDone = arguments[1].equals("1");
            switch (type) {
            case "T": // task.ToDo
                String description = arguments[2];
                Todo t = new Todo(description);
                if (isDone) {
                    t.markDone();
                }
                this.tasks.add(t);
                break;
            case "D": // task.Deadline
                String deadlineDescription = arguments[2];
                LocalDate deadlineDate = Parser.parseDate(arguments[3]);
                Deadline d = new Deadline(deadlineDescription, deadlineDate);
                if (isDone) {
                    d.markDone();
                }
                this.tasks.add(d);
                break;
            case "E": // task.Event
                String eventDescription = arguments[2];
                String[] dates = arguments[3].split("~");
                LocalDate eventFrom = Parser.parseDate(dates[0]);
                LocalDate eventTo = Parser.parseDate(dates[1]);
                Event e = new Event(eventDescription, eventFrom, eventTo);
                if (isDone) {
                    e.markDone();
                }
                this.tasks.add(e);
                break;
            }
        }
    }

    /**
     * Gets the size of the list.
     * @return The size of the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the given index.
     * @param index The index of the task, zero indexed.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

/**
     * Marks the task at the given index as done.
     * @param index The index of the task, one indexed.
     * @throws CoDriverException If the index is out of bounds.
     */
    public void markTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        assert listIndex >= 0 : "Index out of bounds for markTask()";
        Task t = this.tasks.get(listIndex);
        t.markDone();
    }

    /**
     * Marks the task at the given index as not done.
     * @param index The index of the task, one indexed.
     * @throws CoDriverException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        assert listIndex >= 0 : "Index out of bounds for unmarkTask()";
        Task t = this.tasks.get(listIndex);
        t.markNotDone();
    }

    /**
     * Deletes the task at the given index.
     * @param index The index of the task, one indexed.
     * @throws CoDriverException If the index is out of bounds.
     */
    public void deleteTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        assert listIndex >= 0 : "Index out of bounds for deleteTask()";
        Task t = this.tasks.get(listIndex);
        this.tasks.remove(t);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
     * Finds tasks in the task list that match a keyword.
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task t : this.tasks) {
            if (!t.getDescription().contains(keyword)) {
                continue;
            }
            matchingTasks.addTask(t);
        }
        return matchingTasks;
    }

    /**
     * Gets the list of tasks as a string to be saved in a file.
     * @return The list of tasks as a string.
     */
    public String toFileSaveString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : this.tasks) {
            if (t instanceof Todo) {
                sb.append(t.toFileSaveString()).append("\n");
            } else if (t instanceof Deadline) {
                sb.append(t.toFileSaveString()).append("\n");
            } else if (t instanceof Event) {
                sb.append(t.toFileSaveString()).append("\n");
            }
        }
        return sb.toString();
    }
}
