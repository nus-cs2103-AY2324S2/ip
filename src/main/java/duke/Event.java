package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String name, String startTime, String endTime) throws DukeException {
        super(name);
        this.startTime = Task.parse(startTime);
        this.endTime = Task.parse(endTime);
        if (!this.startTime.isBefore(this.endTime)) {
            throw new DukeException("Oops! It seems the event ends before it starts!");
        }
    }

    @Override
    String taskToLine() {
        return "E | " + super.taskToLine() + " | " + startTime.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")) + " | " + endTime.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    boolean canMatchDate(LocalDate localDate) {
        return startTime.toLocalDate().equals(localDate) ||
                endTime.toLocalDate().equals(localDate) ||
                (startTime.toLocalDate().isBefore(localDate) &&
                        endTime.toLocalDate().isAfter(localDate));
    }

    @Override
    public String toString() {
        if (startTime.toLocalDate().equals(endTime.toLocalDate())) {
            return "[E]" + super.toString() + String.format(" (from: %s to: %s) ", startTime.format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")), endTime.toLocalTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            return "[E]" + super.toString() + String.format(" (from: %s to: %s) ", startTime.format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")), endTime.format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")));
        }
    }
}
