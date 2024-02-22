package chatbot.action.util;

import java.lang.reflect.InvocationTargetException;

import chatbot.value.exception.InvalidValueTypeException;

/**
 * Represents the arguments generated from the command input,
 * which are then supplied to the action,
 * to perform the execution.
 *
 * @author Titus Chew
 */
public class SuppliedArgument extends Argument {
    /**
     * Constructor for this {@link Argument} without a value.
     *
     * @param name The name of this argument, which should not be null.
     */
    public SuppliedArgument(String name) {
        super(name);
    }

    /**
     * Constructor for this {@link Argument} with a value and name.
     *
     * @param name The name of this argument, which should not be null.
     * @param value The value of this argument, which can be null.
     */
    public SuppliedArgument(String name, String value) {
        super(name, value);
    }

    /**
     * Casts the stored argument value to the expected type.
     *
     * @throws InvalidValueTypeException If the value cannot be cast successfully.
     */
    public void castValue(ExpectedArgument expectedArgument) throws InvalidValueTypeException {
        // no need to cast if value doesn't exist
        if (getValue() == null) {
            return;
        }

        // cast the value
        try {
            // all StringValue and subclasses constructors should take in a String.
            setValue(expectedArgument
                    .getExpectedType()
                    .getConstructor(String.class)
                    .newInstance(getValue().toString()));
        } catch (NoSuchMethodException e) {
            throw new AssertionError("StringValue and subclasses constructors should exist!");
        } catch (SecurityException e) {
            throw new AssertionError("All JAR files should be properly signed with the same certificate!");
        } catch (IllegalAccessException e) {
            throw new AssertionError("StringValue and subclasses constructors should be accessible!");
        } catch (IllegalArgumentException e) {
            throw new AssertionError("StringValue and subclasses constructors "
                    + "should have a valid argument passed into them!");
        } catch (InstantiationException e) {
            throw new AssertionError("StringValue and subclasses should be able to be instantiated!");
        } catch (InvocationTargetException e) {
            // the only exception that can be thrown is InvalidValueTypeException
            throw (InvalidValueTypeException) e.getCause();
        }
    }
}
