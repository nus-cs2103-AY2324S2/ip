package chatbot.parse;

import java.util.ArrayList;
import java.util.List;

import chatbot.ChatBot;
import chatbot.action.Action;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.SuppliedArgument;

/**
 * Parses the input of a {@link ChatBot} into {@link Argument} list.
 *
 * @author Titus Chew
 */
public final class InputParser {
    /**
     * Parses the input line into it's {@link Command} and {@link Argument}(s).
     *
     * @return An {@link Action} containing the {@link Command} and it's {@link Argument}(s).
     * @throws ActionException If invalid input is provided that results in
     *     an invalid {@link Command} or {@link Argument}(s).
     */
    public static Action getParsedInput(String commandLineInput) throws ActionException {
        // command is always the first word in the input
        String command = commandLineInput.trim().split(" ")[0];

        return Action.of(command, parseArguments(commandLineInput));
    }

    /**
     * Parses the {@link Argument}(s) from an input line.
     * <li>"/" is a special character, when at the start of a word, it denotes the start of an {@link Argument}.
     * <li>"/" not at the start of a word, will not be recognized as a special character.
     *
     * @return The parsed {@link SuppliedArgument} list.
     */
    private static SuppliedArgument[] parseArguments(String commandLineInput) {
        // split input by words (space-separated)
        String[] tokens = commandLineInput.split(" ");

        List<SuppliedArgument> arguments = new ArrayList<>();

        // scan through each word, check if it is an argument
        // first word is always argument name
        String argName = tokens[0];
        StringBuilder argValue = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                arguments.add(parseArgument(argName, argValue.toString()));

                // start parsing next argument
                argName = tokens[i].substring(1);
                argValue.delete(0, argValue.length());
            } else {
                argValue
                        .append(tokens[i])
                        .append(" ");
            }
        }
        arguments.add(parseArgument(argName, argValue.toString()));
        return arguments.toArray(SuppliedArgument[]::new);
    }

    /**
     * Parses an {@link Argument} consisting of the argument name and value.
     *
     * @param argumentName The string representing the {@link Argument} name.
     * @param argumentValue The string representing the {@link Argument} value.
     * @return The {@link Argument} for the action, that is formed.
     */
    private static SuppliedArgument parseArgument(String argumentName, String argumentValue) {
        String parsedArgumentValue = argumentValue.trim();
        parsedArgumentValue = (parsedArgumentValue.equals("")) ? null : parsedArgumentValue;
        return new SuppliedArgument(argumentName, parsedArgumentValue);
    }
}
