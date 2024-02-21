package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * The Parser class is responsible for parsing user input commands and returning the appropriate Command object.
 * It parses user commands and returns Command objects based on the command type and input parameters.
 */
public class Parser {

    /**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param input The user input command to be parsed.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the input command is invalid or cannot be parsed.
     */
    public static Command parseCommand(String input) throws DukeException {
        if (input.trim().equals("bye")) {
            return new ByeCommand("bye");

        } else if (input.trim().equals("list")) {
            return new ListCommand("list");

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

        } else if (input.startsWith("find")) { //todo
            return parseFindCommand(input);

        } else if (input.startsWith("display tagged")) {
            return parseTagCommand(input);

        } else {
            throw new DukeException("UH OH! I don't understand what you mean.. sorry D:");
        }
    }

    private static Command parseTodoCommand(String input) throws DukeException {
        String toSplit = input.substring(5).trim();

        String[] parts = toSplit.split("#");

        String description = parts[0].trim();

        if (description.isEmpty()) {
            throw new DukeException("UH OH! Description for todo cannot be empty!");
        }

        String tag;
        if (parts.length == 2) {
            tag = parts[1].trim();
            return new TodoCommand("todo", description, tag);
        }
        return new TodoCommand("todo", description);

    }

    private static Command parseDeadlineCommand(String input) throws DukeException {
        String toSplit = input.substring(9);
        String[] parts = toSplit.split("/by");

        String taskDesc = parts[0].trim();

        String[] deadlineTagParts = parts[1].split("#");
        String deadline = deadlineTagParts[0].trim();

        // if there was no task description or deadline specified, throw exception
        if (taskDesc.isEmpty() || deadline.isEmpty()) {
            throw new DukeException("UH OH! Description and deadline cannot be empty!");
        }

        LocalDate by = LocalDate.parse(deadline);

        if (deadlineTagParts.length == 2) {
            String tag = deadlineTagParts[1].trim();
            return new DeadlineCommand("deadline", taskDesc, by, tag);
        }

        return new DeadlineCommand("deadline", taskDesc, by);
    }

    private static Command parseEventCommand(String input) throws DukeException {
        String[] descTiming = splitDescTiming(input);

        String taskDesc = descTiming[0].trim();
        String[] fromTo = descTiming[1].split("/to");

        // if format the to/from dates were not keyed in properly, throw exception
        if (fromTo.length != 2) {
            throw new DukeException("UH OH! Invalid format for event task!");
        }

        String from = fromTo[0].trim();
        String to = fromTo[1].trim();

        // if there was no task description or to/from dates specified, throw exception
        if (taskDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("UH OH! Description/start time/end time cannot be empty!");
        }

        // retrieve date and time for "from"
        String[] fromParts = from.split(" ", 2);
        String fDate = fromParts[0].trim();
        LocalDate fromDate = LocalDate.parse(fDate);
        String fromTime = fromParts[1].trim();

        // retrieve date and time for "to"
        String[] toParts = to.split(" ", 2);
        String tDate = toParts[0].trim();
        LocalDate toDate = LocalDate.parse(tDate);
        String[] toTimeAndTag = toParts[1].split("#");
        String toTime = toTimeAndTag[0].trim();

        if (toDate.isBefore(fromDate)) {
            throw new DukeException("UH OH! The to date has to be later than the from date!!");
        }

        String tag;
        if (toTimeAndTag.length == 2) {
            tag = toTimeAndTag[1].trim();
            return new EventCommand("event", taskDesc, fromDate, fromTime, toDate, toTime, tag);
        }

        return new EventCommand("event", taskDesc, fromDate, fromTime, toDate, toTime);
    }

    private static String[] splitDescTiming(String input) {
        String toSplit = input.substring(6);
        String[] parts = toSplit.split("/from");
        return parts;
    }

    private static Command parseMarkCommand(String input) throws DukeException {
        String taskNum = input.substring(5);
        int taskNumber = Integer.parseInt(taskNum);
        assert taskNumber > 0: "Task number has to be greater than 0";
        return new MarkCommand("mark", taskNumber);
    }

    private static Command parseDeleteCommand(String input) throws DukeException {
        String taskNum = input.substring(7);
        int taskNumber = Integer.parseInt(taskNum.trim());
        assert taskNumber > 0: "Task number has to be greater than 0";
        return new DeleteCommand("delete", taskNumber);
    }

    private static Command parseFindCommand(String input) throws DukeException {
        String kw = input.substring(5);
        String keyword = kw.trim();
        return new FindCommand("find", keyword);
    }

    private static Command parseTagCommand(String input) throws DukeException {
        String tagWithHash = input.substring(14);
        String tag = (tagWithHash.trim()).substring(1);
        return new DisplayTaggedCommand("find tag", tag);
    }
}
