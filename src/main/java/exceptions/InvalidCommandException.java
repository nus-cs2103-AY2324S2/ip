package exceptions;

import type.CommandEnum;

import java.util.Arrays;

public class InvalidCommandException extends InvalidInputException {
    public InvalidCommandException(String invalidCommand) {
        super("Invalid Command: " + invalidCommand + " received.\n" +
                "Only the following commands are accepted:\n" +
                Arrays.toString(CommandEnum.values()));
    }
}