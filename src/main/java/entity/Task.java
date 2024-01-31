package entity;

/**
 * The Task class is an abstract class and provides common attributes and methods that are shared by various task types
 * such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String title;
    protected boolean marked;

    Task(String title) {
            this.title = title;
            this.marked = false;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * Retrieves the completion status of a Task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean getMarked() {
        return this.marked;
    }

    public void setMarked(boolean bool) {
        this.marked = bool;
    }

    public abstract String save();

}
