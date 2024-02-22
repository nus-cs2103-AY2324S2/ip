package duke.command;

import duke.CustomExceptions;
import duke.ItemList;

/**
 * Represents a command to find any Item object within the
 * current ItemList that contains a given search string, parsed
 * from command.
 */
public class FindCommand implements Command {
    /**
     * @param command the string input received by scanner.
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @param itemList the ItemList object used in the current Elias instance.
     * @return a string that confirms that the command was executed correctly.
     * @throws CustomExceptions.FindException when no search string is given.
     */
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
