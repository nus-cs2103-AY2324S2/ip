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


    /**
     * Constructs the class TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();

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
    public String mark (int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setDone();
            assert task.getStatusIcon().equals("X");
            return Ui.showMarkTask(task);
        } else {
            return Ui.showNoTaskFound();
        }
    }

    /**
     * Sets an element of the list as Undone.
     *
     * @param n Index number of the Task that wants to be marked as Undone.
     */
    public String unmark(int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setUndone();
            assert task.getStatusIcon().equals(" ");
            return Ui.showUnmarkTask(task);
        } else {
            return Ui.showNoTaskFound();
        }
    }

    /**
     * Deletes a Task from the list.
     *
     * @param n Index number of the Task that wants to be deleted.
     */
    public String delete(int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task removedTask = tasks.remove(n - 1);
            return Ui.showDeleteTask(removedTask, tasks.size());
        } else {
            return Ui.showNoTaskFound();
        }
    }

    /**
     * Creates a ToDo task and puts it into the list.
     *
     * @param description The description of the ToDo task.
     */
    public String createToDo(String description) {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        return Ui.showCreateTask(todo, tasks.size());
    }

    /**
     * Creates a Deadline task and puts it into the list.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline date of the Deadline task.
     */
    public String createDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        return Ui.showCreateTask(deadline, tasks.size());
    }

    /**
     * Creates an Event task and puts it into the list.
     *
     * @param description The description of the Event task.
     * @param from The starting date of the Event task.
     * @param to The end date of the Event task.
     */
    public String createEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        return Ui.showCreateTask(event, tasks.size());
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
