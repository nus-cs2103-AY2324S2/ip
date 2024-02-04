package cappy.parser;

import cappy.command.Command;
import cappy.command.CommandType;
import cappy.error.CappyException;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Represents the parsed input from the user command.
 *
 * <p>The {@code ParsedInput} class encapsulates the parsed information from the user's input,
 * including the command type, named arguments, and positional arguments. It provides methods to
 * retrieve and interact with the parsed information.
 */
public class ParsedInput {
    private CommandType commandType;
    private Command command;
    private Map<String, String> namedArguments;
    private List<String> positionalArguments;

    public ParsedInput(
            CommandType commandType,
            Map<String, String> namedArguments,
            List<String> positionalArguments) {
        this.commandType = commandType;
        this.command = commandType.getCommand();
        this.namedArguments = namedArguments;
        this.positionalArguments = positionalArguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Returns the corresponding value for the named argument. Returns null if the argument does not
     * exist.
     *
     * @param name The name of the argument.
     * @return The corresponding value of the argument.
     */
    public String getNamedArgument(String name) {
        return namedArguments.get(name);
    }

    /**
     * Returns the value of the positional argument at the specified index. Throws {@code
     * IndexOutOfBoundsException} if the specified index is not a valid index.
     *
     * @param index The index of the positional argument to retrieve.
     * @return The value of the positional argument at the specified index.
     * @throws IndexOutOfBoundsException If the specified index is negative or is greater than or
     *     equal to the size of the positional arguments.
     */
    public String getPositionalArgument(int index) {
        return positionalArguments.get(index);
    }

    public List<String> getPositionalArguments() {
        return positionalArguments;
    }

    public int numberOfPositionalArguments() {
        return positionalArguments.size();
    }

    public int numberOfNamedArguments() {
        return namedArguments.size();
    }

    /**
     * Returns true if the named argument exists.
     *
     * @param name The name of the argument.
     */
    public boolean hasNamedArgument(String name) {
        return namedArguments.containsKey(name);
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage)
            throws CappyException, IOException {
        command.execute(tasks, ui, storage, this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ParsedInput)) {
            return false;
        }
        ParsedInput other = (ParsedInput) obj;
        return commandType == other.commandType
                && namedArguments.equals(other.namedArguments)
                && positionalArguments.equals(other.positionalArguments);
    }

    @Override
    public String toString() {
        return "ParsedInput("
                + commandType
                + ", "
                + namedArguments
                + ", "
                + positionalArguments
                + ")";
    }
}
