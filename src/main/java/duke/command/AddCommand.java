package duke.command;

import duke.CustomExceptions;
import duke.ItemList;


public class AddCommand implements Command {
    public String execute(String command, String[] info, ItemList itemList)
            throws CustomExceptions {
        if (info[0].equals("todo")) {
            if (info.length == 1) {
                throw new CustomExceptions.InvalidTaskException(
                        "Please re-enter Todo with a valid name");
            } else {
                return itemList.addToDo(info);
            }
        } else if (info[0].equals("deadline")) {
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
        } else if (info[0].equals("event")) {
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
        return null;
    }
}
