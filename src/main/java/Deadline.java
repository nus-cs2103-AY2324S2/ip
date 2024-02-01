import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

public class Deadline extends Task {
    public Optional<LocalDateTime> byDate;

    Deadline(String name) {
        super(name);
        this.byDate = Optional.empty();
    }

    Deadline(String name, boolean isDone, String byDate, boolean useCustomFormatter) {
        super(name, isDone);
        this.byDate = Optional.of(this.parseDate(byDate, useCustomFormatter));
    }

    Deadline(String name, String byDate) {
        this(name, false, byDate, true);
    }

    public String typeOfTask() {
        return "D";
    }

    public String constructTimeString() {
        if (this.byDate.isPresent()) {
            return String.format("(by: %s)", this.getByDate());
        }
        return "";
    }

    public String getName() {
        return String.format("%s %s", super.getName(), this.constructTimeString());
    }

    public void setByDate(String byDate) throws DateTimeParseException {
        this.byDate = Optional.of(this.parseDate(byDate, true));
    }

    public String getByDate() {
        return this.byDate.map(
            d -> d.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT))).orElse(""
            );
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
