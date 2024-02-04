package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

public class FindCommand implements Command {
    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        String searchString;
        if (info.length == 1) {
            throw new CustomExceptions.FindException(
                    "Please enter valid search string");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < info.length; i++) {
                sb.append(info[i]).append(" ");
            }
            searchString = sb.toString().trim();
        }
        return itemList.find(searchString);
    }
}
