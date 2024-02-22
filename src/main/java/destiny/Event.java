package destiny;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Extension of task that saves the timeline of that task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");

    /**
     * Base constructor used by Destiny to generate an Event object.
     *
     * @param description Title of the task.
     * @param fromTime The start date of this Deadline task.
     * @param toTime The end date of this Deadline task.
     * @throws DukeException If description is empty or either fromTime or toTime is not given in the correct format.
     */
    public Event(String description, String fromTime, String toTime) throws DukeException {
        super(description);
        fromTime = fromTime.trim();
        toTime = toTime.trim();
        if (description.trim().length() == 0 || fromTime.length() == 0 || toTime.length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Event task";
            }
            if (fromTime.length() == 0) {
                errorMessage += "\na deadline after the '/from' command";
            }
            if (toTime.length() == 0) {
                errorMessage += "\na deadline after the '/to' command";
            }
            throw new DukeException(errorMessage);
        }

        String formatIssueMessage = "Please enter the date and time in the following format:\n"
                + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234)";
        try {
            if (LocalDateTime.parse(fromTime, inputFormatter).isAfter(LocalDateTime.parse(toTime, inputFormatter))) {
                throw new DukeException("The time in \"from\" comes after the time in \"to\"\n"
                        + "Please ensure that your \"from\" time is earlier than your \"to\" time");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("The time format in \"from\" or \"to\" is wrong\n" + formatIssueMessage);
        }
        this.from = LocalDateTime.parse(fromTime, inputFormatter);
        this.to = LocalDateTime.parse(toTime, inputFormatter);
    }


    /**
     * Secondary constructor used by Destiny to generate an Event object from a data txt file.
     *
     * @param logic Indicates if this task should be marked.
     * @param description Title of the task.
     * @param fromTime The start date of this Deadline task.
     * @param toTime The end date of this Deadline task.
     * @throws DukeException If description is empty or either fromTime or toTime is not given in the correct format.
     */
    public Event(String logic, String description, String fromTime, String toTime) throws DukeException {
        super(description);
        fromTime = fromTime.trim();
        toTime = toTime.trim();
        if (description.trim().length() == 0 || fromTime.length() == 0 || toTime.length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Event task";
            }
            if (fromTime.length() == 0) {
                errorMessage += "\na deadline after the '/from' command";
            }
            if (toTime.length() == 0) {
                errorMessage += "\na deadline after the '/to' command";
            }
            throw new DukeException(errorMessage);
        }

        String formatIssueMessage = "Please enter the date and time in the following format:\n"
                + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234)";
        try {
            if (LocalDateTime.parse(fromTime, inputFormatter).isAfter(LocalDateTime.parse(toTime, inputFormatter))) {
                throw new DukeException("The time in \"from\" comes after the time in \"to\"\n"
                        + "Please ensure that your \"from\" time is earlier than your \"to\" time");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("The time format in \"from\" or \"to\" is wrong\n" + formatIssueMessage);
        }
        this.from = LocalDateTime.parse(fromTime, inputFormatter);
        this.to = LocalDateTime.parse(toTime, inputFormatter);

        switch (logic) {
        case "1":
            this.isDone = true;
            break;
        default:
            this.isDone = false;
            break;
        }
    }

    /**
     * Getter for from time in the input format, used to store the time in the destiny.txt data file.
     * @return Reformatted string of from time.
     */
    public String getFrom() {
        return from.format(inputFormatter);
    }

    /**
     * Getter for to time in the input format, used to store the time in the destiny.txt data file.
     * @return Reformatted string of to time.
     */
    public String getTo() {
        return to.format(inputFormatter);
    }

    private LocalDateTime getFromAsLdt() {
        return from;
    }

    private LocalDateTime getToAsLdt() {
        return to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(displayFormatter)
                + "  to: " + to.format(displayFormatter) + ")";
    }

    @Override
    public boolean equals(Task task) {
        if (!(task instanceof Event)) {
            return false;
        }
        Event event = (Event) task;
        boolean isSimilar = this.description.equals(event.getDescription())
                && this.from.isEqual(event.getFromAsLdt())
                && this.to.isEqual(event.getToAsLdt());
        boolean isSame = this == task;
        return isSimilar || isSame;
    }
}
