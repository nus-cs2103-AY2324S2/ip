package objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task implements Serializable {
    private final LocalDateTime by;

    public Deadlines(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(formatter));
    }

}
