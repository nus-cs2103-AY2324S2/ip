package tool;

import java.util.ArrayList;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import tool.Ui;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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

        ui.showDivider();
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + todo);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
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

        ui.showDivider();
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + deadline);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
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

        ui.showDivider();
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + event);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
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
        ui.showDivider();
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("          " + selectedTaskToBeMarked);
        ui.showDivider();
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
        ui.showDivider();
        System.out.println("        OK, I've marked this task as not done yet:");
        System.out.println("          " + selectedTaskToBeUnmarked);
        ui.showDivider();
    }

    /**
     * Deletes a task from task list.
     *
     * @param selectedTaskNumberToBeDeleted Task number to be deleted.
     */
    public void deleteTask(int selectedTaskNumberToBeDeleted, Ui ui) {
        Task deletedTask = tasks.get(selectedTaskNumberToBeDeleted - 1);

        ui.showDivider();
        System.out.println("        Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println("        Now you have " + (tasks.size() - 1) + " tasks in the list.");
        ui.showDivider();
        tasks.remove(selectedTaskNumberToBeDeleted - 1);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
}
