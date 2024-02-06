package yapper;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * TaskList that handle operations on a list of Task objects.
 *
 * Internally represented as an {@link ArrayList} and contains a reference to a {@link Ui}.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public int listSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the TaskList.
     * Calls to Ui for message based on Task type.
     * Used as the default function to add to a TaskList.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
        if (task instanceof Todo) {
            ui.addTodoMessage((Todo) task);
        } else if (task instanceof Deadline) {
            ui.addDeadlineMessage((Deadline) task);
        } else if (task instanceof Event) {
            ui.addEventMessage((Event) task);
        } else {
            System.out.println("wrong task type added, user should not reach here");
        }
    }

    /**
     * Adds a Task to the TaskList without calling to Ui for message.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addTaskNoMessage(Task task) {
        tasks.add(task);
    }

    /**
     * Lists out all the Tasks in the TaskList in the order it was added in.
     */
    public void listTasks() {
        Ui.listMessage();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Ui.indent() + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Marks the Task at index i as done in the TaskList.
     *
     * @param i Index of task to be marked as done in the TaskList.
     */
    public void markTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        ui.markMessage(task);
    }

    /**
     * Unmarks the Task at index i in the TaskList.
     *
     * @param i Index of task to be unmarked as done in the TaskList.
     */
    public void unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.unmark();
        ui.unmarkMessage(task);
    }

    /**
     * Deletes the Task at index i from the TaskList.
     *
     * @param i Index of the Task to be deleted in the TaskList.
     */
    public void deleteTask(int i) {
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        ui.deleteMessage(task);
    }

    /**
     * Searches the TaskList for all tasks with a matching description to the given {@link String}.
     *
     * @param string String that describes the task description of the tasks to be found.
     * @return TaskList containing the tasks which has matching descriptions with the given string.
     */
    public TaskList find(String string) {
        TaskList foundTasks = new TaskList();
        Ui.findMessage(string);
        for (Task task : tasks) {
            if (task.contains(string)) {
                foundTasks.addTaskNoMessage(task);
            }
        }

        if (foundTasks.listSize() == 0) {
            Ui.foundNothingMessage();
        } else {
            foundTasks.listTasks();
        }

        return foundTasks;
    }
}
