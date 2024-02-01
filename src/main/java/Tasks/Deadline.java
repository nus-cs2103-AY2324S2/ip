package Tasks;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidInputException;
import Exceptions.LeluException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Deadline(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = LocalDateTime.parse(deadline, formatter);

    }

    public static Deadline DeadlineOf(String input) throws InvalidInputException {
        if (input.replaceAll(" ", "").equals("deadline")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        String[] t = input.replaceFirst("deadline ", "").split("/by ");
        if (t.length < 2) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        return new Deadline(t[0], t[1]);
    }
    @Override
    public String saveFormat() {
        int check = this.completed ? 1 : 0;
        return String.format("D | %d | %s | %s\n", check, this.taskName,
                this.deadline.format(formatter));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma")));
    }
}
