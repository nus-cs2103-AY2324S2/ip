package ui;

import exceptions.DukeException;

public class Parser {

    public enum Command {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE,
        LIST,
        FIND,
        UNKNOWN
    }
    public Command parse(String userInput) throws DukeException {
        String[] splitInput = userInput.split(" ", 2);
        String commandWord = splitInput[0];


        switch (commandWord) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "delete":
                return Command.DELETE;
            case "find":
                return Command.FIND;
            default:
                return Command.UNKNOWN;
        }
    }

}
