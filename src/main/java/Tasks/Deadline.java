package Tasks;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidInputException;
import Exceptions.LeluException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a deadline task by encapsulating information about a specific deadline,
 * including the description, due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Deadline(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = LocalDateTime.parse(deadline, formatter);

    }

    /**
     * A factory method which creates a Deadline object using the user input.
     *
     * @param input User input which starts with "deadline".
     * @return A Deadline object with the specified name and due date and time.
     * @throws InvalidFormatException When the description of task or due date and time is empty in the user input.
     */
    public static Deadline DeadlineOf(String input) throws InvalidFormatException {
        if (input.replaceAll(" ", "").equals("deadline")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        String[] t = input.replaceFirst("deadline ", "").split("/by ");
        if (t.length < 2) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        return new Deadline(t[0].replaceAll("\\s+$", ""), t[1]);
    }

    /**
     * Formats the details of the Deadline object as a String to be written to a text file.
     *
     * @return A String containing the description and due date and time of a Deadline object.
     */
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
