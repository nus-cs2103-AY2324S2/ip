package chatbot.io;

import chatbot.action.Action;
import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;

import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses the input of a ChatBot into argument list.
 *
 * @author Titus Chew
 */
public final class InputParser {
    /**
     * Stores the scanner instance used to get the console input stream.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Parse the input string into it's command and arguments.
     *
     * @return an action containing the command and it's arguments
     * @throws ActionException If invalid input is provided that results in an invalid command or arguments.
     */
    public static Action getParsedInput() throws ActionException {
        String input = SCANNER.nextLine();

        // command is always the first word in the input
        String command = input.trim().split(" ")[0];

        return Action.of(command, parseArguments(input));
    }

    /**
     * Parse the argument(s) from an input string.
     * <ul>
     * <li>"/" is a special character, when at the start of a word, it denotes the start of an argument.
     * <li>"/" not at the start of a word, will not be recognized as a special character.
     *
     * @param input the console input
     * @return the parsed argument list
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
     * An argument consists of the argument name and value.
     *
     * @param argumentName the string representing the argument name
     * @param argumentValue the string representing the argument value
     * @return the argument that is formed
     */
    private static Argument parseArgument(String argumentName, String argumentValue) {
        argumentValue = argumentValue.trim();
        argumentValue = argumentValue.equals("") ? null : argumentValue;
        return new Argument(argumentName, argumentValue);
    }
}
