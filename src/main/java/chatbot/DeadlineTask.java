package chatbot;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exceptions.InvalidArgumentException;

class DeadlineTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private final LocalDateTime deadlineTime;

    public DeadlineTask(String desc, String deadlineTime) throws InvalidArgumentException {
        super(desc);
        try {
            this.deadlineTime = Parser.parseDate(deadlineTime);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
