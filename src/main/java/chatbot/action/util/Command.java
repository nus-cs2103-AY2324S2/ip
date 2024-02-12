package chatbot.action.util;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.MissingArgumentException;
import chatbot.action.exception.UnrecognizedArgumentException;

/**
 * This defines the {@link Argument}(s) of a {@link Command}.
 *
 * @author Titus Chew
 */
public final class Command {
    /** Stores the usage hint for this. */
    private final String usageHint;

    /** Stores this command name. */
    private final String name;

    /** Stores the {@link ExpectedArgument}(s) of this. */
    private final ExpectedArgument[] arguments;

    /**
     * Constructor for this command, which parses the expected {@link Argument}(s).
     * <li>The first {@link Argument} has the name of the command.
     * <li>If the value is null, there is no value for that {@link Argument}. Otherwise, the value is required.
     *
     * @param arguments The {@link Argument}(s) of this command.
     */
    public Command(ExpectedArgument... arguments) {
        // Name of the argument is the first argument
        this.name = arguments[0].getName();
        this.arguments = arguments;
        this.usageHint = generateUsageHint(arguments);
    }

    /**
     * Generates the usage string of this command using the {@link ExpectedArgument}(s).
     * <p>
     * The usage hint is generated when the command is constructed,
     * and stored in this command.
     *
     * @param arguments The {@link ExpectedArgument}(s) of this command.
     * @return The usage hint.
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
        return usageString.toString().trim();
    }

    /**
     * Checks if the command expects an {@link Argument} name.
     *
     * @param otherArgument The {@link Argument} with the name to find.
     * @return True if the command has that {@link Argument} name, otherwise false.
     */
    public boolean hasArgumentName(Argument otherArgument) {
        for (Argument arg : arguments) {
            if (arg.hasSameArgumentName(otherArgument)) {
                return true;
            }
        }
        return false;
    }

    public String getUsageHint() {
        return usageHint;
    }

    public String getName() {
        return name;
    }

    /**
     * Validates the supplied {@link Argument} names and values.
     *
     * @param suppliedArguments The {@link Argument} names.
     * @throws ActionException If an {@link Argument} is missing or invalid, or unrecognized.
     */
    public void validateSuppliedArguments(Argument[] suppliedArguments) throws ActionException {
        validateArgumentsRecognized(suppliedArguments);
        validateArgumentsPresentAndValid(suppliedArguments);
    }

    /**
     * Validates the {@link Argument} names, that they are recognizable.
     *
     * @throws UnrecognizedArgumentException If an {@link Argument} is unrecognizable.
     */
    private void validateArgumentsRecognized(Argument[] suppliedArguments) throws UnrecognizedArgumentException {
        for (Argument suppliedArg : suppliedArguments) {
            if (!hasArgumentName(suppliedArg)) {
                throw new UnrecognizedArgumentException(this, suppliedArg);
            }
        }
    }

    /**
     * Validates that all {@link Argument} names expected are present and not invalid.
     *
     * @throws ActionException If an {@link Argument} is missing or invalid.
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
