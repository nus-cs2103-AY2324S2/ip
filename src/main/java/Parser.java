import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static CommandType parseCommand(String userInput) throws DukeException {
        String[] parts = userInput.trim().split("\\s+", 2);
        String commandStr = parts[0].toUpperCase();

        try {
            return CommandType.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Command processCommand(String userInput) throws DukeException {

        try {
            CommandType command = Parser.parseCommand(userInput);

            switch (command) {
                case LIST:
                    return new ListCommand();
                case MARK:
                    return new MarkCommand();
                case UNMARK:
                    return new UnmarkCommand();
                case DELETE:
                    return new DeleteCommand();
                case BYE:
                    return new ExitCommand();
                case TODO:
                case DEADLINE:
                case EVENT:
                    return new AddCommand();
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseTask(String userInput) throws DukeException {
        String[] parts = userInput.split("\\s+", 2);
        if (parts.length < 2) {
            throw new DukeException("Description cannot be empty.");
        }

        String taskType = parts[0].toLowerCase();
        String taskDetails = parts[1];

        switch (taskType) {
            case "todo":
                return new Todo(taskDetails);
            case "deadline":
                return parseDeadline(taskDetails);
            case "event":
                return parseEvent(taskDetails);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Deadline parseDeadline(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid deadline format. Please include '/by' followed by the deadline.");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        LocalDateTime dateTime = parseDateTime(by);

        return new Deadline(description, dateTime);
    }

    private static Event parseEvent(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid event format. Please include '/from' followed by start time and '/to' followed by end time.");
        }

        String description = parts[0].trim();
        String[] dateAndTime = parts[1].split("/to", 2);
        if (dateAndTime.length < 2) {
            throw new DukeException("Invalid event format. Please include '/from' followed by start time and '/to' followed by end time.");
        }

        String start = dateAndTime[0].trim();
        String end = dateAndTime[1].trim();

        LocalDateTime startTime = parseDateTime(start);
        LocalDateTime endTime = parseDateTime(end);

        // Check if the start time is before the end time
        if (startTime.isAfter(endTime)) {
            throw new DukeException("Invalid event times. The start time is after the end time.");
        }

        return new Event(description, startTime, endTime);
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) throws DukeException {
        try {
            if (dateTimeStr.equalsIgnoreCase("today")) {
                return LocalDateTime.now().with(LocalTime.MIN);
            } else if (dateTimeStr.equalsIgnoreCase("tomorrow")) {
                return LocalDateTime.now().plusDays(1).with(LocalTime.MAX);
            } else if (dateTimeStr.contains(" ")) {
                return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            } else {
                LocalDate date = LocalDate.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                return date.atStartOfDay();
            }
        } catch (Exception e) {
            throw new DukeException("Invalid date/time format. Please use 'yyyy/MM/dd HHmm', 'yyyy/MM/dd', 'today', or 'tomorrow'.");
        }
    }

    
    public static int extractTaskIndex(String userInput) throws DukeException {
        try {
            return Integer.parseInt(userInput.split("\\s+")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
    }
}
