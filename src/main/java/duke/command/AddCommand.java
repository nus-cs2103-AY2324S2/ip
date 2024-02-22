package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

/**
 * Represents a command to add either a To-do, Deadline, Event, or
 * any other instance of a class that implements the Item interface
 * to an ItemList Object.
 */
public class AddCommand implements Command {
    /**
     * Returns a String that confirms the add command has been executed.
     * The confirmation string is obtained from methods of the classes
     * that implement Item directly.
     *
     * @param command the string input received by scanner.
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @param itemList the ItemList object used in the current Elias instance.
     * @return a string that confirms that the command was executed correctly.
     * @throws CustomExceptions.InvalidTaskException if there is no following
     *                                               information after the command.
     * @throws CustomExceptions.NamelessTaskException if the name in the command
     *                                                cannot be parsed.
     * @throws CustomExceptions.ToBeforeFromException if the /to command comes
     *                                                before the /from command.
     * @throws CustomExceptions.EventExceptionForFromTo if the start and end time
     *                                                  of the event fails to parse.
     */
    public String execute(String command, String[] info, ItemList itemList)
            throws CustomExceptions {

        if (info[0].equals("todo")) {
            if (info.length == 1) {
                throw new CustomExceptions.InvalidTaskException(
                        "Please re-enter Todo with a valid name");
            } else {
                return itemList.addToDo(info);
            }
        }

        if (info[0].equals("deadline")) {
            if (info.length == 1) {
                throw new CustomExceptions.InvalidTaskException(
                        "Please re-enter duke.item.Deadline with a valid name");
            } else {
                try {
                    return itemList.addDeadline(info);
                } catch (CustomExceptions.NamelessTaskException e) {
                    throw new CustomExceptions.NamelessTaskException(
                            "Please re-enter duke.item.Deadline with a valid name");
                }
            }
        }

        if (info[0].equals("event")) {
            if (info.length == 1) {
                throw new CustomExceptions.InvalidTaskException(
                        "Please re-enter duke.item.Event with a valid name");
            } else {
                try {
                    return itemList.addEvent(info);
                } catch (CustomExceptions.ToBeforeFromException e) {
                    throw new CustomExceptions.ToBeforeFromException(
                            "Please re-enter duke.item.Event /from BEFORE /to: " + command);
                } catch (CustomExceptions.EventExceptionForFromTo e) {
                    throw new CustomExceptions.EventExceptionForFromTo(
                            "Could not parse /from and /to strings: " + command);
                } catch (CustomExceptions.NamelessTaskException e) {
                    throw new CustomExceptions.NamelessTaskException(
                            "Please re-enter duke.item.Event with a valid name");
                }
            }
        }

        return "something went wrong";
    }
}
