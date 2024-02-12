package tyler.parser;
import tyler.command.*;
import tyler.exception.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String line) throws TylerException {
        String[] input = line.split(" ");
        String command = input[0];
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("mark")) {
            if (input.length < 2) {
                throw new InvalidTaskException();
            }
            int index = Integer.parseInt(input[1]);
            return new MarkCommand(index);
        } else if (command.equals("unmark")) {
            if (input.length < 2) {
                throw new InvalidTaskException();
            }
            int index = Integer.parseInt(input[1]);
            return new UnmarkCommand(index);
        } else if (command.equals("todo")) {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String name = line.split(" ", 2)[1];
            return new TodoCommand(name);
        } else if (command.equals("deadline")) {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String[] info = line.split(" ", 2)[1].split("/by ");
            String name = info[0];
            LocalDateTime end = LocalDateTime.parse(info[1], INPUT_DATE_FORMAT);
            return new DeadlineCommand(name, end);
        } else if (command.equals("event")) {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String[] info = line.split(" ", 2)[1].split("/from ");
            String name = info[0];
            String[] startToEnd = info[1].split(" /to ");
            LocalDateTime start = LocalDateTime.parse(startToEnd[0], INPUT_DATE_FORMAT);
            LocalDateTime end = LocalDateTime.parse(startToEnd[1], INPUT_DATE_FORMAT);
            return new EventCommand(name, start, end);
        } else if (command.equals("delete")) {
            if (input.length < 2) {
                throw new InvalidTaskException();
            }
            int index = Integer.parseInt(input[1]);
            return new DeleteCommand(index);
        } else if (command.equals("find")) {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String name = line.split(" ", 2)[1];
            return new FindCommand(name);
        } else {
            throw new UndefinedActionException();
        }
    }
}
