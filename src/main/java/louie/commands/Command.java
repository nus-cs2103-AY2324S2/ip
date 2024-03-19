package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.Parser;

/**
 * Encapsulates the running and parsing logic of a command given to Louie. Create it using the constructor and run it
 * using the <code>run</code> method.
 */
public abstract class Command {
    /**
     * Executes the command. This runs logic of the command
     * @param parser the Parser object that contains the args passed to the command in the user's input. It is assumed
     *             that the parser has already read the first token (i.e. the name of the command).
     */
    public abstract void run(Parser parser, Louie louie) throws LouieException;

}
