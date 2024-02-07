package tool;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tool.Ui;

/**
 * Represents the list of tasks specified by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the list of saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds and prints a todo task.
     *
     * @param description Description of the todo task.
     */
    public void addToDo(String description, Ui ui) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.printAddTodoSuccessful(todo, tasks.size());
    }

    /**
     * Adds and prints a task with a deadline.
     *
     * @param description Description of the task with deadline.
     * @param dueDate Deadline of the task.
     */
    public void addDeadline(String description, String dueDate, Ui ui) {
        Deadline deadline = new Deadline(description, dueDate);
        tasks.add(deadline);
        ui.printAddDeadlineSuccessful(deadline, tasks.size());
    }

    /**
     * Adds and prints an event.
     *
     * @param description Description of the event.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public void addEvent(String description, String from, String to, Ui ui) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        ui.printAddEventSuccessful(event, tasks.size());
    }

    /**
     * Marks a task as completed.
     *
     * @param selectedTaskNumberToBeMarked Task number to be marked as completed.
     */
    public void markTask(int selectedTaskNumberToBeMarked, Ui ui) {
        Task selectedTaskToBeMarked = tasks.get(selectedTaskNumberToBeMarked - 1);
        selectedTaskToBeMarked.setMarked();
        tasks.set(selectedTaskNumberToBeMarked - 1, selectedTaskToBeMarked);
        ui.printMarkTaskSuccessful(selectedTaskToBeMarked);
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param selectedTaskNumberToBeUnmarked Task number to be unmarked as incomplete.
     */
    public void unMarkTask(int selectedTaskNumberToBeUnmarked, Ui ui) {
        Task selectedTaskToBeUnmarked = tasks.get(selectedTaskNumberToBeUnmarked - 1);
        selectedTaskToBeUnmarked.setUnmarked();
        tasks.set(selectedTaskNumberToBeUnmarked - 1, selectedTaskToBeUnmarked);
        ui.printUnmarkTaskSuccessful(selectedTaskToBeUnmarked);
    }

    /**
     * Deletes a task from task list.
     *
     * @param selectedTaskNumberToBeDeleted Task number to be deleted.
     */
    public void deleteTask(int selectedTaskNumberToBeDeleted, Ui ui) {
        Task deletedTask = tasks.get(selectedTaskNumberToBeDeleted - 1);
        ui.printDeleteTaskSuccessful(deletedTask, tasks.size());
        tasks.remove(selectedTaskNumberToBeDeleted - 1);
    }

    /**
     * Retrieves a task from task list.
     *
     * @param taskNumber Task number to be retrieved.
     * @return Retrieved task.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return True if list of tasks is empty, else False.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the size of the task list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}