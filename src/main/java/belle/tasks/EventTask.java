package belle.tasks;

import belle.others.DateFormatter;

/**
 * Represents tasks with start and end time.
 */
public class EventTask extends Task {

    private String start;
    private String end;

    /**
     * Constructs EventTask.
     *
     * @param name Name of task.
     * @param isDone Boolean that indicates
     *               whether task is done.
     * @param start Start date/time of
     *              event.
     * @param end End date/time of
     *            event.
     */
    public EventTask(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        this.start = start;
        this.end = end;
        DateFormatter d1 = new DateFormatter(this.start);
        if (d1.hasValidDate()) {
            this.start = d1.convertDate();
        }
        DateFormatter d2 = new DateFormatter(this.end);
        if (d2.hasValidDate()) {
            this.end = d2.convertDate();
        }
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }
    public String getType() {
        return "E";
    }

    public void setDeadline(String newdeadline) {
        this.end = newdeadline;
    }
    @Override
    public String toString() {
        return " [E]" + super.toString()
                + " (from: " + this.start + " " + "to: " + end + ")";
    }
}
