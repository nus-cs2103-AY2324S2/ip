package raphael.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import raphael.command.Command;
import raphael.exception.RaphaelException;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Event(String description, String from, String to) throws RaphaelException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, this.inputFormat);
            this.to = LocalDateTime.parse(to, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(Command.TYPE.EVENT));
        }
    }
    public Event(String description, String from, String to, boolean isDone) throws RaphaelException {
        super(description, isDone);
        try {
            this.from = LocalDateTime.parse(from, this.inputFormat);
            this.to = LocalDateTime.parse(to, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(Command.TYPE.EVENT));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(this.outputFormat),
                this.to.format(this.outputFormat));
    }
    @Override
    public String toFileFormat() {
        return String.format("E |&| %s |&| %s |&| %s", super.toFileFormat(),
                this.from.format(this.inputFormat), this.to.format(this.inputFormat));
    }
}
