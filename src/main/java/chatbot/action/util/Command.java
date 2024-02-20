package chatbot.action.util;

import java.util.Arrays;
import java.util.Optional;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.InvalidArgumentValueException;
import chatbot.action.exception.MissingArgumentException;
import chatbot.action.exception.UnrecognizedArgumentException;
import chatbot.value.exception.InvalidValueTypeException;

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
        return Arrays
                .stream(arguments)
                .map(Argument::toString)
                .reduce("", (concatenated, element) -> concatenated + " " + element)
                .trim()
                // remove redundant slash at the start
                .substring(1);
    }

    /**
     * Checks if the command expects an {@link Argument} name.
     *
     * @param otherArgument The {@link Argument} with the name to find.
     * @return True if the command has that {@link Argument} name, otherwise false.
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
     * Validates the {@link SuppliedArgument} names and values.
     *
     * @param suppliedArguments The {@link SuppliedArgument} names.
     * @throws ActionException If an {@link SuppliedArgument} is missing or invalid, or unrecognized.
     */
    public void validateSuppliedArguments(SuppliedArgument[] suppliedArguments) throws ActionException {
        validateArgumentsRecognized(suppliedArguments);
        validateArgumentsPresentAndValid(suppliedArguments);
    }

    /**
     * Validates the {@link SuppliedArgument} names, that they are recognizable.
     *
     * @throws UnrecognizedArgumentException If an {@link SuppliedArgument} is unrecognizable.
     */
    private void validateArgumentsRecognized(SuppliedArgument[] suppliedArguments)
            throws UnrecognizedArgumentException {
        Optional<SuppliedArgument> unrecognizedArgument = Arrays
                .stream(suppliedArguments)
                .filter(arg -> !hasArgumentName(arg))
                .findAny();

        if (unrecognizedArgument.isPresent()) {
            throw new UnrecognizedArgumentException(this, unrecognizedArgument.get());
        }
    }

    /**
     * Validates that all {@link SuppliedArgument} names expected are present and not invalid.
     *
     * @throws ActionException If an {@link SuppliedArgument} is missing or invalid.
     */
    private void validateArgumentsPresentAndValid(SuppliedArgument[] suppliedArguments) throws ActionException {
        for (ExpectedArgument expectedArg : this.arguments) {
            Optional<SuppliedArgument> matchingArgument = Arrays
                    .stream(suppliedArguments)
                    .filter(expectedArg::hasSameArgumentName)
                    .findAny();

            // missing argument exception if the argument is expected and mandatory
            if (matchingArgument.isEmpty() && !(expectedArg instanceof OptionalArgument)) {
                throw new MissingArgumentException(this, expectedArg);
            }

            if (matchingArgument.isPresent()) {
                expectedArg.validateArgumentValue(this, matchingArgument.get());
            }
        }
    }

    /**
     * Casts the {@link SuppliedArgument}(s) to their expected type.
     *
     * @throws InvalidArgumentValueException If the value cannot be cast successfully.
     */
    public void castSuppliedArgumentsValues(SuppliedArgument[] suppliedArguments) throws InvalidArgumentValueException {
        for (ExpectedArgument expectedArg : this.arguments) {
            Optional<SuppliedArgument> matchingArgument = Arrays
                    .stream(suppliedArguments)
                    .filter(expectedArg::hasSameArgumentName)
                    .findAny();

            if (matchingArgument.isEmpty()) {
                continue;
            }

            try {
                matchingArgument.get().castValue(expectedArg);
            } catch (InvalidValueTypeException e) {
                throw new InvalidArgumentValueException(
                        this,
                        expectedArg.getValue().toString(),
                        e.getMessage()
                );
            }
        }
    }
}
