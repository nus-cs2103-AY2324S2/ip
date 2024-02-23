package dino.commands;

import dino.DinoException.DinoException;

/**
 * The Parser class processes user input and returns the corresponding command object based on the input.
 */
public class Parser {
    /**
     * The function processes user input and returns the corresponding command object based on the input.
     * 
     * @param input A string representing the user input.
     * @return The method is returning a Command object based on the input provided. The specific type of Command
     * object returned depends on the instruction parsed from the input.
     */
    public static Command processInput(String input) throws DinoException {
        if (input == null || input.trim().isEmpty()) {
            throw new DinoException("Please enter a valid command.");
        }

        String[] parsedInput = input.split(" ", 2);
        Instruction instruction = toInstruction(parsedInput[0]);
        String details = parsedInput.length > 1 ? parsedInput[1] : "";

        return createCommand(instruction, details);
    }

    private static Instruction toInstruction(String input) throws DinoException {
        try {
            return Instruction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DinoException("Invalid command. Valid commands: list, mark, unmark, deadline, event, todo, delete, find");
        }
    }

    private static Command createCommand(Instruction instruction, String details) throws DinoException {
        switch (instruction) {
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
            case FIND:
                return new FindCommand(details);
            default:
                throw new DinoException("Unsupported command.");
        }
    }


    enum Instruction {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND
    }
}
