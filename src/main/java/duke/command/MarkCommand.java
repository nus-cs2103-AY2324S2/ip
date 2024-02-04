package duke.command;

import duke.CustomExceptions;
import duke.ItemList;
import duke.item.Item;

public class MarkCommand implements Command {

    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        if (info.length != 2) {
            throw new CustomExceptions.markException("Please enter mark command in the following format: mark <index>");
        } else {
            try {
                int index = Integer.parseUnsignedInt(info[1]);
                Item item = itemList.getList().get(index - 1);
                item.markDone();
                return item.doneMessage();
            } catch (NumberFormatException e) {
                throw new CustomExceptions.unrecognizedCommandException("");
            }
        }
    }
}
