package klee.task;

/**
 * Represent a task for Klee to note down.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor for Task class.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the current completion state of a task in String form.
     *
     * @return String representation for task.
     */
    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + statusIcon + "] " + description;
    }

    /**
     * Returns if the task is done.
     *
     * @return If the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the type of the task represented by a letter.
     *
     * @return String of the character type.
     */
    public String type() {
        return type;
    }

    /**
     * Returns a String representation of this task which will be saved into the txt file by Storage.
     *
     * @return String to represent the task in the txt file.
     */
    public String toText() {
        return type + " / " + (isDone ? "1" : "0") + " / " + description;
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unMark() {
        isDone = false;
    }

    /**
     * Check if task description contains searchTerm, capitalisation does not matter.
     *
     * @param searchTerm
     * @return
     */
    public boolean include(String searchTerm) {
        return this.description.toLowerCase().contains(searchTerm.toLowerCase());
    }

    /**
     * Checks if the obj has the same fields as the current instance.
     *
     * @param obj
     * @return If obj has the same fields as the current instance.
     */
    @Override
    public boolean equals(Object obj) {
        System.out.println("Gets called");
        if (Task.class.isAssignableFrom(obj.getClass())) {
            boolean hasSameFields = this.isDone == ((Task) obj).isDone && this.description
                    == ((Task) obj).description && this.type == ((Task) obj).type;
            return hasSameFields;
        } else {
            return false;
        }
    }
}
