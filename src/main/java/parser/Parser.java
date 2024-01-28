package parser;

import commands.*;

import java.util.Arrays;

public abstract class Parser {
    public static Command parse(String userInput) {
        String[] inputs = userInput.split(" ");
        String command = inputs[0];
        String message = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        switch (command) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(message);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(message);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(message);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(message);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(message);
        default:
            return new ExitCommand();
        }
    }
}
