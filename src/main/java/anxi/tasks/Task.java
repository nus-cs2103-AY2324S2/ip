package anxi.tasks;

/**
 * Basic template for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     * @param description Task name or description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Updates if task is done.
     * @param isDone    Marks task as completed/uncompleted. [True: complete, False: uncompleted]
     */
    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if task description matches string.
     *
     * @param match String to search for within description.
     * @return hasMatch     True: string is found within description.
     *                      False: string is not found within description.
     */
    public boolean isMatchingDescription(String match) {
        return this.description.contains(match);
    }

    /**
     * Formats Task as a string to be saved to file.
     * @return saveTask     Returns the task as a string in the format compatible with file.
     */
    public String saveFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }
}
