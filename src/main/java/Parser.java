import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String[] parseSavedTask(String taskString) {
        return taskString.split(" \\| ");
    }

    public static Command parse(String command) throws ChatBotCommandException {
        if (command.isEmpty()) {
            throw new ChatBotCommandException("Empty command.");
        }
        String[] splitCommandArr = command.split("\\s+", 2);
        String keyword = splitCommandArr[0];
        String parameters;
        try {
            parameters = splitCommandArr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            parameters = "";
        }
        switch (keyword) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(keyword, parameters);
        case "bye":
            return new ExitCommand(keyword, parameters);
        case "list":
            return new ListCommand(keyword, parameters);
        case "mark":
            return new MarkCommand(keyword, parameters);
        }
        return new AddCommand(keyword, parameters);
    }

    public static String[] parseToDo(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("Missing description for ToDo \n" +
                    "try: todo <todo_name>");
        }
        return new String[]{parameters};
    }

    public static String[] parseDeadline(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("There is no description and by for Deadline \n" +
                    "try: deadline <deadline_name> /by <by>");
        }
        String[] parametersArr = parameters.split(" /by ");
        if (parametersArr.length == 1) {
            throw new ChatBotParameterException("Missing description or by for Deadline \n" +
                    "try: deadline <deadline_name> /by <by>");
        }
        return parametersArr;
    }

    public static String[] parseEvent(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("There is no description and from and to for Event \n" +
                    "try: event <event_name> /by <from> /to <to>");
        }
        String[] parametersArr = parameters.split(" /from | /to ");
        if (parametersArr.length < 3) {
            throw new ChatBotParameterException("Missing description and/or from and/or to for Event \n" +
                    "try: event <event_name> /by <from> /to <to>");
        }
        return parametersArr;
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateTime);
        }
    }
}
