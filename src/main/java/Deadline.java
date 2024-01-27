import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    public static Deadline deadlineParse(boolean isDone, String input) throws TaskCreationException, DateTimeParseException {
        if (!input.contains("/by")) {
            throw new TaskCreationException("Missing parameters: /by");
        }

        String description = input.substring(8, input.indexOf("/by")).trim();
        String deadlineString = input.substring(input.indexOf("/by") + 3).trim();
        // Check if inputs are blank
        String missingInfo = "";

        if (description.equals("")) {
            missingInfo = missingInfo + "\"description\" ";
        }
        if (deadlineString.equals("")) {
            missingInfo = missingInfo + "\"by\" ";
        }
        if (!missingInfo.equals("")) {
            throw new TaskCreationException("Missing information: " +  missingInfo);
        }

        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineString, Parser.inputdtFormatter);
        Deadline d = new Deadline(isDone, description, deadlineDateTime);
        return d;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(Parser.outputdtFormatter) + ")";
    }

    @Override
    public String toSave() {
        return "[D]|" + super.toSave() + "|" + deadline.format(Parser.inputdtFormatter);
    }
}
