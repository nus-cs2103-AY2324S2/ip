package seedu.banter.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import seedu.banter.errors.Errors;
import seedu.banter.errors.InvalidBanterUsageError;
import seedu.banter.errors.InvalidTaskNumberUsageError;
import seedu.banter.ui.Ui;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a Todo task to the TaskList.
     * @param description Description of the Todo task.
     * @return String representation of the Todo task.
     */
    public String addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        return "Got it. I've added this task:\n" + todo
                + "\nNow you have " + taskList.size() + " banter.tasks in the list.";
    }

    /**
     * Loads a Todo task to the TaskList.
     * @param description Description of the Todo task.
     * @param isDone Done status of the Todo task.
     */
    public void loadTodo(String description, boolean isDone) {
        Todo todo = new Todo(description, isDone);
        taskList.add(todo);
    }

    /**
     * Adds a Deadline task to the TaskList.
     * @param description Description of the Deadline task.
     * @param dueDate Due date of the Deadline task.
     * @return String representation of the Deadline task.
     */
    public String addDeadline(String description, LocalDateTime dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        taskList.add(deadline);
        return "Got it. I've added this task:\n" + deadline
                + "\nNow you have " + taskList.size() + " banter.tasks in the list.";
    }

    /**
     * Loads a Deadline task to the TaskList.
     * @param description Description of the Deadline task.
     * @param isDone Done status of the Deadline task.
     * @param dueDate Due date of the Deadline task.
     */
    public void loadDeadline(String description, boolean isDone, LocalDateTime dueDate) {
        Deadline deadline = new Deadline(description, isDone, dueDate);
        taskList.add(deadline);
    }

    /**
     * Adds an Event task to the TaskList.
     * @param eventDescription Description of the Event task.
     * @param start Start time of the Event task.
     * @param end End time of the Event task.
     * @return String representation of the Event task.
     */
    public String addEvent(String eventDescription, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(eventDescription, start, end);
        taskList.add(event);
        return "Got it. I've added this task:\n" + event
                + "\nNow you have " + taskList.size() + " banter.tasks in the list.";
    }

    /**
     * Loads an Event task to the TaskList.
     * @param eventDescription Description of the Event task.
     * @param isDone Done status of the Event task.
     * @param start Start time of the Event task.
     * @param end End time of the Event task.
     */
    public void loadEvent(String eventDescription, boolean isDone, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(eventDescription, isDone, start, end);
        taskList.add(event);
    }

    /**
     * Returns the string representation of the TaskList.
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the banter.tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\n" + (i + 1) + ". " + taskList.get(i));
        }
        return sb.toString();
    }

    /**
     * Marks a task as done.
     * @param taskNumber Number of tasks in the TaskList.
     * @return String representation of the task marked as done.
     */
    public String markTaskAsDone(int taskNumber) throws InvalidBanterUsageError {
        try {
            return taskList.get(taskNumber - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Ui.MARK_USAGE, this);
        }
    }

    /**
     * Marks a task as undone.
     * @param taskNumber Number of tasks in the TaskList.
     * @return String representation of the task marked as undone.
     */
    public String markTaskAsUndone(int taskNumber) throws InvalidBanterUsageError {
        try {
            return taskList.get(taskNumber - 1).markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Ui.UNMARK_USAGE, this);
        }
    }

    /**
     * Deletes a task from the TaskList.
     * @param taskNumber Number of tasks in the TaskList.
     * @return String representation of the task deleted.
     */
    public String deleteTask(int taskNumber) throws InvalidBanterUsageError {
        try {
            Task deleted = taskList.remove(taskNumber - 1);
            return "Noted. I've removed this task:\n" + deleted
                    + "\nNow you have " + taskList.size() + " banter.tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Ui.DELETE_USAGE, this);
        }
    }

    /**
     * Returns the iterator of the TaskList.
     * @return Iterator of the TaskList.
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    /**
     * Returns the string representation of the list of tasks in TaskList that contain the keyword.
     * @param keyword
     * @return String representation of the list of tasks in TaskList that contain the keyword.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        int count = 0;
        for (Task task : taskList) {
            if (task.contains(keyword)) {
                sb.append("\n" + (count + 1) + ". " + task);
                count++;
            }
        }
        if (count == 0) {
            return "No matching tasks found.";
        }
        return sb.toString();
    }
}
