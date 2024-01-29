package cappy.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import cappy.command.CommandType;
import cappy.error.CappyException;
import cappy.task.Task;
import cappy.task.Todo;
import cappy.task.Deadline;
import cappy.task.Event;

public class Parser {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static ParsedInput parse(String input) throws CappyException {
        StringTokenizer st = new StringTokenizer(input,  " ");
        int numTokens = st.countTokens();
        if (numTokens == 0) {
            return new ParsedInput(CommandType.EMPTY, new HashMap<>(), new ArrayList<>());
        }
        String commandString = st.nextToken();
        CommandType command = CommandType.fromString(commandString);
        if (command == CommandType.INVALID || command == CommandType.EMPTY) {
            return new ParsedInput(command, new HashMap<>(), new ArrayList<>());
        }
        HashMap<String, String> namedArguments = new HashMap<>();
        ArrayList<String> positionalArguments = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.startsWith("/")) {
                if (!st.hasMoreTokens()) {
                    throw new CappyException("Missing value for argument " + token);
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

    public static Task parseCsvLine(String csvLine) throws CappyException {
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
                throw new CappyException("Invalid Type");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new CappyException("Invalid storage format!");
        }
    }
}

