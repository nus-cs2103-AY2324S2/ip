package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

public class DeleteCommand implements Command {
    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        if (info.length != 2) {
            throw new CustomExceptions.MarkException(
                    "Please enter delete command in the following format: delete <index>");
        } else {
            try {
                int index = Integer.parseUnsignedInt(info[1]);
                return itemList.removeItem(index);
            } catch (NumberFormatException e) {
                throw new CustomExceptions.UnrecognizedCommandException("");
            }
        }
    }
}
