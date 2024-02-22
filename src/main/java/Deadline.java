// Deadline.java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "D |" + super.toString() + " | " + formattedDate;
    }
}
