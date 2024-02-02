package commands;

import commands.Command;
import exceptions.DukeException;

public class Parser {

    public static Command parse(String message) throws DukeException {
        if (message.equals("bye")) {
            return Command.BYE;
        } else if (message.equals("yap")) {
            return Command.YAP;
        } else if (message.startsWith("mark ")) {
            return Command.MARK;
        } else if (message.startsWith("unmark ")) {
            return Command.UNMARK;
        } else if (message.startsWith("todo")) {
            return Command.ADD_TODO;
        } else if (message.startsWith("deadline")) {
            return Command.ADD_DEADLINE;
        } else if (message.startsWith("event")) {
            return Command.ADD_EVENT;
        } else if (message.startsWith("delete")) {
            return Command.DELETE;
        } else if (message.startsWith("find")) {
            return Command.FIND;
        } else {
            throw new DukeException("What's YAPpening??!! Please yap your instruction more clearly");
        }
    }

}
