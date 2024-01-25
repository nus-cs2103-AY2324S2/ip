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
    
}
