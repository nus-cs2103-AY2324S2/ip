package chipchat.parser;

import chipchat.action.*;
import chipchat.task.Deadline;
import chipchat.task.Event;
import chipchat.task.Task;
import chipchat.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

public class Parser {
    public static Action parseUserInput(String userInput) {
        String[] args = userInput.trim().split(" ", 2);
        CommandType command = CommandType.valueOf(args[0].toUpperCase());

        int index = 0;
        switch (command) {
            case BYE:
                return new Bye();
            case LIST:
                return new ListTasks();
            case DELETE:
                index = Integer.parseInt(args[1].trim());
                return new Delete(index);
            case MARK:
                index = Integer.parseInt(args[1].trim());
                return new Mark(index);
            case UNMARK:
                index = Integer.parseInt(args[1].trim());
                return new Unmark(index);
            case TODO: case DEADLINE: case EVENT:
                return parseTask(command, args[1]);
            default:
                break;
        }
        return null;
    }

    private static Action parseTask(CommandType command, String userInput) {
        String[] args;
        String description;
        switch(command) {
            case TODO:
                description = userInput.trim();
                return AddTask.addTodo(description, false);
            case DEADLINE:
                args = userInput.split("/by", 2);
                description = args[0].trim();
                LocalDate dueBy = parseDate(args[1].trim());
                return AddTask.addDeadline(description, false, dueBy);
            case EVENT:
                args = userInput.split("/from");
                description = args[0].trim();
                String[] dateArgs = args[1].split("/to");
                LocalDate dateFrom = parseDate(dateArgs[0].trim());
                LocalDate dateTo = parseDate(dateArgs[1].trim());
                return AddTask.addEvent(description, false, dateFrom, dateTo);
            default:
                return null;
        }
    }

    public static Task parseLoadedTask(String line) {
        String taskStr = line.split("[.] \\[", 2)[1];

        String[] argsTaskType = taskStr.split("\\]\\[", 2);
        String taskChar = argsTaskType[0];
        CommandType taskType = CommandType.matchTaskType(taskChar);

        String[] argsIsDone = argsTaskType[1].split("\\] ", 2);
        boolean isDone = argsIsDone[0].equals("X");

        String description;
        String[] argsDate;
        switch (taskType) {
            case TODO:
                description = argsIsDone[1].trim();
                return new Todo(description, isDone);
            case DEADLINE:
                argsDate = argsIsDone[1].split("\\(by:", 2);
                description = argsDate[0].trim();
                LocalDate dueBy = parseDate(argsDate[1].replaceAll("[\\)]", " ").trim());
                return new Deadline(description, isDone, dueBy);
            case EVENT:
                argsDate = argsIsDone[1].split("\\(from:", 2);
                description = argsDate[0].trim();
                String[] argsEvent = argsDate[1].split("to:");
                LocalDate dateFrom = parseDate(argsEvent[0].trim());
                LocalDate dateTo = parseDate(argsEvent[1].replaceAll("[\\)]", " ").trim());
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
