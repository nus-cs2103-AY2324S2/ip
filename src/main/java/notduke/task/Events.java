package notduke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an Event which is a type of Task
 */
public class Events extends Task {

    /** The start date and time of the Event */
    private LocalDateTime from;
    /** The end date and time of the Event */
    private LocalDateTime to;

    /**
     * Constructs a Event object with the specified name, the start and end date of the Event and mark whether it is
     * done.
     * @param name The name of the Event
     * @param from The start date and time of the Event
     * @param to The end date and time of the Event
     * @param status The status of the Event
     */
    public Events(String name, LocalDateTime from, LocalDateTime to, Boolean status) {
        super(name, status);
        this.from = from;
        this.to = to;

    }

    @Override
    public String happenOn(LocalDate date) {
        boolean isAfterFrom = date.isAfter(from.toLocalDate());
        boolean isBeforeTo = date.isBefore(to.toLocalDate());
        boolean isOnFrom = date.isEqual(from.toLocalDate());
        boolean isOnTo = date.isEqual(to.toLocalDate());
        boolean isBetweenPeriod = isAfterFrom && isBeforeTo;
        boolean isWithinPeriod = isBetweenPeriod || isOnFrom || isOnTo;

        if (isWithinPeriod) {
            return taskInfo();
        } else {
            return "";
        }
    }

    /**
     * @InheritDoc Includes task type Events, from date and to date to string.
     */
    @Override
    public String printMatch(String match) {
        if (super.contains(match)) {
            return taskInfo();
        }
        return "";
    }

    @Override
    public String saveOutput() {
        return "E " + super.saveOutput() + String.format(" | %s/%s", from, to);
    }

    /**
     * @InheritDoc Includes task type Events, from date and to date to string.
     */
    @Override
    public String taskInfo() {
        String output = "";
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));

        output += "[E]";
        output += super.taskInfo();
        return output + " (from: " + formattedFrom + "hrs to: " + formattedTo + "hrs)\n";
    }

    @Override
    public String happenSoon() {
        LocalDate now = LocalDate.now();
        LocalDate nowPlusSeven = now.plus(7, ChronoUnit.DAYS);
        LocalDate start = from.toLocalDate();
        LocalDate end = to.toLocalDate();

        boolean isInSevenDays = start.isEqual(nowPlusSeven);
        boolean isBetweenSevenDays = start.isAfter(now) && start.isBefore(nowPlusSeven);
        boolean isHappeningSoon = isBetweenSevenDays || isInSevenDays;

        boolean isStart = now.isEqual(start);
        boolean isEnd = now.isEqual(end);
        boolean isBetween = now.isAfter(start) && now.isBefore(end);
        boolean isHappeningNow = isBetween || isStart || isEnd;

        if (isHappeningSoon || isHappeningNow) {
            return taskInfo();
        } else {
            return "";
        }
    }
}
