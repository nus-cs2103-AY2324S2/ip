import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    public static Integer stringToInt(String integer) throws DukeException {
        try{
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            throw new DukeException("I'm afraid I've encountered an error while parsing the integer, my dear.");
        }
    }
    public static LocalDateTime stringToDateTime(String dateTime) throws DukeException {
        try{
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("I'm afraid I've encountered an error while parsing the date and time, my dear.");
        }
    }

    public static void checkForEmpty(String[] arguments, int expectedNumber) throws DukeException {
        if (arguments.length != expectedNumber) {
            throw new DukeException("I'm afraid you're missing some arguments, my dear.");
        }
        for (String i : arguments) {
            if (i.isBlank()) {
                throw new DukeException("I'm afraid you're missing some arguments, my dear.");
            }
        }
    }

    public static void parseFile(String line, ArrayList<Task> list, int index) throws DukeException {
        String[] task = line.split("\\| ", 5);
        switch (task[0].trim()) {
            case "T":
                list.add(new ToDo(task[2]));
                break;
            case "D":
                list.add(new Deadline(task[2], stringToDateTime(task[3].trim())));
                break;
            case "E":
                list.add(new Event(task[2], stringToDateTime(task[3].trim()),
                        stringToDateTime(task[4].trim())));
                break;
        }
        if (task[1].equals("1")) {
            list.get(index).mark();
        }
    }

    public static Command parseCommand(String command) throws DukeException {
        String[] task = command.trim().split(" ", 2);
        switch (task[0]) {
            case "list":
                return new ListCommand();
            case "mark":
                checkForEmpty(task, 2);
                return new MarkCommand(stringToInt(task[1]), true);
            case "unmark":
                checkForEmpty(task , 2);
                return new MarkCommand(stringToInt(task[1]), false);
            case "todo":
                checkForEmpty(task, 2);
                return new AddCommand(new ToDo(task[1]));
            case "deadline":
                checkForEmpty(task, 2);
                String[] deadline = task[1].split("/by ", 2);
                checkForEmpty(deadline, 2);
                return new AddCommand(new Deadline(deadline[0].trim(), stringToDateTime(deadline[1].trim())));
            case "event":
                checkForEmpty(task, 2);
                String[] event = task[1].split("/from|/to", 3);
                checkForEmpty(event, 3);
                return new AddCommand(new Event(event[0].trim(), stringToDateTime(event[1].trim()),
                        stringToDateTime(event[2].trim())));
            case "delete":
                checkForEmpty(task, 2);
                return new DeleteCommand(stringToInt(task[1]));
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    }
}
