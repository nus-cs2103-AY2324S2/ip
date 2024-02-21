package bartenderbob;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    /** The storage of the tasks */
    private static final Storage STORAGE = new Storage("./data/tasks.txt");
    /** The list of tasks */
    private final ArrayList<Task> tasks;
    /** The user interface */
    private Ui ui = new Ui();
    /**
     * Creates an instance of the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Creates an instance of the TaskList class with the specified tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Stores the task in the list of tasks.
     *
     * @param task The task to be stored.
     * @return Message to the user after storing the task.
     */
    public String store(Task task) {
        assert task != null : "Task cannot be null";
        if (task instanceof Event) {
            Event existingEvent = getEventIfClash(task);
            if (existingEvent != null) {
                //TODO: Throw the exception and handle it in the input handler?
                return BartenderBobException.showEventClashError((Event) task, existingEvent);
            }
        }
        tasks.add(task);
        STORAGE.saveTask(task);
        int totalTasks = tasks.size();
        return ui.showStoreTasksMessage(task, totalTasks);
    }
    /**
     * Returns the existing event that clashes with the new event.
     *
     * @param task The new event.
     * @return The event that clashes with the new event.
     */
    public Event getEventIfClash(Task task) {
        for (Task existingTask : tasks) {
            if (!(existingTask instanceof Event)) {
                continue;
            }
            Event newEvent = (Event) task;
            Event existingEvent = (Event) existingTask;
            if (newEvent.hasNoClash(existingEvent)) {
                continue;
            }
            return existingEvent;
        }
        return null;
    }
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public String list() {
        assert tasks != null : "Tasks list cannot be null";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ui.showListCommandHeader()).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            stringBuilder.append(ui.showListElements(number, tasks, i)).append("\n");
        }
        return stringBuilder.toString();
    }
    /**
     * Marks the task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return Message to the user after marking the task as done.
     * @throws BartenderBobException If the index is out of bounds.
     */
    public String markDone(String index) throws BartenderBobException {
        assert tasks != null : "Tasks list cannot be null";
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = tasks.get(integerIndex - 1);
            task.mark();
            STORAGE.saveChanges(tasks);
            return ui.showMarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }
    /**
     * Unmarks the task.
     *
     * @param index The index of the task to be unmarked.
     * @return Message to the user after unmarking the task.
     * @throws BartenderBobException If the index is out of bounds.
     */
    public String unmarkDone(String index) throws BartenderBobException {
        assert tasks != null : "Tasks list cannot be null";
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = tasks.get(integerIndex - 1);
            task.unmark();
            STORAGE.saveChanges(tasks);
            return ui.showUnmarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }

    }
    /**
     * Deletes the task.
     *
     * @param index The index of the task to be deleted.
     * @return Message to the user after deleting the task.
     * @throws BartenderBobException If the index is out of bounds.
     */
    public String delete(String index) throws BartenderBobException {
        assert tasks != null : "Tasks list cannot be null";
        try {
            int integerIndex = Integer.parseInt(index);
            String display = tasks.get(integerIndex - 1).show();
            tasks.remove(integerIndex - 1);
            STORAGE.saveChanges(tasks);
            int totalTasks = tasks.size();
            return ui.showDelete(display, totalTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }
    /**
     * Finds the tasks that contain the specified substring.
     *
     * @param substring The substring to be found.
     * @return The tasks that contain the specified substring.
     */
    public String find(String substring) {
        assert tasks != null : "Tasks list cannot be null";
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(substring)) {
                result.add(task);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ui.showFindCommandHeader()).append("\n");
        for (int i = 0; i < result.size(); i++) {
            int number = i + 1;
            ui.showListElements(number, result, i);
            stringBuilder.append(ui.showListElements(number, result, i)).append("\n");
        }
        return stringBuilder.toString();
    }
}
