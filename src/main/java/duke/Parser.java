package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents the parser.
 */
public class Parser {

    /**
     * Parses the given text and returns the corresponding command.
     *
     * @param text  The text to be parsed.
     * @param state The state of the app.
     * @return The corresponding command.
     * @throws DukeException If any parsing related arguments is encountered.
     */
    public static Command parse(String text, State state) throws DukeException, DateTimeParseException {
        if (text.contains("|")) {
            throw new DukeException("No | pleaserin-o!");
        }
        List<String> parts = Arrays.asList(text.split(" "));
        if (parts.isEmpty()) {
            throw new DukeException("Invalid Commands");
        }
        String header = parts.get(0);
        Command command;
        switch (header) {
        case "list":
            command = new ListCommand();
            break;
        case "view_schedule":
            if (parts.size() == 1) {
                throw new DukeException("Please add date!");
            }
            String dateString = parts.get(1);
            if (dateString == null) {
                throw new DukeException("Mamma-mia provide-o ur date-o!");
            }
            command = new ViewScheduleCommand(LocalDate.parse(dateString));
            break;
        case "mark":
            command = new MarkCommand(Integer.parseInt(parts.get(1)));
            break;
        case "todo":
            if (parts.size() <= 1) {
                throw new DukeException("Mamma-Mia where's ur description!");
            }
            command = new AddTodoCommand(String.join(" ", parts.subList(1, parts.size())));
            break;
        case "deadline":
            int byIndex = parts.indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("No /by???");
            }
            if (byIndex == parts.size() - 1) {
                throw new DukeException("Add something after your /by...");
            }
            String deadline = String.join(" ", parts.subList(byIndex + 1, parts.size()));
            LocalDate deadlineDate = LocalDate.parse(deadline);
            String description = String.join(" ", parts.subList(0, byIndex));

            command = new AddDeadlineCommand(description, deadlineDate);
            break;
        case "event":
            int fromIndex = parts.indexOf("/from");
            int toIndex = parts.indexOf("/to");
            if (fromIndex == -1) {
                throw new DukeException("No /from???");
            }
            if (fromIndex == parts.size() - 1) {
                throw new DukeException("Add something after your /from...");
            }
            if (toIndex == -1) {
                throw new DukeException("No /to???");
            }
            if (toIndex == parts.size() - 1) {
                throw new DukeException("Add something after your /to...");
            }
            if (fromIndex > toIndex) {
                throw new DukeException("Don't throw funny funny... Mamma-Mia!");
            }
            String start = String.join(" ", parts.subList(fromIndex + 1, toIndex));
            String end = String.join(" ", parts.subList(toIndex + 1, parts.size()));

            String desc = String.join(" ", parts.subList(0, fromIndex));

            command = new AddEventCommand(
                    desc,
                    LocalDate.parse(start),
                    LocalDate.parse(end));
            break;
        case "delete":
            if (parts.size() < 2) {
                throw new DukeException("Mamma Mia! Where is-a the index?");
            }
            String indexAsString = parts.get(1);
            Integer index = Util.parseInt(indexAsString) - 1;
            if (index == null) {
                throw new DukeException("Input-o Number-o please-o!");
            }
            if (index < 0 || index >= state.getTasks().size()) {
                throw new DukeException("Invalid number >:(");
            }

            command = new DeleteCommand(index);
            break;
        case "find":
            command = new FindCommand(String.join(" ", parts.subList(1, parts.size())));
            break;
        default:
            throw new DukeException("Mamma Mia! Me-no understand!");
        }
        return command;
    }
}
