package chatbot.action.util;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.MissingArgumentException;
import chatbot.action.exception.UnrecognizedArgumentException;

import java.lang.StringBuilder;

/**
 * This defines the {@link Argument}(s) of a {@link Command}.
 *
 * @author Titus Chew
 */
public class Command {
    /**
     * Stores the usage hint for this command.
     */
    private final String usageHint;

    /**
     * Stores this command name.
     */
    private final String name;

    /**
     * Stores the expected arguments of this command.
     */
    private final ExpectedArgument[] arguments;

    /**
     * Constructor for this command, which parses the expected arguments.
     * <ul>
     * <li>The first argument has the name of the command.
     * <li>If the value is null, there is no value for that argument. Otherwise, the value is required.
     *
     * @param arguments the arguments of this command
     */
    public Command(ExpectedArgument... arguments) {
        // Name of the argument is the first argument
        this.name = arguments[0].getName();
        this.arguments = arguments;
        this.usageHint = generateUsageHint(arguments);
    }

    /**
     * Generates the usage string of this command using the arguments.
     *
     * @param arguments the expected arguments of this command
     * @return the usage hint
     */
    private String generateUsageHint(ExpectedArgument[] arguments) {
        StringBuilder usageString = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            if (i != 0) {
                usageString.append("/");
            }
            usageString
                    .append(arguments[i].getName())
                    .append(" ");

            if (arguments[i].getValue() != null) {
                // not null indicates that a value should be present.
                usageString
                        .append("<")
                        .append(arguments[i].getValue())
                        .append("> ");
            }
        }
        usageString.deleteCharAt(usageString.length() - 1);
        return usageString.toString();
    }

    /**
     * Checks if the command expects an argument name.
     *
     * @param otherArgument the argument with the name to find
     * @return true if the command has that argument name, otherwise false
     */
    public boolean hasArgumentName(Argument otherArgument) {
        for (Argument arg : arguments) {
            if (arg.hasSameArgumentName(otherArgument)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the command usage hint.
     *
     * @return the usage hint
     */
    public String getUsageHint() {
        return usageHint;
    }

    /**
     * Gets the name of the command.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Validates the supplied argument names and values.
     *
     * @param suppliedArguments the argument names
     * @throws ActionException If an argument is missing or invalid, or unrecognized.
     */
    public void validateSuppliedArguments(Argument[] suppliedArguments) throws ActionException {
        validateArgumentsRecognized(suppliedArguments);
        validateArgumentsPresentAndValid(suppliedArguments);
    }

    /**
     * Validates the argument names, that they are recognizable.
     *
     * @throws UnrecognizedArgumentException If an argument is unrecognizable.
     */
    private void validateArgumentsRecognized(Argument[] suppliedArguments) throws UnrecognizedArgumentException {
        for (Argument suppliedArg : suppliedArguments) {
            if (!hasArgumentName(suppliedArg)) {
                throw new UnrecognizedArgumentException(this, suppliedArg);
            }
        }
    }

    /**
     * Validates that all argument names expected are present and not invalid.
     *
     * @throws ActionException If an argument is missing or invalid.
     */
    private void validateArgumentsPresentAndValid(Argument[] suppliedArguments) throws ActionException {
        for (ExpectedArgument expectedArg : this.arguments) {
            boolean isRecognized = false;
            for (Argument suppliedArg : suppliedArguments) {
                if (expectedArg.hasSameArgumentName(suppliedArg)) {
                    expectedArg.validateArgument(this, suppliedArg);
                    isRecognized = true;
                    break;
                }
            }

            if (!isRecognized) {
                throw new MissingArgumentException(this, expectedArg);
            }
        }
    }
}