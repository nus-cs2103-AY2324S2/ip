package goldbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Deadline class for tasks with deadlines
 */
public class Deadline extends Task {
    private Optional<LocalDateTime> byDate;

    public Deadline(String name) {
        super(name);
        this.byDate = Optional.empty();
    }

    public Deadline(String name, boolean isDone, String byDate, boolean useCustomFormatter) {
        super(name, isDone);
        this.byDate = Optional.of(this.parseDate(byDate, useCustomFormatter));
    }

    Deadline(String name, String byDate) {
        this(name, false, byDate, true);
    }

    public String typeOfTask() {
        return "D";
    }

    private String constructTimeString() {
        if (this.byDate.isPresent()) {
            return String.format("(by: %s)", this.getByDate());
        }
        return "";
    }

    /**
     * Returns a string representation of the task name with deadline time
     *
     * @return String of task name with deadline time
     */
    public String getName() {
        return String.format("%s %s", super.getName(), this.constructTimeString());
    }

    /**
     * Gets the deadline date
     *
     * @return String of deadline date in format specified by OUTPUT_DATE_TIME_FORMAT
     */
    public String getByDate() {
        return this.byDate.map(
            d -> d.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT))).orElse(""
        );
    }

    /**
     * Sets the deadline date
     *
     * @param byDate Deadline date
     * @throws DateTimeParseException If the date is not in the correct format
     */
    public void setByDate(String byDate) throws DateTimeParseException {
        this.byDate = Optional.of(this.parseDate(byDate, true));
    }

    protected String getByDateIso() {
        return this.byDate.map(d -> d.toString()).orElse("");
    }

    protected ArrayList<String> exportDataAsArray() {
        ArrayList<String> data = super.exportDataAsArray();
        data.add(this.getByDateIso());
        return data;
    }
}
