import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    public static Command parse(String fullCommand) throws JohnnyException {
        List<String> parsedCommand = Arrays.asList(fullCommand.split(" "));

        if (parsedCommand.isEmpty()) {
            throw new JohnnyException("Enter a command bro.");
        }

        String command = parsedCommand.get(0);

        try {
            switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                if (parsedCommand.size() < 2) {
                    throw new JohnnyException("Which task do you want to mark bro?");
                } else if (parsedCommand.size() > 2) {
                    throw new JohnnyException("I can only mark 1 task bro.");
                }

                int index = Integer.parseInt(parsedCommand.get(1)) - 1;
                return new MarkCommand(index);
            case "unmark":
                if (parsedCommand.size() < 2) {
                    throw new JohnnyException("Which task do you want to unmark bro?");
                } else if (parsedCommand.size() > 2) {
                    throw new JohnnyException("I can only unmark 1 task bro.");
                }

                index = Integer.parseInt(parsedCommand.get(1)) - 1;
                return new UnmarkCommand(index);
            case "delete":
                if (parsedCommand.size() < 2) {
                    throw new JohnnyException("Which task am I supposed to delete bro?");
                } else if (parsedCommand.size() > 2) {
                    throw new JohnnyException("I can only delete 1 task bro.");
                }

                index = Integer.parseInt(parsedCommand.get(1)) - 1;
                return new DeleteCommand(index);
            case "todo":
                if (parsedCommand.size() < 2) {
                    throw new JohnnyException("What is your todo bro?");
                }

                String name = String.join(" ", parsedCommand.subList(1, parsedCommand.size()));
                return new AddTodoCommand(name);
            case "deadline":
                if (parsedCommand.size() < 2) {
                    throw new JohnnyException("What is your deadline bro?");
                }

                int i = parsedCommand.indexOf("/by");

                if (i == -1) {
                    throw new JohnnyException("When is your deadline by bro?");
                }

                name = String.join(" ", parsedCommand.subList(1, i));
                String by = String.join(" ", parsedCommand.subList(i + 1, parsedCommand.size()));
                LocalDateTime byDate = LocalDateTime.parse(by, INPUT_DATE_FORMAT);
                return new AddDeadlineCommand(name, byDate);
            case "event":
                if (parsedCommand.size() < 2) {
                    throw new JohnnyException("What is your deadline bro?");
                }

                i = parsedCommand.indexOf("/from");

                if (i == -1) {
                    throw new JohnnyException("When does your event start from bro?");
                }

                int j = parsedCommand.indexOf("/to");

                if (j == -1) {
                    throw new JohnnyException("When does your event last to bro?");
                }

                name = String.join(" ", parsedCommand.subList(1, i));
                String from = String.join(" ", parsedCommand.subList(i + 1, j));
                String to = String.join(" ", parsedCommand.subList(j + 1, parsedCommand.size()));
                LocalDateTime fromDate = LocalDateTime.parse(from, INPUT_DATE_FORMAT);
                LocalDateTime toDate = LocalDateTime.parse(to, INPUT_DATE_FORMAT);
                return new AddEventCommand(name, fromDate, toDate);
            default:
                throw new JohnnyException("Your command does not make sense bro.");
            }
        } catch (NumberFormatException e) {
            throw new JohnnyException("Enter a valid number bro.");
        } catch (DateTimeParseException e) {
            throw new JohnnyException("Date and Time should be in the format of YYYY/MM/DD HHMM bro.");
        }
    }

}
