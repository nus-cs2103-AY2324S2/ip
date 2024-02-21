package nicole.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import nicole.nicoleexceptions.NicoleException;

public class Event extends Task {
    private LocalDateTime deadlineFromDateTimeLocalDate;
    private LocalDateTime deadlineToDateTimeLocalDate;
    private String deadlineFromDateReformattedString;
    private String deadlineFromTimeReformattedString;
    private String deadlineToDateReformattedString;
    private String deadlineToTimeReformattedString;

    /**
     * Initialises a Event
     *
     * @param name the user request.
     * @throws NicoleException if the description is not
     *                         as event [name] from YYYY-MM-DD at HH-MM-SS
     *                         to YYYY-MM-DD at HH-MM-SS.
     */
    public Event(String name) throws NicoleException {
        super();
        if (name.contains("null")) {
            throw new NicoleException("Describe your event like this: "
                                      + "event [name] from YYYY-MM-DD at HH-MM-SS to YYYY-MM-DD at HH-MM-SS");
        }
        parseDateTime(name);
    }

    private void parseDateTime(String name) throws NicoleException {
        /*
         The expected structure of this array is [..., from, date, at, time, to, date, at, time] where the
         ... are the event description words
         */
        String[] whiteSpaceSeparatedDate = name.split(" ");

        StringBuilder eventDescription = new StringBuilder();
        for (int i = 0; i < whiteSpaceSeparatedDate.length - 8; i++) {
            eventDescription.append(whiteSpaceSeparatedDate[i]).append(" ");
        }
        super.updateName(eventDescription.toString());

        try {
            for (int i = 0; i < whiteSpaceSeparatedDate.length; i++) {
                if (whiteSpaceSeparatedDate[i].matches("\\d{4}-\\d{2}-\\d{2}")
                    && i < whiteSpaceSeparatedDate.length - 4) {
                    deadlineFromDateTimeLocalDate = LocalDateTime.parse(whiteSpaceSeparatedDate[i]
                            + "T" + whiteSpaceSeparatedDate[i + 2]);
                } else if (whiteSpaceSeparatedDate[i].matches("\\d{4}-\\d{2}-\\d{2}")
                           && i > whiteSpaceSeparatedDate.length - 4) {
                    deadlineToDateTimeLocalDate = LocalDateTime.parse(whiteSpaceSeparatedDate[i]
                            + "T" + whiteSpaceSeparatedDate[i + 2]);
                } else {
                    eventDescription.append(whiteSpaceSeparatedDate[i]).append(" ");
                }
            }
        } catch (DateTimeParseException e) {
            throw new NicoleException("Are you sure your date and time are in the proper "
                                      + "[YYYY-MM-DD] and [HH-MM-SS] format...?");
        }
        if (deadlineFromDateTimeLocalDate.isAfter(deadlineToDateTimeLocalDate)) {
            throw new NicoleException("Erm, the 'to' datetime can't be before 'from' right...");
        }

        if (deadlineFromDateTimeLocalDate.isBefore(LocalDateTime.now())
            || deadlineToDateTimeLocalDate.isBefore(LocalDateTime.now())) {
            throw new NicoleException("Erm, the event can't before now right...");
        }

        deadlineFromDateReformattedString = ""
                + deadlineFromDateTimeLocalDate.getDayOfMonth() + " "
                + deadlineFromDateTimeLocalDate.getMonth().toString() + " "
                + deadlineFromDateTimeLocalDate.getYear();
        deadlineToDateReformattedString = ""
                + deadlineToDateTimeLocalDate.getDayOfMonth() + " "
                + deadlineToDateTimeLocalDate.getMonth().toString() + " "
                + deadlineToDateTimeLocalDate.getYear();
        deadlineFromTimeReformattedString = ""
                + deadlineFromDateTimeLocalDate.getHour() + " "
                + deadlineFromDateTimeLocalDate.getMinute() + " "
                + deadlineFromDateTimeLocalDate.getSecond();
        deadlineToTimeReformattedString = ""
                + deadlineToDateTimeLocalDate.getHour() + " "
                + deadlineToDateTimeLocalDate.getMinute() + " "
                + deadlineToDateTimeLocalDate.getSecond();

    }
    @Override
    public LocalDate getDate() {
        return deadlineFromDateTimeLocalDate.toLocalDate();
    }

    @Override
    public LocalDateTime getFromDateTime() {
        return deadlineFromDateTimeLocalDate;
    }

    @Override
    public LocalDateTime getToDateTime() {
        return deadlineToDateTimeLocalDate;
    }
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "from "
                + deadlineFromDateTimeLocalDate.toLocalDate()
                + " at "
                + formatTime(deadlineFromDateTimeLocalDate.toLocalTime().toString())
                + " to "
                + deadlineToDateTimeLocalDate.toLocalDate()
                + " at "
                + formatTime(deadlineToDateTimeLocalDate.toLocalTime().toString());
    }

    private String formatTime(String fromDateTime) {
        return fromDateTime.length() == 5 ? fromDateTime + ":00" : fromDateTime;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Event)) {
            return false;
        }
        Event task = (Event)object;
        return super.equals(object)
                && this.getFromDateTime().equals(task.getFromDateTime())
                && this.getToDateTime().equals(task.getToDateTime());
    }
}
