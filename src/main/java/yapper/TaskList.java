package yapper;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * TaskList that handle operations on a list of Task objects.
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
    public String addTask(Task task) {
        tasks.add(task);
        if (task instanceof Todo) {
            return ui.addTodoMessage((Todo) task);
        } else if (task instanceof Deadline) {
            return ui.addDeadlineMessage((Deadline) task);
        } else if (task instanceof Event) {
            return ui.addEventMessage((Event) task);
        } else {
            return "wrong task type added, user should not reach here";
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
    public String listTasks() {
        String responseWithList = Ui.listMessage();
        return tasks.stream()
                .map(x -> Ui.indent() + (tasks.indexOf(x) + 1) + "." + x + "\n")
                .reduce(responseWithList, (y, z) -> y + z);
    }

    /**
     * Marks the Task at index i as done in the TaskList.
     *
     * @param i Index of task to be marked as done in the TaskList.
     */
    public String markTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        assert(ui != null);
        return ui.markMessage(task);
    }

    /**
     * Unmarks the Task at index i in the TaskList.
     *
     * @param i Index of task to be unmarked as done in the TaskList.
     */
    public String unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsNotDone();
        assert(ui != null);
        return ui.unmarkMessage(task);
    }

    /**
     * Deletes the Task at index i from the TaskList.
     *
     * @param i Index of the Task to be deleted in the TaskList.
     */
    public String deleteTask(int i) {
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        assert(ui != null);
        return ui.deleteMessage(task);
    }

    /**
     * Searches the TaskList for all tasks with a matching description to the given {@link String}.
     *
     * @param str String that describes the task description of the tasks to be found.
     * @return TaskList containing the tasks which has matching descriptions with the given string.
     */
    public String find(String str) {
        TaskList foundTasks = new TaskList();
        foundTasks.setUi(ui);
        tasks.stream().filter(x -> x.contains(str)).forEach(foundTasks::addTask);

        if (foundTasks.listSize() == 0) {
            return Ui.findMessage(str) + Ui.foundNothingMessage();
        }
        return Ui.findMessage(str) + foundTasks.listTasks();
    }
}
