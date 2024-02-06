package util;

import commands.*;
import exceptions.ChatBotException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Command firstParse(String input) {
        String[] sections = input.split(" ", 2);
        String commandWord = sections[0].toUpperCase();

        switch (commandWord) {
        case "BYE":
            return new ByeCommand();
        case "TODO":
            return parseTodo(input);
        case "MARK":
            return parseMark(input);
        case "UNMARK":
            return parseUnmark(input);
        case "LIST":
            return new ListCommand();
        case "DELETE":
            return parseDelete(input);
        case "DEADLINE":
            return parseDeadline(input);
        case "EVENT":
            return parseEvent(input);
        case "FIND":
            return parseFind(input);
        default:
            return new InvalidCommand("Oops! Please enter a valid command.");
        }
    }

    public Command parseTodo(String input) {
        String[] sections = input.split(" ", 2);
            if (sections.length <= 1) {
                return new InvalidCommand("Oops! You have not entered a task.");
            }
        String taskDescription = sections[1].trim();
        return new TodoCommand(taskDescription);
    }

    public Command parseMark(String input) {
        String[] sections = input.split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task number to mark.");
        }
        int index = Integer.parseInt(sections[1].trim());
        if (index <= 0) {
            return new InvalidCommand("Oops! Task number cannot be zero or negative.");
        }
        return new MarkCommand(index);
    }

    public Command parseUnmark(String input) {
        String[] sections = input.split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task number to mark.");
        }
        int index = Integer.parseInt(sections[1].trim());
        if (index <= 0) {
            return new InvalidCommand("Oops! Task number cannot be zero or negative.");
        }
        return new UnmarkCommand(index);
    }

    public Command parseDelete(String input) {
        String[] sections = input.split(" ", 2);
        if (sections.length <= 1 || sections[1].isEmpty()) {
            return new InvalidCommand("Oops! Please enter a task number to delete.");
        }
        int index = Integer.parseInt(sections[1].trim());
        if (index <= 0) {
            return new InvalidCommand("Oops! Task number cannot be zero or negative.");
        }
        return new DeleteCommand(index);
    }

    public Command parseDeadline(String input) {
        String[] sections = input.split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task description.");
        }
        String taskDescriptionPlusDue = sections[1].trim();
        String[] parseTaskDescriptionPlusDue = taskDescriptionPlusDue.split(" /by ");
        if (parseTaskDescriptionPlusDue.length <= 1) {
            return new InvalidCommand("Oops! Please enter a valid deadline format.");
        }
        String taskDescription = parseTaskDescriptionPlusDue[0].trim();
        LocalDateTime due;
        try {
            due = parseDate(parseTaskDescriptionPlusDue[1].trim());
        } catch (ChatBotException e) {
            return new InvalidCommand("Oops! Please enter a valid date/time format.");
        }
        return new DeadlineCommand(taskDescription, due);
    }

    public Command parseEvent(String input) {
        String[] sections = input.split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task description.");
        }
        String eventPlusStartAndEnd = sections[1].trim();
        String[] parseEventPlusStartAndEnd = eventPlusStartAndEnd.split(" /from | /to ");
        if (parseEventPlusStartAndEnd.length <= 2) {
            return new InvalidCommand("Oops! Please enter a valid event format.");
        }
        String event = parseEventPlusStartAndEnd[0].trim();
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = parseDate(parseEventPlusStartAndEnd[1].trim());
            end = parseDate(parseEventPlusStartAndEnd[2].trim());
        } catch (ChatBotException e) {
            return new InvalidCommand("Oops! Please enter a valid date/time format.");
        }
        return new EventCommand(event, start, end);
    }

    /**
     * Parses the input string to create a command for finding tasks.
     *
     * @param input The user input string.
     * @return A command object for finding tasks based on the input.
     */
    public Command parseFind(String input) {
        String[] sections = input.split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task to find.");
        }
        String tasktoFind = sections[1].trim();
        return new FindCommand(tasktoFind);
    }

    public LocalDateTime parseDate(String dateString) throws ChatBotException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateString, dateFormatter).atStartOfDay();
            } catch (DateTimeParseException e2) {
            }
        }
        throw new ChatBotException("Oops! Invalid date and time format. " +
                "Please enter in yyyy-MM-dd HHmm format, you may leave HHmm empty.");
    }

    public static Task parseTasksFromFile(String tasksFromFile) throws ChatBotException {
        String[] sections = tasksFromFile.split("\\|");
        if (sections.length < 3 || sections.length > 4) {
            throw new ChatBotException("Oops! tasks.Task format is invalid.");
        }
        String taskType = sections[0];
        boolean isDone = sections[1].trim().equals("1");
        String taskDescription = sections[2].trim();
        String dateOrTime = sections.length > 3 ? sections[3].trim() : null;

        switch (taskType) {
        case "T":
            ToDo todo = new ToDo(taskDescription);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (dateOrTime == null) {
                throw new ChatBotException("Oops! tasks.Deadline format is invalid.");
            }
            LocalDateTime due = LocalDateTime.parse(dateOrTime);
            Deadline deadline = new Deadline(taskDescription, due);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            String[] startAndEnd = dateOrTime.split("to");
            if (startAndEnd.length < 2) {
                throw new ChatBotException("Oops! tasks.Event format is invalid.");
            }
            LocalDateTime eventStart = LocalDateTime.parse(startAndEnd[0].trim());
            LocalDateTime eventEnd = LocalDateTime.parse(startAndEnd[1].trim());
            Event event = new Event(taskDescription, eventStart, eventEnd);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new ChatBotException("Oops! Unknown task type");
        }
    }
}


