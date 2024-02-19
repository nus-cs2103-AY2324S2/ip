package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Event class to represent tasks with a start and end time
 */
public class Event extends Task {
    private Optional<LocalDateTime> fromDate;
    private Optional<LocalDateTime> toDate;

    public Event(String name) {
        super(name);
        this.fromDate = Optional.empty();
        this.toDate = Optional.empty();
    }

    public Event(String name, boolean isDone, String fromString, String toString, boolean useCustomFormatter) {
        super(name, isDone);
        this.fromDate = Optional.of(this.parseDate(fromString, useCustomFormatter));
        this.toDate = Optional.of(this.parseDate(toString, useCustomFormatter));
    }

    Event(String name, String fromString, String toString) {
        this(name, false, fromString, toString, true);
    }

    public String typeOfTask() {
        return "E";
    }

    private String constructTimeString() {
        List<String> arr = new ArrayList<>();
        if (this.fromDate.isPresent()) {
            arr.add(String.format("from: %s", this.getFromDate()));
        }
        if (this.toDate.isPresent()) {
            arr.add(String.format("to: %s", this.getToDate()));
        }
        String s = String.join(" ", arr);
        return "(" + s + ")";
    }

    public String getName() {
        return String.format("%s %s", super.getName(), this.constructTimeString());
    }

    public String getFromDate() {
        return this.fromDate.map(
            d -> d.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT))).orElse(""
        );
    }

    public void setFromDate(String fromDate) {
        this.fromDate = Optional.of(this.parseDate(fromDate, true));
    }

    public String getToDate() {
        return this.toDate.map(
            d -> d.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT))).orElse(""
        );
    }

    public void setToDate(String toDate) {
        this.toDate = Optional.of(this.parseDate(toDate, true));
    }

    protected String getFromDateIso() {
        return this.fromDate.map(d -> d.toString()).orElse("");
    }

    protected String getToDateIso() {
        return this.toDate.map(d -> d.toString()).orElse("");
    }

    protected ArrayList<String> exportDataAsArray() {
        ArrayList<String> data = super.exportDataAsArray();
        data.add(this.getFromDateIso());
        data.add(this.getToDateIso());
        return data;
    }
}
