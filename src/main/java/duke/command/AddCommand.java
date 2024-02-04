package duke.command;

import duke.CustomExceptions;
import duke.ItemList;


public class AddCommand implements Command {
    public String execute(String command, String[] info, ItemList itemList)
            throws CustomExceptions {
        if (info[0].equals("todo")) {
            if (info.length == 1) {
                throw new CustomExceptions.invalidTaskException(
                        "Please re-enter Todo with a valid name");
            } else {
                return itemList.addToDo(info);
            }
        } else if (info[0].equals("deadline")) {
            if (info.length == 1) {
                throw new CustomExceptions.invalidTaskException(
                        "Please re-enter duke.item.Deadline with a valid name");
            } else {
                try {
                    return itemList.addDeadline(info);
                } catch (CustomExceptions.namelessTaskException e) {
                    throw new CustomExceptions.namelessTaskException(
                            "Please re-enter duke.item.Deadline with a valid name");
                }
            }
        } else if (info[0].equals("event")) {
            if (info.length == 1) {
                throw new CustomExceptions.invalidTaskException(
                        "Please re-enter duke.item.Event with a valid name");
            } else {
                try {
                    return itemList.addEvent(info);
                } catch (CustomExceptions.toBeforeFromException e) {
                    throw new CustomExceptions.toBeforeFromException(
                            "Please re-enter duke.item.Event /from BEFORE /to: " + command);
                } catch (CustomExceptions.eventExceptionForFromTo e) {
                    throw new CustomExceptions.eventExceptionForFromTo(
                            "Could not parse /from and /to strings: " + command);
                } catch (CustomExceptions.namelessTaskException e) {
                    throw new CustomExceptions.namelessTaskException(
                            "Please re-enter duke.item.Event with a valid name");
                }
            }
        }
        return null;
    }
}
