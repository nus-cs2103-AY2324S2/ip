import java.util.ArrayList;
public class Storage {
    private  ArrayList<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>(); // assume maximum size of task list to be 100
    }

    /**
     * Store the task into the tasklist.
     *
     * @param task Description of task to be stored.
     */
    public void storeToDo(String task) {
        ToDo newToDo = new ToDo(task);
        this.tasks.add(newToDo);
    }

    public void storeDeadline(String task, String by) {
        Deadline newDeadline = new Deadline(task, by);
        this.tasks.add(newDeadline);
    }

    public void storeEvent(String task, String from, String to) {
        Event newEvent = new Event(task, from, to);
        this.tasks.add(newEvent);
    }

    /**
     * Get task description.
     *
     * @param index Index of task in the list.
     * @return The task description.
     */
    public String getTaskDescription(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Get the status of a task.
     * @param index Index of task in the list.
     * @return The status icon, either X or empty space.
     */
    public String getTaskStatus(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    /**
     * Get the size of list.
     * @return The size of list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Mark the task as done.
     *
     * @param index Index of task in the list.
     */
    public void mark(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Unmark the task.
     * @param index Index of task in the list.
     */
    public void unmark(int index) {
        this.tasks.get(index).unmark();
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }
}
