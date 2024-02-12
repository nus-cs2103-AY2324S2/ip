package Aaron.Parser;
import Aaron.Exception.IndexFormatException;
/**
 * Class that encapsulates means of obtaining numerical index based on user input for delete/mark/unmark operations
 */
public class IndexParser {

    /**
     * Method to get index from user input
     * @param userInput user input
     * @return integer index of the tasklist to be modified/read from
     * @throws IndexFormatException if user command is not a number
     */
    public static int getIndex(String userInput) throws IndexFormatException {
        Integer index;
        try {
            index = Integer.parseInt(userInput);
            return index;
        } catch (NumberFormatException e) {
            throw new IndexFormatException("index is not a number: " + userInput);
        }
    }
}
