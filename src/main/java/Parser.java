import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String input) throws AtlasException, NullPointerException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : null;

        switch (command) {
        case "bye":
            return new Command(Command.CommandType.EXIT);
        case "list":
            return new Command(Command.CommandType.LIST);
        case "mark":
            int taskIndex = Integer.parseInt(details) - 1;
            return new Command(Command.CommandType.MARK, taskIndex);
        case "unmark":
            taskIndex = Integer.parseInt(details) - 1;
            return new Command(Command.CommandType.UNMARK, taskIndex);
        case "delete":
            taskIndex = Integer.parseInt(details) - 1;
            return new Command(Command.CommandType.DELETE, taskIndex);
        case "todo":
            return new Command(Command.CommandType.ADD_TODO, new String[]{details});
        case "deadline":
            String[] deadlineDetails = details.split(" /by ");
            return new Command(Command.CommandType.ADD_DEADLINE, deadlineDetails);
        case "event":
            String[] eventDetails = details.split(" /at ");
            if (eventDetails.length != 2) {
                throw new AtlasException("Invalid event format. Please use 'event [description] /at [start date] /at [end date]'.");
            }
            return new Command(Command.CommandType.ADD_EVENT, eventDetails);
        case "tasks_on":
            return new Command(Command.CommandType.TASKS_ON_DATE, new String[]{details});


        default:
            return new Command(Command.CommandType.INVALID);
        }
    }

    public static Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        switch (type) {
        case "T":
            ToDo todo = new ToDo(description);
            if (isDone) todo.toggle();
            return todo;
        case "D":
            try {
                LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.toggle();
                return deadline;
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse deadline date: " + parts[3]);
                return null;
            }
        case "E":
            try {
                LocalDateTime start = LocalDateTime.parse(parts[3].trim());
                LocalDateTime end = LocalDateTime.parse(parts[4].trim());
                Event event = new Event(description, start, end);
                if (isDone) event.toggle();
                return event;
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse event dates: " + parts[3] + " and " + parts[4]);
                return null;
            }
        default:
            return null;
        }
    }
}
