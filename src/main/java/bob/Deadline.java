package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task of type Deadline.
 */
public class Deadline extends Task {

    private String deadline;
    private boolean hasTime = false;
    private LocalDateTime dateTime = null;

    /**
     * Constructor of Deadline.
     *
     * @param description The description for the deadline.
     * @param deadline When the deadline is due.
     * @throws BobException.InvalidDateTimeFormat If an invalid date is supplied by the user.
     */
    public Deadline(String description, String deadline) throws BobException.InvalidDateTimeFormat {
        super(description);
        this.deadline = deadline;

        if (!this.deadline.contains("/")) {
            return;
        }

        String[] args = this.deadline.split("\\s+");

        if (args.length > 2) {
            return;
        }

        if (args.length == 2) {
            hasTime = true;
        }

        int hour = 0;
        int minute = 0;

        if (hasTime && args[1].length() != 4) {
            throw new BobException.InvalidDateTimeFormat(
                    BobErrorMessages.INVALID_TIME_FORMAT);
        }

        if (hasTime && args[1].length() == 4) {
            try {
                hour = Integer.parseInt(args[1].substring(0, 2));
                minute = Integer.parseInt(args[1].substring(2, 4));
            } catch (NumberFormatException e) {
                throw new BobException.InvalidDateTimeFormat(
                        BobErrorMessages.INVALID_TIME_FORMAT);
            }
        }

        dateTime = BobUtil.convertToLocalDateTime(args, hour, minute);
    }

    @Override
    public String toString() {

        String deadlineText = deadline;
        if (dateTime != null) {

            String format = "MMM dd yyyy HH:mm";

            if (!hasTime) {
                format = "MMM dd yyyy";
            }

            deadlineText = dateTime.format(DateTimeFormatter.ofPattern(format));
        }

        return super.toString() + " (by: " + deadlineText + ")";
    }

    @Override
    public String toSavableFormat() {
        return isArchived + "|" + uuid + "|D|" + description + "|" + isDone + "|" + deadline;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean hasTime() {
        return hasTime;
    }
}
