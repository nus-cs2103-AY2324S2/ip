package rochin;

import java.time.LocalDateTime;

/**
 * Represent a task with a description and a status.
 */
class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Construct a task with the given description and set its status to not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon representing whether the task is done or not done.
     *
     * @return The status icon ('X' if done, ' ' if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * The string representation of the task that to be saved in the file.
     *
     * @return The string representation of the task.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s", getTaskType(), isDone ? 1 : 0, description, getAdditionalDetails());
    }


    /**
     * Create new tasks according to the data in the file.
     *
     * @param fileLine data from the file.
     * @return new task
     */
    public static Task createTaskFromFileString(String fileLine) throws RochinException {
        String[] parts = fileLine.split("\\s* \\| \\s*");
        if (parts.length >= 3) {
            boolean isDone = Integer.parseInt(parts[1].trim()) == 1;
            String description = parts[2].trim();
            LocalDateTime dateTime = null;
            if (parts.length > 3) {
                String dateTimeString = parts[3];
                dateTime = DateAndTime.parseDateTime(dateTimeString);
            }
            // Extract additional details based on task type (Todo, Deadline, Event)
            if (parts[0].trim().equals("T")) {
                TodoTask todoTask = new TodoTask(description);
                if (isDone) {
                    todoTask.markAsDone();
                }
                return todoTask;
            } else if (parts[0].trim().equals("D")) {
                DeadlineTask deadlineTask = new DeadlineTask(description, dateTime);
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                return deadlineTask;
            } else if (parts[0].trim().equals("E")) {
                // Extract 'from' and 'to' datetime parameters
                String[] eventTimes = parts[3].trim().split(" to ");
                if (eventTimes.length == 2) {
                    LocalDateTime fromDateTime = DateAndTime.parseDateTime(eventTimes[0]);
                    LocalDateTime toDateTime = DateAndTime.parseDateTime(eventTimes[1]);
                    EventTask eventTask = new EventTask(description, fromDateTime, toDateTime);
                    if (isDone) {
                        eventTask.markAsDone();
                    }
                    return eventTask;
                } else {
                    throw new RochinException("Invalid format for event task: " + description);
                }
            } else {
                throw new RochinException("Unknown task type: " + parts[0]);
            }
        } else {
            throw new RochinException("Invalid task format: " + fileLine);
        }

    }

    /**
     * Default implementation of getTaskType, can be overridden by subclasses
     *
     * @return A string.
     */
    public String getTaskType() {
        return "U";
    }

    /**
     * Default implementation of getAdditionalDetails, can be overridden by subclasses
     *
     * @return A string.
     */
    public String getAdditionalDetails() {
        return "";
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

