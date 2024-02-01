import javax.lang.model.element.ModuleElement;
import java.time.format.DateTimeParseException;

public class Parser {
    public Command getCommand(String line) throws FelixException {
        // separate first word from rest of words
        String[] words = line.split(" ", 2);
        switch (words[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    return new MarkCommand(Integer.parseInt(words[1]) - 1);
                } catch (NumberFormatException err) {
                    throw new FelixException("Enter a number after \"mark\"");
                }
            case "unmark":
                try {
                    return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
                } catch (NumberFormatException err) {
                    throw new FelixException("Enter a number after \"unmark\"");
                }
            case "delete":
                try {
                    return new DeleteCommand(Integer.parseInt(words[1]) - 1);
                } catch (NumberFormatException err) {
                    throw new FelixException("Enter a number after \"delete\"");
                }
            case "todo":
            case "deadline":
            case "event":
                return new AddTaskCommand(this.generateTask(words));
            default:
                throw new FelixException("Unrecognised command");
        }
    }

    private Task generateTask(String[] words) throws FelixException {
        Task task;
        if (words[0].equals("todo")) {
            try {
                task = new ToDo(words[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("The description of a todo cannot be empty.");
            }
        } else if (words[0].equals("deadline")) {
            try {
                String[] remainder = words[1].split(" /by ");
                task = new Deadline(remainder[0], remainder[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("Command does not follow this format: deadline {description} by {end_datetime}");
            } catch (DateTimeParseException e) {
                throw new FelixException("datetime for deadline is not in the format \"yyyy-MM-dd HHmm\"");
            }
        } else {
            try {
                String[] remainder = words[1].split(" /from | /to ");
                task = new Event(remainder[0], remainder[1], remainder[2]);
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("Command does not follow this format: event {description} /from {start_datetime} /to {end_datetime}");
            } catch (DateTimeParseException e) {
                throw new FelixException("datetime not in the format \"yyyy-MM-dd HHmm\"");
            }
        }
        return task;
    }
}
