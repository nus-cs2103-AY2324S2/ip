package duke;

import duke.command.*;
import duke.exceptions.ChatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static int parseInt(String val) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new ChatException("Task number incorrect format!");
        }
    }

    private static String getContents(String line, int startFrom) {
        String msg = line.substring(startFrom);
        if (msg.equals(" ") || msg.isEmpty()) {
            throw new ChatException("The description of a todo cannot be empty.");
        }
        msg = msg.stripLeading();
        return msg;
    }

    private static LocalDateTime dateParse(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }
    public static Command parse(String line) {
        String command = line.split(" ")[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark": {
            int idx = Parser.parseInt(line.split(" ")[1]);
            return new MarkCommand(idx-1);
        }
        case "unmark": {
            int idx = Parser.parseInt(line.split(" ")[1]);
            return new UnMarkCommand(idx - 1);
        }
        case "delete":
            int idx = Parser.parseInt(line.split(" ")[1]);
            return new DeleteCommand(idx - 1);
        case "todo":
            return new TodoCommand(Parser.getContents(line, 4));
        case "deadline": {
            String[] res = Parser.getContents(line, 8).split(" /by ");
            return new DeadlineCommand(res[0], Parser.dateParse(res[1]));
        }
        case "event": {
            String[] res = Parser.getContents(line, 5).split(" ((/from)|(/to)) ");
            return new EventCommand(res[0], Parser.dateParse(res[1]), Parser.dateParse(res[2]));
        }
        case "":
            return new NothingCommand();
        default:
            throw new ChatException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
