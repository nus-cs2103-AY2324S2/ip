package Tasks;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidInputException;
import Exceptions.LeluException;

public class Deadline extends Task {
    protected String deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
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
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
