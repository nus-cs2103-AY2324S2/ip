import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Event extends Task {
    public Optional<String> fromDate;
    public Optional<String> toDate;

    Event(String name) {
        super(name);
        this.fromDate = Optional.empty();
        this.toDate = Optional.empty();
    }

    Event(String name, boolean isDone, String fromString, String toString) {
        super(name, isDone);
        this.fromDate = Optional.of(fromString);
        this.toDate = Optional.of(toString);
    }

    Event(String name, String fromString, String toString) {
        this(name, false, fromString, toString);
    }

    public String typeOfTask() {
        return "E";
    }

    public String constructTimeString() {
        List<String> arr = new ArrayList<>();
        if (this.fromDate.isPresent()) {
            arr.add(String.format("from: %s", this.fromDate.get()));
        }
        if (this.toDate.isPresent()) {
            arr.add(String.format("to: %s", this.toDate.get()));
        }
        String s = String.join(" ", arr);
        return "(" + s + ")";
    }

    public String getName() {
        return String.format("%s %s", super.getName(), this.constructTimeString());
    }
    
    public void setFromDate(String fromDate) {
        this.fromDate = Optional.of(fromDate);
    }

    public void setToDate(String toDate) {
        this.toDate = Optional.of(toDate);
    }

    public String getFromDate() {
        return this.fromDate.orElse("");
    }

    public String getToDate() {
        return this.toDate.orElse("");
    }

    protected ArrayList<String> exportDataAsArray() {
        ArrayList<String> data = super.exportDataAsArray();
        data.add(this.getFromDate());
        data.add(this.getToDate());
        return data;
    }
}
