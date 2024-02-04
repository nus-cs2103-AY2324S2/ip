package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

public interface Command {
    String execute(String command, String[] info, ItemList itemList) throws CustomExceptions;

}
