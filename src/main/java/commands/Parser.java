package commands;

import DukeException.DukeException;

public class Parser {
    public static Command processInput(String input) throws DukeException {
        try {
            String[] parsedInput = input.split(" ", 2);
            Parser.Instruction ins = toInstruction(parsedInput[0]);
            String details = parsedInput.length > 1 ? parsedInput[1] : "";

            switch (ins) {
                case LIST:
                    return new ListCommand();
                case MARK:
                    return new MarkCommand(details);
                case UNMARK:
                    return new UnmarkCommand(details);
                case TODO:
                    return new TodoCommand(details);
                case DEADLINE:
                    return new DeadlineCommand(details);
                case EVENT:
                    return new EventCommand(details);
                case DELETE:
                    return new DeleteCommand(details);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Please enter instruction in the correct format"
                    + "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
        return null;
    }

    private static Instruction toInstruction(String input) throws DukeException {
        try {
            return Instruction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Please enter instruction in the correct format"
                    + "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
    }
    enum Instruction {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }
}
