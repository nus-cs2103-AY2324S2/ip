package duke;

/**
 * CommandBuilder interface represents the objects that can be used to build a command object
 * given a string input.
 * <p>
 * For example, a command builder object can take in a string "homework" and return a
 * {@link AddTodoCommand} object, that when executed, will add a new todo task with the description
 * "homework".
 */
public interface CommandBuilder {
    public Command build(String argument) throws InvalidCommandException;
}
