package oak.task.model;

/**
 * The parent Task Class to be inherited from for all Task-types
 */
public class Task {
    /** Whether the task has been completed, Default value = false */
    private Boolean isCompleted = false;
    /** The name of the task */
    private String name;
    /** The type icon of the task */
    private String typeIcon;

    /**
     * Constructor method for Task
     *
     * @param name
     * @param typeIcon
     */
    public Task(String name, String typeIcon) {
        this.name = name;
        this.typeIcon = typeIcon;
    }

    /**
     * Mark task completed.
     */
    public void markTaskCompleted() {
        this.isCompleted = true;
    }

    /**
     * Mark task not completed.
     */
    public void markTaskNotCompleted() {
        this.isCompleted = false;
    }

    public String getName() {
        return this.name;
    }

    // @@author SherisseTJW-reused
    // Reused from https://nus-cs2103-ay2324s2.github.io/website/schedule/week2/project.html,
    // Level-3 Extension A-Classes Partial Solution
    // with only function name modification
    private String getCompletedIcon() {
        return (isCompleted ? "X" : " ");
    }

    public String getTypeIcon() {
        return this.typeIcon;
    };

    /**
     * Converts the current task to a string format to be saved in the task list
     *
     * @return the formatted string
     */
    public String toTaskListStringFormat() {
        String completedValue = this.isCompleted ? "1" : "0";
        return String.format("%s|%s", completedValue, this.name);
    }


    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "][" + this.getCompletedIcon() + "] " + this.name;
    }
}
