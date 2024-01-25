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

    Event(String name, String fromString, String toString) {
        super(name);
        this.fromDate = Optional.of(fromString);
        this.toDate = Optional.of(toString);
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
        return String.format("%s, %s", super.getName(), this.constructTimeString());
    }
    
}
