package duke.commands;

import duke.Parser;

import java.util.function.Consumer;

/**
 * Encapsulates the running and parsing logic of a command given to Duke. Create it using the constructor and run it
 * using the <code>run</code> method.
 */
public class Command {
    private final String name;
    private final Consumer<Parser> executor;

    /**
     * Executes the command. This runs the lambda previously passed to it in the constructor.
     * @param args the Parser object that contains the args passed to the command in the user's input. It is assumed
     *             that the parser has already read the first token (i.e. the name of the command).
     */
    public void run(Parser args) {
        this.executor.accept(args);
    }

    /**
     * Command constructor method.
     * @param name The name of the command. It should be the name that is used by the user to run that command while
     *             The program is running.
     * @param executor A lambda that takes a <code>Parser</code> object. The executor should be written with the
     *                 assumption that the parser has already read one token.
     */
    public Command(String name, Consumer<Parser> executor) {
        this.name = name;
        this.executor = executor;
    }
}
