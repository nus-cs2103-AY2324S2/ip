package Aaron.Parser;
import Aaron.Exception.IndexFormatException;

public class IndexParser {
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
