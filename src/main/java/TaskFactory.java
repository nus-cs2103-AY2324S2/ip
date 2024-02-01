import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    public static Task createTask(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        if(!(inputParts[0].equals("todo")) && !(inputParts[0].equals("deadline")) && !(inputParts[0].equals("event"))) {
            throw new DukeException("Don't talk nonsense");
        } else if (inputParts.length < 2) {
            throw new DukeException("What do you want to do");
        }

        String taskType = inputParts[0].toLowerCase();

        switch (taskType) {
            case "todo":
                return new Todo(inputParts[1]);
            case "deadline":
                return createDeadlineTask(inputParts[1]);
            case "event":
                return createEventTask(inputParts[1]);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    private static Task createDeadlineTask(String input) throws DukeException {
        String[] parts = input.split("/by");
        if (parts.length < 2) {
            throw new DukeException("Please provide a deadline in the format '/by <yyyy-mm-dd>'");
        }

        LocalDate dateTime = parseDate(parts[1].trim());
        return new Deadline(parts[0].trim(), dateTime);
    }

    private static Task createEventTask(String input) throws DukeException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide an event in the format '/from <yyyy-mm-dd> /to <yyyy-mm-dd>'");
        }

        LocalDate fromDateTime = parseDate(parts[1].trim().split("/to")[0].trim());
        LocalDate toDateTime = parseDate(parts[1].trim().split("/to")[1].trim());

        return new Event(parts[0].trim(), fromDateTime, toDateTime);
    }

    private static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use 'yyyy-mm-dd'");
        }
    }
}
