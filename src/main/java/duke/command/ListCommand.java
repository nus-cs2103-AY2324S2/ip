package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

/**
 * Represents a command to list all To-do, Deadline, Event, or
 * any other instances of a class that implements the Item interface
 * to an ItemList Object, using the itemList toString() method
 */
public class ListCommand implements Command {
    /**
     * Returns a String that confirms the list command has been executed.
     * The confirmation string is obtained from methods of the classes
     * that implement Item directly.
     *
     * @param command the string input received by scanner.
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @param itemList the ItemList object used in the current Elias instance.
     * @return a string that confirms that the command was executed correctly.
     */
    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        if (info.length == 1) {
            return "Here are your remaining tasks:\n" + itemList.toString();
        }
        return null;
    }
}
