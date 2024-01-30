package duke.parser;

import duke.command.*;
import duke.exception.ChatBotCommandException;
import duke.exception.ChatBotParameterException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Convert a TaskString (i.e. String-formatted Task) from local saves.
     * into array of parameters to create a task.
     * @param taskString String the String parse from a line in local saves.
     * @return String[] Parameter Array.
     */
    public static String[] parseSavedTask(String taskString) {
        return taskString.split(" \\| ");
    }

    /**
     * Convert command String into a Command object.
     * @param command String the command String read from UI.
     * @return Command appropriate Command object to be executed.
     * @throws ChatBotCommandException when command is not available or command String is empty.
     */
    public static Command parseCommand(String command) throws ChatBotCommandException {
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
        case "unmark":
            return new UnmarkCommand(keyword, parameters);
        case "delete":
            return new DeleteCommand(keyword, parameters);
        default:
            throw new ChatBotCommandException("Invalid command.");
        }

    }

    /**
     * Parse parameters String for ToDo.
     * @param parameters String the String containing information to create a ToDo.
     * @return String[] Parameter Array.
     * @throws ChatBotParameterException when parameters String is empty.
     */
    public static String[] parseToDo(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("Missing description for duke.task.ToDo \n" +
                    "try: todo <todo_name>");
        }
        return new String[]{parameters};
    }

    /**
     * Parse parameters String for Deadline.
     * @param parameters String the String containing information to create a Deadline.
     * @return String[] Parameter Array.
     * @throws ChatBotParameterException when parameters String is empty or length 1.
     */
    public static String[] parseDeadline(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("There is no description and by for duke.task.Deadline \n" +
                    "try: deadline <deadline_name> /by <by>");
        }
        String[] parametersArr = parameters.split(" /by ");
        if (parametersArr.length == 1) {
            throw new ChatBotParameterException("Missing description or by for duke.task.Deadline \n" +
                    "try: deadline <deadline_name> /by <by>");
        }
        return parametersArr;
    }

    /**
     * Parse parameters String for Event.
     * @param parameters String the String containing information to create an Event.
     * @return String[] Parameter Array.
     * @throws ChatBotParameterException when parameters String is empty or length < 3.
     */
    public static String[] parseEvent(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("There is no description and from and to for duke.task.Event \n" +
                    "try: event <event_name> /by <from> /to <to>");
        }
        String[] parametersArr = parameters.split(" /from | /to ");
        if (parametersArr.length < 3) {
            throw new ChatBotParameterException("Missing description and/or from and/or to for duke.task.Event \n" +
                    "try: event <event_name> /by <from> /to <to>");
        }
        return parametersArr;
    }

    /**
     * Parse DateTime String (with pattern of ISO 8601 or "d/M/yyyy Hmm") into LocalDateTime object.
     * @param dateTime String the DateTime String.
     * @return LocalDateTime LocalDateTime object.
     * @throws ChatBotParameterException String is in wrong format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ChatBotParameterException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateTime);
            } catch (DateTimeParseException e1) {
                throw new ChatBotParameterException("Invalid Date Time format, try d/M/yyy Hmm");
            }
        }
    }

    /**
     * Convert String parameter into int for certain commands.
     * @param parameters String String parameter.
     * @return int value from the String.
     * @throws ChatBotParameterException when parameters is empty or cannot be converted into int.
     */
    public static int parseInteger(String parameters) throws ChatBotParameterException {
        if (parameters.isEmpty()) {
            throw new ChatBotParameterException("Missing task number \n" +
                    "try: mark/unmark/delete <task_number>");
        }
        try {
           return Integer.parseInt(parameters);
        } catch (NumberFormatException e) {
            throw new ChatBotParameterException("Invalid task number \n" +
                    "try: mark/unmark/delete <task_number>");
        }
    }
}
