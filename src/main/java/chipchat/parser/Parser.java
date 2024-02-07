package chipchat.parser;

import chipchat.action.*;
import chipchat.exception.ArgumentException;
import chipchat.exception.InvalidArgumentException;
import chipchat.exception.MissingArgumentException;
import chipchat.task.Deadline;
import chipchat.task.Event;
import chipchat.task.Task;
import chipchat.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public static Action parseAction(String userInput) throws ArgumentException {
        String[] tokens = userInput.trim().split(" ");
        if (tokens.length == 0) {
            throw new MissingArgumentException("Error: Please enter something");
        }

        CommandType command = parseCommand(tokens);
        switch(command) {
        case BYE:
            return new Bye();
        case LIST:
            return new ListTasks();
        case DELETE:
        case MARK:
        case UNMARK:
            return parseEditAction(command, tokens);
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseTask(command, tokens);
        }
        return null;
    }

    private static Action parseEditAction(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException("Error: Missing Index\nPlease enter the index (number) of the task to edit");
        }

        int index = parseIndex(tokens);
        switch(command) {
        case DELETE:
            return new Delete(index);
        case MARK:
            return new Mark(index);
        case UNMARK:
            return new Unmark(index);
        }
        return null;
    }

    private static CommandType parseCommand(String[] tokens) throws InvalidArgumentException {
        try {
            return CommandType.valueOf(tokens[0].toUpperCase());
        } catch (IllegalArgumentException exc) {
            throw new InvalidArgumentException("Error: Sorry. I don't know what that means");
        }
    }

    private static int parseIndex(String[] tokens) throws InvalidArgumentException {
        try {
            return Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exc) {
            throw new InvalidArgumentException("Error: Please enter the index (number) of the task you would like to edit");
        }
    }

    private static Action parseTask(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException("Error: Missing description\nPlease enter the description of the task to add");
        } else {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        }

        ArrayList<String> args = new ArrayList<>();
        StringBuilder currArg = new StringBuilder();
        for (String token : tokens) {
            if (token.startsWith("/")) {
                args.add(currArg.toString().trim());
                currArg.setLength(0);
            } else {
                currArg.append(token).append(" ");
            }
        }
        // Add remaining currArg to args list
        args.add(currArg.toString().trim());

        String description = args.get(0);
        switch(command) {
        case TODO:
            return AddTask.addTodo(description, false);
        case DEADLINE:
            LocalDate dueBy = parseDate(args.get(1));
            return AddTask.addDeadline(description, false, dueBy);
        case EVENT:
            LocalDate dateFrom = parseDate(args.get(1));
            LocalDate dateTo = parseDate(args.get(2));
            return AddTask.addEvent(description, false, dateFrom, dateTo);
        }
        return null;
    }

    public static Task parseLoadedTask(String line) {
        String taskStr = line.split("[.] \\[", 2)[1];

        String[] tokensTaskType = taskStr.split("\\]\\[", 2);
        String taskChar = tokensTaskType[0];
        CommandType taskType = CommandType.matchTaskType(taskChar);

        String[] tokensIsDone = tokensTaskType[1].split("\\] ", 2);
        boolean isDone = tokensIsDone[0].equals("X");

        String description;
        String[] tokensDate;
        switch (taskType) {
        case TODO:
            description = tokensIsDone[1].trim();
            return new Todo(description, isDone);
        case DEADLINE:
            tokensDate = tokensIsDone[1].split("\\(by:", 2);
            description = tokensDate[0].trim();
            LocalDate dueBy = parseDate(tokensDate[1].replaceAll("[\\)]", " ").trim());
            return new Deadline(description, isDone, dueBy);
        case EVENT:
            tokensDate = tokensIsDone[1].split("\\(from:", 2);
            description = tokensDate[0].trim();
            String[] tokensEvent = tokensDate[1].split("to:");
            LocalDate dateFrom = parseDate(tokensEvent[0].trim());
            LocalDate dateTo = parseDate(tokensEvent[1].replaceAll("[\\)]", " ").trim());
            return new Event(description, isDone, dateFrom, dateTo);
        default:
            return null;
        }
    }

    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(date, formatter);
    }
}
