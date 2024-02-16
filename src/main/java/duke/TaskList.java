package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates a list of Tasks.
 */
public class TaskList {
    public ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs the class TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns an element of the list.
     *
     * @param i Index number of the element.
     * @return A Task from the list.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Sets an element of the list as Done.
     *
     * @param n Index number of the Task that wants to be marked as Done.
     */
    public void mark (int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setDone();
            ui.showMarkTask(task);
        } else {
            ui.showNoTaskFound();
        }
    }

    /**
     * Sets an element of the list as Done.
     *
     * @param n Index number of the Task that wants to be marked as Done.
     * @param show Shows the user that the Task has been marked as Done.
     */
    public void mark (int n, boolean show) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setDone();
            if (show) {
                ui.showMarkTask(task);
            }
        }
    }

    /**
     * Sets an element of the list as Undone.
     *
     * @param n Index number of the Task that wants to be marked as Undone.
     */
    public void unmark(int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setUndone();
            ui.showUnmarkTask(task);
        } else {
            ui.showNoTaskFound();
        }
    }

    /**
     * Deletes a Task from the list.
     *
     * @param n Index number of the Task that wants to be deleted.
     */
    public void delete(int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task removedTask = tasks.remove(n - 1);
            ui.showDeleteTask(removedTask, tasks.size());
        } else {
            ui.showNoTaskFound();
        }
    }

    /**
     * Creates a ToDo task and puts it into the list.
     *
     * @param description The description of the ToDo task.
     */
    public void createToDo(String description) {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        ui.showCreateTask(todo, tasks.size());
    }

    /**
     * Creates a ToDo task and puts it into the list.
     *
     * @param description The description of the ToDo task.
     * @param show Shows the user that the ToDo task has been added to the list.
     */
    public void createToDo(String description, boolean show) {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        if (show) {
            ui.showCreateTask(todo, tasks.size());
        }
    }

    /**
     * Creates a Deadline task and puts it into the list.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline date of the Deadline task.
     */
    public void createDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.showCreateTask(deadline, tasks.size());
    }

    /**
     * Creates a Deadline task and puts it into the list.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline date of the Deadline task.
     * @param show Shows the user that the Deadline task has been added to the list.
     */
    public void createDeadline(String description, LocalDate by, boolean show) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        if (show) {
            ui.showCreateTask(deadline, tasks.size());
        }
    }

    /**
     * Creates an Event task and puts it into the list.
     *
     * @param description The description of the Event task.
     * @param from The starting date of the Event task.
     * @param to The end date of the Event task.
     */
    public void createEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        ui.showCreateTask(event, tasks.size());
    }

    /**
     * Creates an Event task and puts it into the list.
     *
     * @param description The description of the Event task.
     * @param from The starting date of the Event task.
     * @param to The end date of the Event task.
     * @param show Shows the user that the Event task has been added to the list.
     */
    public void createEvent(String description, LocalDate from, LocalDate to, boolean show) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        if (show) {
            ui.showCreateTask(event, tasks.size());
        }
    }

    /**
     * Returns the String representation of the TaskList class.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            string.append(i + 1).append(". ").append(get(i)).append("\n");
        }
        return string.toString();
    }
}
