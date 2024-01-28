package Task;

import NicoleExceptions.NicoleException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
public class Event extends Task {
    private LocalDateTime deadlineFromDateTimeLocalDate;
    private LocalDateTime deadlineToDateTimeLocalDate;
    private String deadlineFromDateReformattedString;
    private String deadlineFromTimeReformattedString;
    private String deadlineToDateReformattedString;
    private String deadlineToTimeReformattedString;
    public Event(String name) throws NicoleException {
        super();
        if (name.contains("null")) {
            throw new NicoleException("Describe your event like this: " +
                                      "event [name] from YYYY-MM-DD at HH-MM-SS to YYYY-MM-DD at HH-MM-SS");
        }
        this.parseDateTime(name);
        super.setName(name);
    }

    private void parseDateTime(String name) throws NicoleException {
        String[] whiteSpaceSeparatedDate = name.split(" ");
        try {
            for (int i = 0; i < whiteSpaceSeparatedDate.length; i++) {
                if (whiteSpaceSeparatedDate[i].matches("\\d{4}-\\d{2}-\\d{2}") && i < whiteSpaceSeparatedDate.length - 4) {
                    this.deadlineFromDateTimeLocalDate = LocalDateTime.parse(whiteSpaceSeparatedDate[i]
                            + "T" + whiteSpaceSeparatedDate[i + 2]);
                } else if (whiteSpaceSeparatedDate[i].matches("\\d{4}-\\d{2}-\\d{2}") && i > whiteSpaceSeparatedDate.length - 4) {
                    this.deadlineToDateTimeLocalDate = LocalDateTime.parse(whiteSpaceSeparatedDate[i]
                            + "T" + whiteSpaceSeparatedDate[i + 2]);
                }
            }
        } catch (DateTimeParseException e) {
            throw new NicoleException("Are you sure your date and time are in the proper [YYYY-MM-DD], [HH-MM-SS] format...?");
        }
        if (this.deadlineFromDateTimeLocalDate.isAfter(this.deadlineToDateTimeLocalDate)) {
            throw new NicoleException("Erm, the 'to' datetime can't be before 'from' right...");
        }
        this.deadlineFromDateReformattedString = ""
                + this.deadlineFromDateTimeLocalDate.getDayOfMonth() + " "
                + this.deadlineFromDateTimeLocalDate.getMonth().toString() + " "
                + this.deadlineFromDateTimeLocalDate.getYear();
        this.deadlineToDateReformattedString = ""
                + this.deadlineToDateTimeLocalDate.getDayOfMonth() + " "
                + this.deadlineToDateTimeLocalDate.getMonth().toString() + " "
                + this.deadlineToDateTimeLocalDate.getYear();
        this.deadlineFromTimeReformattedString = ""
                + this.deadlineFromDateTimeLocalDate.getHour() + " "
                + this.deadlineFromDateTimeLocalDate.getMinute() + " "
                + this.deadlineFromDateTimeLocalDate.getSecond();
        this.deadlineToTimeReformattedString = ""
                + this.deadlineToDateTimeLocalDate.getHour() + " "
                + this.deadlineToDateTimeLocalDate.getMinute() + " "
                + this.deadlineToDateTimeLocalDate.getSecond();

    }
    @Override
    public LocalDate getDate() {
        return this.deadlineFromDateTimeLocalDate.toLocalDate();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
