package duke.command;

import duke.ItemList;
import duke.CustomExceptions;

public interface Command {
    String execute(String command, String[] info, ItemList itemList) throws CustomExceptions;

}
