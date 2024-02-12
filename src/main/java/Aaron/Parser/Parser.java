package aaron.parser;

import aaron.command.CommandType;
import aaron.exception.InvalidCommandFormatException;
import aaron.exception.NonsenseCommandException;
import aaron.exception.ParsingException;
import aaron.executer.Executer;
import aaron.task.TaskList;
import aaron.ui.UI;

public class Parser {
    public static boolean parse(String userInput, TaskList taskList, UI ui) throws ParsingException {
        CommandType commandType;
        String[] TokenizedUserInputs;
        try {
            commandLengthCheck(userInput);
        } catch (InvalidCommandFormatException e) {
            throw new ParsingException("Invalid command format: " + userInput);
        }

        try {
            TokenizedUserInputs = userInput.split(" ", 2);
            commandType = getCommandType(TokenizedUserInputs[0]);
        } catch (NonsenseCommandException e) {
            throw new ParsingException("Invalid command type: " + userInput);
        }
        return Executer.execute(commandType, userInput, taskList, ui);
    }

    /**
     * function that checks if the user input is >1 words (unless a 'bye' command is
     * given)
     * 
     * @param userInputString User input
     * @throws InvalidCommandFormatException if user input is <=1 words unless the
     *                                       input is 'bye'
     */
    private static void commandLengthCheck(String userInputString) throws InvalidCommandFormatException {
        if (!(userInputString.equals("bye")) && userInputString.split("\\s+").length <= 1) {
            throw new InvalidCommandFormatException(userInputString);
        }
    }

    private static CommandType getCommandType(String commandType) throws NonsenseCommandException {
        return CommandType.getCommandType(commandType);
    }
}
