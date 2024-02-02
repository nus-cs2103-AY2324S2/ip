package Parser;

import Actions.*;
import ExceptionHandling.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    //pass in the chat bot and read command function and return an action

    public static Action parseCommand(String command) throws DukeException{
        String[] split_command = command.split(" ", 2);
        String method = split_command[0];
            switch (method) {
                case "list":
                    return new DisplayList();
                case "bye":
                    return new Bye();

                case "mark":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please include a task index to mark");
                    }
                    String s = split_command[1];
                    return new Mark(Integer.parseInt(s));

                case "unmark":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please include a task index to mark");
                    }
                    String s2 = split_command[1];
                    return new Unmark(Integer.parseInt(s2));

                case "todo":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description for your task!");
                    }
                    return new CreateTodo(split_command[1]);

                case "deadline":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description and a deadline for your task!");
                    }
                    String[] info_split = split_command[1].split("/by ", 2);
                    if (info_split.length <= 1) {
                        throw new DukeException("Please include a deadline by using by keyword like '/by Thursday'");
                    }
                    String deadline_desc = info_split[0];

                    try {
                        LocalDate date = parseDate(info_split[1]);
                        return new CreateDeadline(deadline_desc, date);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Date is in wrong format! Follow yyyy-MM-dd format");
                    }

                case "event":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description and the time period for your task!");
                    }
                    String[] info_split2 = split_command[1].split("/from ", 2);
                    if (info_split2.length <= 1) {
                        throw new DukeException("Please include a time period by using from and to keyword such as" +
                                "'/from today /to tomorrow");
                    }
                    String[] info_split3 = info_split2[1].split("/to ", 2);
                    if (info_split3.length <= 1) {
                        throw new DukeException("Please include a time period by using from and to keyword such as" +
                                "'/from today /to tomorrow");
                    }
                    String eventDesc = info_split2[0];
                    String from = info_split3[0];
                    String to = info_split3[1];
                    return new CreateEvent(eventDesc, from, to);

                case "delete":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please include an index to delete");
                    }
                    String s3 = split_command[1];
                    return new Delete(Integer.parseInt(s3));
                default:
                    return new InvalidAction();
            }
    }

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, f);
    }
}
