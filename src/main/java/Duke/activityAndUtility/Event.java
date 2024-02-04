package Duke.activityAndUtility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Event} class represents an event activity, implementing the {@link Activity} interface. It encapsulates
 * the status, name, start date and time, and end date and time of an event. This class is designed to manage events,
 * allowing for them to be printed, named, and marked as complete or incomplete. It ensures that the event's start
 * date and time are logically before its end date and time.
 */
public class Event implements Activity {
    List<String> act;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;

    /**
     * Constructs a new {@code Event} instance with the given status, name, start date and time, and end date and time.
     * It parses the start and end date and time strings to set the corresponding {@code LocalDate} and {@code LocalTime} fields.
     * Validates that the start date and time are before the end date and time, throwing a {@code RuntimeException} if not.
     *
     * @param status            The initial status of the event (e.g., completed or not).
     * @param name              The name or description of the event.
     * @param startDateAndTime  The start date and time string for the event, to be parsed.
     * @param endDateAndTime    The end date and time string for the event, to be parsed.
     * @throws RuntimeException if the end date is before the start date, or if the end time is before the start time on the same day.
     */
    public Event(String status, String name, String startDateAndTime, String endDateAndTime) {
        act = new ArrayList<>();
        act.add(status); // Status
        act.add(name); // Event name
        act.add(startDateAndTime);
        act.add(endDateAndTime);
        LocalDate startDate = DateTimeFormat.getDate(startDateAndTime);
        LocalTime startTime = DateTimeFormat.getTime(startDateAndTime);
        LocalDate endDate = DateTimeFormat.getDate(endDateAndTime);
        LocalTime endTime = DateTimeFormat.getTime(endDateAndTime);
        if (startDate != null && endDate != null) {
            if (startDate.isAfter(endDate)) {
                throw new RuntimeException("Finish date ahead of start date");
            } else if (startDate.isEqual(endDate)) {
                if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
                    throw new RuntimeException ("Finish time ahead of start time");
                }
            }
        }
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Prints the event's details, including its status, name, and start and end dates and times.
     * If the start and end dates and times are not null, they are formatted and printed; otherwise,
     * the raw input strings are printed.
     */
    @Override
    public void printActivity() {
        if (startDate != null && startTime != null && endDate != null && endTime != null) {
            String startDateOutput = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String startTimeOutput = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String endDateOutput = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String endTimeOutput = endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.format("\t\t [E][%s]%s(from: %s %s to: %s %s)%n",
                    act.get(0), act.get(1), startDateOutput, startTimeOutput, endDateOutput, endTimeOutput);
        } else {
            System.out.format("\t\t [E][%s]%s(%s %s)%n",
                    act.get(0), act.get(1), act.get(2), act.get(3));
        }
    }

    /**
     * Returns the name or description of the event.
     *
     * @return A {@code String} representing the name of the event.
     */
    @Override
    public String getName() {
        return act.get(1);
    }

    /**
     * Marks the event as complete or incomplete based on the input. If the input is "mark", the status is set to a
     * checkmark (indicating completion). If the input is "unmark", the status is set to an "X" (indicating not completed).
     * After marking, the updated event details are printed.
     *
     * @param input A {@code String} indicating whether to mark the event as completed ("mark") or not completed ("unmark").
     */
    @Override
    public void mark(String input) {
        if (Objects.equals(input, "mark")) {
            act.set(0, "√");
        } else if (Objects.equals(input, "unmark")) {
            act.set(0, "X");
        }
        System.out.format("\t%sed:%n", input);
        printActivity();
    }
}
