public class Storage {
    private Task[] tasks;
    private static int size = 0;

    public Storage() {
        this.tasks = new Task[100]; // assume maximum size of task list to be 100
    }

    /**
     * Store the task into the tasklist.
     *
     * @param task Description of task to be stored.
     */
    public void storeTask(String task) {
        Task newTask = new Task(task);
        this.tasks[size] = newTask;
        size++;
    }

    /**
     * Get task description.
     *
     * @param index Index of task in the list.
     * @return The task description.
     */
    public String getTaskDescription(int index) {
        return "[" + this.tasks[index].getStatusIcon() + "] " + this.tasks[index].getDescription();
    }

    /**
     * Get the status of a task.
     * @param index Index of task in the list.
     * @return The status icon, either X or empty space.
     */
    public String getTaskStatus(int index) {
        return this.tasks[index].getStatusIcon();
    }

    /**
     * Get the size of list.
     * @return The size of list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Mark the task as done.
     *
     * @param index Index of task in the list.
     */
    public void mark(int index) {
        this.tasks[index].mark();
    }

    /**
     * Unmark the task.
     * @param index Index of task in the list.
     */
    public void unmark(int index) {
        this.tasks[index].unmark();
    }

}
