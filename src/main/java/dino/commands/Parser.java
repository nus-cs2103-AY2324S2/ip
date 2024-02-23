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
        try {
            assert input != null && !input.isEmpty() : "Input string must not be null or empty";
            String[] parsedInput = input.split(" ", 2);
            Instruction ins = toInstruction(parsedInput[0]);
            assert ins != null : "Instruction enum must not be null";
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
                case FIND:
                    return new FindCommand(details);
            }
        } catch (IllegalArgumentException e) {
            throw new DinoException("Please enter instruction in the correct format"
                    + "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
        return null;
    }

    /**
     * The function converts a string input into an Instruction enum value, throwing a DinoException if the input is
     * not a valid instruction.
     * 
     * @param input A string representing the user input for an instruction.
     * @return The method is returning an Instruction
     */
    private static Instruction toInstruction(String input) throws DinoException {
        try {
            return Instruction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DinoException("Please enter instruction in the correct format"
                    + "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
    }


    enum Instruction {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND
    }
}
