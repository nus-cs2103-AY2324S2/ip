import java.time.LocalDate;

public class Parser {
    public static Object[] parseCommand(String input) throws DukeException {
        if (input.equals("bye")) {
            return new Object[] { "bye" };
        } else if (input.equals("list")) {
            return new Object[] { "list" };
        } else if (input.startsWith("todo")) {
            return parseTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input);
        } else if (input.startsWith("mark")) {
            return parseMarkCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else {
            throw new DukeException("UH OH! I don't understand what you mean.. sorry D:");
        }
    }

    private static Object[] parseTodoCommand(String input) throws DukeException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("UH OH! Description for todo cannot be empty!");
        }
        return new Object[] { "todo", description };
    }

    private static Object[] parseDeadlineCommand(String input) throws DukeException {
        String toSplit = input.substring(9);
        String[] parts = toSplit.split("/by");

        String taskDesc = parts[0].trim();
        String deadline = parts[1].trim();

        if (taskDesc.isEmpty() || deadline.isEmpty()) {
            throw new DukeException("UH OH! Description and deadline cannot be empty!");
        }

        LocalDate by = LocalDate.parse(deadline);

        return new Object[] { "deadline", taskDesc, by };
    }

    private static Object[] parseEventCommand(String input) throws DukeException {
        String toSplit = input.substring(6);
        String[] parts = toSplit.split("/from");

        String taskDesc = parts[0].trim();
        String[] timeParts = parts[1].split("/to");

        if (timeParts.length != 2) {
            throw new DukeException("UH OH! Invalid format for event task!");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (taskDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("UH OH! Description/start time/end time cannot be empty!");
        }

        String[] fromParts = from.split(" ", 2);
        String fDate = fromParts[0].trim();
        LocalDate fromDate = LocalDate.parse(fDate);
        String fromTime = fromParts[1].trim();

        String[] toParts = to.split(" ", 2);
        String tDate = toParts[0].trim();
        LocalDate toDate = LocalDate.parse(tDate);
        String toTime = toParts[1].trim();

        if (toDate.isBefore(fromDate)) {
            throw new DukeException("UH OH! The to date has to be later than the from date!!");
        }

        return new Object[] { "event", taskDesc, fromDate, fromTime, toDate, toTime };
    }

    private static Object[] parseMarkCommand(String input) throws DukeException {
        String taskNum = input.substring(5);
        int taskNumber = Integer.parseInt(taskNum);
        return new Object[] { "mark", taskNumber };
    }

    private static Object[] parseDeleteCommand(String input) throws DukeException {
        String taskNum = input.substring(7);
        int taskNumber = Integer.parseInt(taskNum);
        return new Object[] { "delete", taskNumber };
    }
}
