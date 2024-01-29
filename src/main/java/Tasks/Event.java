package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, boolean status, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, status, Type.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        return formattedStartDate;
    }

    public String getEndDate() {
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        return formattedEndDate;
    }
}
