package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

/**
 * The Command interface ensures that all its implementing
 * classes contain the execute method.
 */
public interface Command {
    /**
     * Returns a String that confirms the command has been executed.
     * @param command the string input received by scanner.
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @param itemList the ItemList object used in the current Elias instance.
     * @return a string that confirms that the command was executed correctly.
     * @throws CustomExceptions which encompasses all custom exceptions in this
     *                          project
     */
    String execute(String command, String[] info, ItemList itemList) throws CustomExceptions;

}
