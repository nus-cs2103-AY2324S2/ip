package chatbot.action.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

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
     * @param arguments the {@link Argument}(s) of this command
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
     * @param arguments the {@link ExpectedArgument}(s) of this command
     * @return the usage hint
     */
    private String generateUsageHint(ExpectedArgument[] arguments) {
        // converts an expected argument to a usage hint for that argument
        Function<ExpectedArgument, String> expectedArgumentMapper = arg ->
                arg.getName() + " "
                + Optional.ofNullable(arg.getValue())
                        .map(val -> "<" + val + ">")
                        .orElse("");

        return Arrays
                .stream(arguments)
                .map(expectedArgumentMapper)
                .reduce("", (concatenated, element) -> concatenated + " /" + element)
                .trim()
                // remove redundant slash at the start
                .substring(1);
    }

    /**
     * Checks if the command expects an {@link Argument} name.
     *
     * @param otherArgument the {@link Argument} with the name to find
     * @return true if the command has that {@link Argument} name, otherwise false
     */
    public boolean hasArgumentName(Argument otherArgument) {
        return Arrays
                .stream(arguments)
                .anyMatch(arg -> arg.hasSameArgumentName(otherArgument));
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
     * @param suppliedArguments the {@link Argument} names
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
        Optional<Argument> unrecognizedArgument = Arrays
                .stream(suppliedArguments)
                .filter(arg -> !hasArgumentName(arg))
                .findAny();

        if (unrecognizedArgument.isPresent()) {
            throw new UnrecognizedArgumentException(this, unrecognizedArgument.get());
        }
    }

    /**
     * Validates that all {@link Argument} names expected are present and not invalid.
     *
     * @throws ActionException If an {@link Argument} is missing or invalid.
     */
    private void validateArgumentsPresentAndValid(Argument[] suppliedArguments) throws ActionException {
        for (ExpectedArgument expectedArg : this.arguments) {
            Optional<Argument> matchingArgument = Arrays
                    .stream(suppliedArguments)
                    .filter(expectedArg::hasSameArgumentName)
                    .findAny();

            if (matchingArgument.isEmpty()) {
                throw new MissingArgumentException(this, expectedArg);
            }

            expectedArg.validateArgument(this, matchingArgument.get());
        }
    }
}
