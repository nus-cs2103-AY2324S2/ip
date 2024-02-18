package linus.tasks;

import java.time.LocalDateTime;

/**
 * Parent class for task
 * @param description
 */
public abstract class Task {
    protected String description;
    protected Boolean isMarked;

    /**
     * Constructor for Task
     * @param description String task description
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Constructor for Task when loaded from a save
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isMarked = isDone;
    }

    /**
     * Get the description of the task
     * @return String of task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Finds if the string find matches with a part of the description
     * @param find String to find match
     * @return Boolean true if match else false
     */
    public Boolean isMatchingDescription(String find) {
        return description.contains(find);
    }

    /**
     * Gets status of class whether it is marked or not
     * @return String X or ' '
     */
    public String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }

    /**
     * Get the type of task
     * @return String of the type of task
     */
    public abstract String getTaskType();

    /**
     * Get Date of task to use for sorting
     * @return LocalDateTime of task
     */
    public abstract LocalDateTime getDate();

    /**
     * Mark task as done
     */
    public void markAsDone() {
        isMarked = true;
    }

    /**
     * Unmark a task
     */
    public void markAsNotDone() {
        isMarked = false;
    }

    /**
     * Creates the save format for task
     * @return String Task save format
     */
    public String saveFormat() {
        String isDoneSave = (isMarked ? "1" : "0");
        return String.format("%s;;%s",
                isDoneSave, description);
    }

    /**
     * @return String for task to be printed
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.getStatusIcon(), description);
    }

}
