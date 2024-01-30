package chatbot;

import chatbot.exceptions.InvalidArgumentException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public EventTask(String desc, String startTime, String endTime) throws InvalidArgumentException {
        super(desc);
        try {
            this.startTime = Parser.parseDate(startTime);
            this.endTime = Parser.parseDate(endTime);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

