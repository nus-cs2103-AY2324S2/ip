package chatbot.parse;

import java.util.ArrayList;
import java.util.List;

import chatbot.ChatBot;
import chatbot.action.Action;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * Parses the input of a {@link ChatBot} into {@link Argument} list.
 *
 * @author Titus Chew
 */
public final class InputParser {
    /**
     * Parses the input string into it's {@link Command} and {@link Argument}(s).
     *
     * @param input the input string
     * @return an {@link Action} containing the {@link Command} and it's {@link Argument}(S)
     * @throws ActionException If invalid input is provided that results in
     *     an invalid {@link Command} or {@link Argument}(s).
     */
    public static Action getParsedInput(String input) throws ActionException {
        // command is always the first word in the input
        String command = input.trim().split(" ")[0];

        return Action.of(command, parseArguments(input));
    }

    /**
     * Parses the {@link Argument}(s) from an input string.
     * <li>"/" is a special character, when at the start of a word, it denotes the start of an {@link Argument}.
     * <li>"/" not at the start of a word, will not be recognized as a special character.
     *
     * @param input the console input
     * @return the parsed {@link Argument} list
     */
    private static Argument[] parseArguments(String input) {
        // split input by words (space-separated)
        String[] tokens = input.split(" ");

        List<Argument> arguments = new ArrayList<>();

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

        Argument[] argumentsArray = new Argument[arguments.size()];
        arguments.toArray(argumentsArray);
        return argumentsArray;
    }

    /**
     * Parses an {@link Argument} consisting of the argument name and value.
     *
     * @param argumentName the string representing the {@link Argument} name
     * @param argumentValue the string representing the {@link Argument} value
     * @return the {@link Argument} that is formed
     */
    private static Argument parseArgument(String argumentName, String argumentValue) {
        argumentValue = argumentValue.trim();
        argumentValue = (argumentValue.equals("")) ? null : argumentValue;
        return new Argument(argumentName, argumentValue);
    }
}
