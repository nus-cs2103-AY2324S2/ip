import java.util.Optional;

public class Deadline extends Task {
    public Optional<String> byDate;

    Deadline(String name) {
        super(name);
        this.byDate = Optional.empty();
    }

    Deadline(String name, String byDate) {
        super(name);
        this.byDate = Optional.of(byDate);
    }

    public String constructTimeString() {
        if (this.byDate.isPresent()) {
            return String.format("(by: %s)", this.byDate.get());
        }
        return "";
    }

    public String getName() {
        return String.format("%s, %s", super.getName(), this.constructTimeString());
    }
}
