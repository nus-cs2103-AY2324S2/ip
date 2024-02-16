package duke;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     *
     * The string representation should be a sequence of valid commands for the program,
     * separated by newlines,
     * such that if there was originally taskIndex tasks in the list,
     * then after executing the string representation,
     * there will be taskIndex + 1 tasks in the list.
     *
     * @param taskIndex the 0-indexed index of this task
     * @return the serialized string
     */
    public abstract String serializeToCommand(int taskIndex);

    protected String serializeDoneMark(int taskIndex) {
        if (this.isDone) {
            return "mark " + (taskIndex + 1) + "\n";
        } else {
            return "";
        }
    }
}
