package chatteroo.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and a status of whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    String DIVIDER = " | ";

    /**
     * Constructor for the Task class.
     * @param description The description of the task.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;

        assert description != null : "Task description cannot be null";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Formats the task status according to the required format to be saved in the file.
     * @return The formatted task status to be saved in the file.
     */
    public String formatTaskStatusForFile() {
        return (isDone ? "1" : "0");
    }

    /**
     * Formats the task according to the required format to be saved in the file.
     * @param taskToSave The task to be saved.
     * @return The formatted task to be saved in the file.
     */
    public String formatTaskForFile(Task taskToSave) {
        String taskType = taskToSave.getTaskType();
        String taskStatus = taskToSave.formatTaskStatusForFile();
        String taskDescription = taskToSave.getDescription();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (taskToSave instanceof ToDo) {
            return taskType + DIVIDER + taskStatus + DIVIDER + taskDescription + "\n";
        } else if (taskToSave instanceof Deadline) {
            String taskBy = ((Deadline) taskToSave).getBy().format(dateFormat);
            return taskType + DIVIDER + taskStatus + DIVIDER + taskDescription + DIVIDER + taskBy + "\n";
        } else if (taskToSave instanceof Event) {
            String taskFrom = ((Event) taskToSave).getFrom().format(dateFormat);
            String taskTo = ((Event) taskToSave).getTo().format(dateFormat);
            return taskType + DIVIDER + taskStatus + DIVIDER + taskDescription + DIVIDER
                    + taskFrom + DIVIDER + taskTo + "\n";
        }
        return taskType + DIVIDER + taskStatus + DIVIDER + taskDescription + "\n";
    }
}
