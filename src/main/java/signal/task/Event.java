package signal.task;

import java.time.LocalDate;
import java.time.LocalTime;

import static signal.Duke.formatDate;
import static signal.Duke.formatTime;

public class Event extends Task {

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    public Event(String description, String start, String end) {
        super(description);
        String[] parseStart = start.split(" ");
        String[] parseEnd = end.split(" ");
        if (parseStart.length > 1) {
            this.startTime = LocalTime.parse(parseStart[1]);
        }
        this.startDate = LocalDate.parse(parseStart[0]);
        if (parseEnd.length > 1) {
            this.endDate = LocalDate.parse(parseEnd[0]);
            this.endTime = LocalTime.parse(parseEnd[1]);
        } else {
            this.endTime = LocalTime.parse(parseEnd[0]);
        }

    }

    @Override
    public LocalDate getDue() {
        return this.startDate;
    }

    @Override
    public String toString() {
        return "E" + super.toString()
                + " | from: " + formatDate(startDate) + (startTime != null ? " " + formatTime(startTime) : "")
                +  " | to: " + (endDate != null ? " " + formatDate(endDate) : "") + formatTime(endTime);
    }

    @Override
    public String checkType() {
        return "Event";
    }

}
