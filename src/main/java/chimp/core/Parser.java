package chimp.core;
import java.time.LocalDate;
import chimp.command.*;
import chimp.exception.CommandParseException;
import chimp.exception.InvalidCommandException;

public class Parser {
    /**
     * Parses a given user input to a command.
     *
     * @param input the user input to be parsed
     * @return the parsed Command object
     * @throws InvalidCommandException if the command is invalid
     * @throws CommandParseException if there is an error while parsing the command
     */
    public static Command parse(String input) throws InvalidCommandException, CommandParseException {
        int num;
        input = input.toLowerCase();
        String command = input.split(" ")[0];
        String arg = null;
        String text = null;

        assert command != null : "command should not be null";

        if (input.split(" ").length > 1) {
            // split at first space, and take everything on the right
            arg = input.substring(input.indexOf(' '), input.length());
        }

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            num = Integer.parseInt(arg.trim());
            return new MarkCommand(num);
        case "unmark":
            num = Integer.parseInt(arg.trim());
            return new UnmarkCommand(num);
        case "todo":
            if (arg == null || arg.equals("")) {
                throw new CommandParseException("todo must have a desc");
            }
            return new TodoCommand(arg);
        case "event":
            String from = extractDateString(arg, 1);
            String to = extractDateString(arg, 2);

            LocalDate fromDate = parseStringToDate(from);
            LocalDate toDate = parseStringToDate(to);

            text = extractTextArg(arg);

            return new EventCommand(text, fromDate, toDate);
        case "deadline":
            String by = extractDateString(arg, 1);
            LocalDate byDate = parseStringToDate(by);
            text = extractTextArg(arg);

            return new DeadlineCommand(text, byDate);
        case "delete":
            num = Integer.parseInt(arg.strip());
            return new DeleteCommand(num);
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(arg);
        default:
            throw new InvalidCommandException("command \"" + command + "\" is invalid");
        }
    }

    private static String extractDateString(String arg, int argPos) {
        String fromSubCommand = arg.split("/")[argPos];
        String from = fromSubCommand.substring(fromSubCommand.indexOf(' '));
        from = from.strip();
        return from;
    }

    private static String extractTextArg(String arg) {
        return arg.split("/")[0].strip();
    }

    private static LocalDate parseStringToDate(String date) throws CommandParseException {
        if (date == null || date.equals("")) {
            throw new CommandParseException("command needs by date/time!");
        }

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new CommandParseException("Invalid date format provided to command");
        }

        return parsedDate;
    }
}