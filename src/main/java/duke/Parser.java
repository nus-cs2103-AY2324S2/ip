package duke;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser class to help with formatting user input.
 */
public class Parser {

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param input User input.
     * @return List<> of formatted user input.
     */
    public List<String> parse(String input) {
        List<String> stringList = new ArrayList<>();

        String firstWord;
        String trail = "";
        int space = input.indexOf(" ");
        if (space == -1) {
            firstWord = input;
        } else {
            firstWord = input.substring(0, space);
            trail = input.substring(space + 1);
        }

        stringList.add(firstWord);
        stringList.add(trail);

        return stringList;
    }
}
