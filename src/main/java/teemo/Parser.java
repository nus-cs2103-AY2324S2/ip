package teemo;
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
        String[] strings = input.split(" ");
        String s = "";
        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                stringList.add(strings[0]); // command
            } else { // build the remaining fields
                if (strings[i].equals("/by") || strings[i].equals("/from") || strings[i].equals("/to")) {
                    stringList.add(s);
                    s = "";
                } else {
                    if (s.equals("")) {
                        s = strings[i];
                    } else {
                        s += " " + strings[i];
                    }
                }
            }
        }
        stringList.add(s);
        assert stringList.size() > 0; // Ensure that this list contains a string
        return stringList;
    }
}
