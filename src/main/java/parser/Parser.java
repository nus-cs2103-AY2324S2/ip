package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import actions.*;
import exceptionhandling.DukeException;

/**
 * The `Parser` class is responsible for parsing user commands and converting them into executable actions.
 * It provides methods to parse different types of commands and handles various exceptions related to command parsing.
 */
public class Parser {

    /**
     * Parses the user command and returns the corresponding action.
     *
     * @param command The user command to be parsed.
     * @return The corresponding action based on the parsed command.
     * @throws DukeException If there is an error in parsing the command or if the command is invalid.
     */
    public static Action parseCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        String method = splitCommand[0];
        switch (method) {
        case "hi" :
            return new Greet();
        case "list":
            return new DisplayList();
        case "bye":
            return new Bye();

        case "mark":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please include a task index to mark");
            }
            String s = splitCommand[1];
            return new Mark(Integer.parseInt(s));

        case "unmark":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please include a task index to mark");
            }
            String s2 = splitCommand[1];
            return new Unmark(Integer.parseInt(s2));

        case "todo":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please write a description for your task!");
            }
            return new CreateTodo(splitCommand[1]);

        case "deadline":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please write a description and a deadline for your task!");
            }
            String[] infoSplit = splitCommand[1].split("/by ", 2);
            if (infoSplit.length <= 1) {
                throw new DukeException("Please include a deadline by using by keyword like '/by Thursday'");
            }
            String deadlineDesc = infoSplit[0];

            try {
                LocalDate date = parseDate(infoSplit[1]);
                return new CreateDeadline(deadlineDesc, date);
            } catch (DateTimeParseException e) {
                throw new DukeException("Date is in the wrong format! Follow yyyy-MM-dd format");
            }

        case "event":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please write a description and the time period for your task!");
            }
            String[] infoSplit2 = splitCommand[1].split("/from ", 2);
            if (infoSplit2.length <= 1) {
                throw new DukeException("Please include a time period by using from and to keyword such as"
                        + "'/from today /to tomorrow");
            }
            String[] infoSplit3 = infoSplit2[1].split("/to ", 2);
            if (infoSplit3.length <= 1) {
                throw new DukeException("Please include a time period by using from and to keyword such as"
                        + "'/from today /to tomorrow");
            }
            String eventDesc = infoSplit2[0];
            String from = infoSplit3[0];
            String to = infoSplit3[1];
            return new CreateEvent(eventDesc, from, to);

        case "delete":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please include an index to delete");
            }
            String s3 = splitCommand[1];
            return new Delete(Integer.parseInt(s3));

        case "find":
            if (splitCommand.length <= 1) {
                throw new DukeException("Please include search details");
            }
            String search = splitCommand[1];
            return new Find(search);

        default:
            return new InvalidAction();
        }
    }

    /**
     * Parses a date string and returns a LocalDate object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the date string is in the wrong format.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, f);
    }
}
