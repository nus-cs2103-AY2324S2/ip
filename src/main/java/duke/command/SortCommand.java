package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

public class SortCommand implements Command {
    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        return itemList.sort();
    }
}
