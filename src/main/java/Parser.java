import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static ParsedInput parse(String input) throws DukeException, IOException {
        StringTokenizer st = new StringTokenizer(input,  " ");
        int numTokens = st.countTokens();
        if (numTokens == 0) {
            return new ParsedInput(CommandType.EMPTY, null, null);
        }
        String commandString = st.nextToken();
        CommandType command = CommandType.fromString(commandString);
        if (command == CommandType.INVALID || command == CommandType.EMPTY) {
            return new ParsedInput(command, null, null);
        }
        HashMap<String, String> namedArguments = new HashMap<>();
        ArrayList<String> positionalArguments = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.startsWith("/")) {
                if (!st.hasMoreTokens()) {
                    throw new DukeException("Missing value for argument " + token);
                }
                namedArguments.put(token.substring(1), st.nextToken());
            } else {
                positionalArguments.add(token);
            }
        }
        return new ParsedInput(command, namedArguments, positionalArguments);
    }

    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DATE_TIME_FORMAT);
    }

    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    public static Task parseCsvLine(String csvLine) throws DukeException {
        String[] data = csvLine.split(",");
        try {
            String type = data[0];
            boolean done = data[1].equals("1");
            String description = data[2];
            if (type.equals(Todo.TYPE_SYMBOL)) {
                return new Todo(description, done);
            } else if (type.equals(Deadline.TYPE_SYMBOL)) {
                LocalDateTime due = parseDateTime(data[3]);
                return new Deadline(description, done, due);
            } else if (type.equals(Event.TYPE_SYMBOL)) {
                LocalDateTime from = parseDateTime(data[3]);
                LocalDateTime to = parseDateTime(data[4]);
                return new Event(description, done, from, to);
            } else {
                throw new DukeException("Invalid Type");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Invalid storage format!");
        }
    }
}

