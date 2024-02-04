package Duke.activityAndUtility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Deadline} class represents a deadline activity, implementing the {@link Activity} interface. It encapsulates
 * the status, name, and due date and time of a task. This class is designed to manage deadlines, allowing for them to be
 * printed, named, and marked as complete or incomplete.
 */
public class Deadline implements Activity {
    List<String> act;
    LocalDate date;
    LocalTime time;

    /**
     * Constructs a new {@code Deadline} instance from the given status, name, and date and time string.
     * The date and time string is parsed to set the {@code LocalDate} and {@code LocalTime} fields.
     *
     * @param status     The initial status of the deadline (e.g., completed or not).
     * @param name       The name or description of the deadline task.
     * @param dateAndTime The combined date and time string for the deadline, which will be parsed to set the deadline's date and time.
     */
    public Deadline(String status, String name, String dateAndTime) {
        act = new ArrayList<>();
        act.add(status); // Status
        act.add(name); // Task name
        act.add(dateAndTime);
        LocalDate date = DateTimeFormat.getDate(dateAndTime);
        LocalTime time = DateTimeFormat.getTime(dateAndTime);
        this.date = date;
        this.time = time;
    }

    /**
     * Prints the activity's details, including its status, name, and due date and time. If the date and time are not null,
     * they are formatted and printed; otherwise, the raw input string is printed.
     */
    @Override
    public void printActivity() {
        if (date != null && time != null) {
            String dateOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String timeOutput = time.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.format("\t\t [D][%s]%s(by: %s, %s)%n", act.get(0), act.get(1), dateOutput, timeOutput);
        } else {
            System.out.format("\t\t [D][%s]%s(by: %s)%n", act.get(0), act.get(1), act.get(2));
        }
    }

    /**
     * Returns the name or description of the deadline task.
     *
     * @return A {@code String} representing the name of the deadline.
     */
    @Override
    public String getName() {
        return act.get(1);
    }

    /**
     * Marks the deadline as complete or incomplete based on the input. If the input is "mark", the status is set to a
     * checkmark (indicating completion). If the input is "unmark", the status is set to an "X" (indicating not completed).
     * After marking, the updated activity details are printed.
     *
     * @param input A {@code String} indicating whether to mark the deadline as completed ("mark") or not completed ("unmark").
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
