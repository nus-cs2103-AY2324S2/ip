package bentley;

/**
 * The Task class represents a basic task with a description and completion
 * status.
 * It provides methods to mark the task as done or undone and retrieve
 * information about the task.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String taskDescription;

    /**
     * The original task data as it was read from a file or created.
     */
    protected String originalTaskData;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a Task object based on the provided task data.
     * The task data is parsed to extract the completion status and description.
     *
     * @param taskData The raw task data, including completion status and
     *                 description.
     */
    public Task(String taskData) {
        if (taskData.matches("^\\[[Xx ]\\] .*")) {
            this.isDone = taskData.charAt(1) == 'X' || taskData.charAt(1) == 'x';
            this.originalTaskData = taskData.trim();
            this.taskDescription = taskData.substring(5).trim();
        } else {
            this.isDone = false;
            this.originalTaskData = "[ ] " + taskData.trim(); // Add [ ] if not present
            this.taskDescription = taskData.trim();
        }
    }

    /**
     * Returns a string representation indicating whether the task is done or not.
     *
     * @return "X" if the task is done, an empty string otherwise.
     */
    public String doneOrNot() {
        return (isDone ? "X" : "");
    }

    /**
     * Marks the task as done by updating its completion status and original task
     * data.
     */

    public void markAsDone() {
        this.isDone = true;
        // Update the original task data when marking as done
        this.taskDescription = "[X] " + this.taskDescription.substring(4).trim();
    }

    /**
     * Marks the task as undone by updating its completion status and original task
     * data.
     */

    public void markAsUndone() {
        this.isDone = false;
        // Update the original task data when marking as undone
        this.taskDescription = "[  ] " + this.taskDescription.substring(4).trim();
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return taskDescription;
    }

    /**
     * Returns a formatted string representation of the task.
     * If the task description already contains square brackets, it is returned as
     * is.
     * Otherwise, square brackets are added to indicate the completion status.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        // Check if taskDescription already contains square brackets
        if (taskDescription.matches("^\\[.*\\].*")) {
            return taskDescription; // Return as is if square brackets are already present
        } else {
            // Check if the taskDescription starts with a number followed by a dot and
            // whitespace
            if (taskDescription.matches("^\\d+\\.\\s.*")) {
                return taskDescription; // Return as is if numbering is already present
            } else {
                // Add numbering and square brackets
                return "[ " + (doneOrNot()) + " ] " + taskDescription;
            }
        }
    }

    /**
     * Retrieves the original task data as it was read from a file or created.
     *
     * @return The original task data.
     */
    public String getOriginalTaskData() {
        return originalTaskData;
    }
}
