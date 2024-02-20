package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Parser;

/**
 * Encapsulates the running and parsing logic of a command given to Duke. Create it using the constructor and run it
 * using the <code>run</code> method.
 */
public abstract class Command {
    /**
     * Executes the command. This runs logic of the command
     * @param parser the Parser object that contains the args passed to the command in the user's input. It is assumed
     *             that the parser has already read the first token (i.e. the name of the command).
     */
    public abstract void run(Parser parser, Duke duke) throws DukeException;

}
