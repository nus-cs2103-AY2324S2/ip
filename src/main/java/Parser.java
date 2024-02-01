import commands.*;
import exception.*;
import objects.TaskList;
import view.EncaseLines;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static utils.InputUtil.getCommandType;
import static utils.InputUtil.getDetails;

public class Parser {
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String HELP = "help";

    public static void parse(String input, TaskList tasks) {
        try {
            input = input.trim().toLowerCase();
            String commandType = getCommandType(input);
            Command command = null;

            switch (commandType) {
                case LIST:
                    command = new ListTasks(tasks);
                    break;

                case MARK:
                    command = new MarkTask(tasks, parseIndex(input));
                    break;

                case UNMARK:
                    command = new UnmarkTask(tasks, parseIndex(input));
                    break;

                case DELETE:
                    command = new DeleteTask(tasks, parseIndex(input));
                    break;


                case TODO:
                    command = new CreateTodo(tasks, parseTodo(input));
                    break;

                case DEADLINE:
                    command = new CreateDeadline(tasks, parseDeadline(input));
                    break;

                case EVENT:
                    command = new CreateEvent(tasks, parseEvent(input));
                    break;

                case HELP:
                    command = new Help();
                    break;

                default:
                    throw new InvalidCommandException();
            }

            command.execute();

        } catch (DukeException e) {
            EncaseLines.display(e.getMessage());

        }
    }

    public static int parseIndex(String input) throws InvalidIndexException {
        String[] parts = input.split("\\s+");

        if (parts.length >= 2) {
            return Integer.parseInt(parts[1]) - 1;
        } else {
            throw new InvalidIndexException();
        }
    }

    public static String parseTodo(String input) throws InvalidTodoException, InvalidCommandException {
        return getDetails(input);
    }

    public static String[] parseDeadline(String input) throws InvalidDeadlineException, InvalidDateException {
        String[] parts = input.trim().split("\\s+");

        if (parts.length < 4 || !input.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        parts = input.split("/by", 2);
        String name = parts[0].trim().split("\\s+", 2)[1];
        String date = parseDate(parts[1].trim());

        return new String[]{name, date};
    }

    public static String[] parseEvent(String input) throws InvalidEventException, InvalidDateException {
        String[] parts = input.trim().split("\\s+");

        if (parts.length < 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }

        parts = input.split("/from", 2);
        String name = parts[0].trim().split("\\s+", 2)[1];
        String[] dates = parts[1].split("/to");

        String fromDate = parseDate(dates[0].trim());
        String toDate = parseDate(dates[1].trim());

        return new String[]{name, fromDate, toDate};
    }

    public static String parseDate(String inputDate) throws InvalidDateException {
        DateFormat sdf = new SimpleDateFormat("d/M/yyyy HHmm");
        sdf.setLenient(false);

        try {
            sdf.parse(inputDate);

        } catch (ParseException e) {
            throw new InvalidDateException();
        }
        return inputDate;
    }
}
