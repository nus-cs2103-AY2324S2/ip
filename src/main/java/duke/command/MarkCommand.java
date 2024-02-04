package duke.command;

import duke.CustomExceptions;
import duke.ItemList;
import duke.item.Item;

/**
 * Represents a command to mark either a To-do, Deadline, Event, or
 * any other instance of a class that implements the Item interface
 * to an ItemList Object as completed.
 */
public class MarkCommand implements Command {
    /**
     * Returns a String that confirms the mark command has been executed.
     * The confirmation string is obtained from methods of the classes
     * that implement Item directly.
     *
     * @param command the string input received by scanner.
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @param itemList the ItemList object used in the current Elias instance.
     * @return a string that confirms that the command was executed correctly.
     * @throws CustomExceptions.MarkException if there is no following information
     *                                        after the command.
     * @throws CustomExceptions.UnrecognizedCommandException if a negative integer
     *                                                       index was passed.
     * @throws CustomExceptions.NoSuchIndexException if an index out of bounds was
     *                                               passed.
     */
    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        if (info.length != 2) {
            throw new CustomExceptions.MarkException("Please enter mark command in the following format: mark <index>");
        } else {
            try {
                int index = Integer.parseUnsignedInt(info[1]);
                Item item = itemList.getList().get(index - 1);
                item.markDone();
                return item.doneMessage();
            } catch (NumberFormatException e) {
                throw new CustomExceptions.UnrecognizedCommandException("");
            } catch (IndexOutOfBoundsException e) {
                throw new CustomExceptions.NoSuchIndexException(
                        "Index out of bounds, there is no event with such an index");
            }
        }
    }
}
