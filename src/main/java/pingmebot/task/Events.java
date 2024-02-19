package pingmebot.task;

/**
 * A category of task that has a task description, and a start and end date or time to complete the task by.
 */
public class Events extends Task {
    protected String start;
    protected String end;
    protected String description;

    /**
     * Creates an object with the initialisation of the task's description, start and end date or time.
     *
     * @param description The task's description.
     * @param start The starting date or time of the task.
     * @param end The ending date or time of the task.
     */
    public Events(String description, String start, String end) {
        super(description);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A string representation of the Events task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + start + " to:" + end + ")";
    }

    /**
     * Returns a string representation that is saved to the local file whenever an Events task is completed.
     *
     * @param isCompleted Integer boolean flag to indicate if the task is completed.
     * @return A string representation when a Events task is completed.
     */
    public String updateEventText(int isCompleted) {
        String text = "";
        text += "event | " + isCompleted + " | " + this.description + " | " + this.start + " | " + this.end;
        return text;
    }

    /**
     * Returns true only when 2 objects have the same description and the starting and ending date or time of the task.
     *
     * @param obj The object that is being compared to.
     * @return A boolean to indicate if 2 objects have the same description and ending date or time of the task.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Events otherEvent = (Events) obj;
        return this.description.equals(otherEvent.description)
                && this.start.equals(otherEvent.start) && this.end.equals(otherEvent.end);
    }
}


