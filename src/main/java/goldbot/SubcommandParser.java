package goldbot;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SubcommandParser utility class to handle parsing of commands with subcommands
 * (eg. event task /from 2020-12-12 /to 2020-12-13)
 */
public class SubcommandParser {
    /**
     * Parses subcommands from a string
     *
     * @param text        String to parse
     * @param initializer Initializer for subcommands
     * @return Pair of initial argument and list of subcommands
     */
    public static Pair<String, List<Pair<String, String>>> parseSubcommands(String text, String initializer) {
        List<Pair<String, String>> subcommandList = new ArrayList<>();
        List<Pair<String, List<String>>> subcommandInternalList = new ArrayList<>();
        String[] words = text.split(" ");
        List<String> initial = new ArrayList<>();
        Pattern p = Pattern.compile("\\" + initializer + "\\w*");
        int i = 0;
        boolean hasBeenMatched = false;
        while (i < words.length) {
            String word = words[i];
            i++;
            Matcher m = p.matcher(word);
            if (m.matches()) {
                hasBeenMatched = true;
                Pair<String, List<String>> subcommand = new Pair<String, List<String>>(word, new ArrayList<String>());
                subcommandInternalList.add(subcommand);
                while (i < words.length) {
                    word = words[i];
                    if (p.matcher(word).matches()) {
                        break;
                    }
                    subcommand.getSecond().add(word);
                    i++;
                }
            }
            if (!hasBeenMatched) {
                initial.add(word);
            }
        }
        String initialArg = String.join(" ", initial);
        for (Pair<String, List<String>> subcommand : subcommandInternalList) {
            String argument = String.join(" ", subcommand.getSecond());
            subcommandList.add(new Pair<String, String>(subcommand.getFirst(), argument));
        }
        return new Pair<String, List<Pair<String, String>>>(initialArg, subcommandList);
    }
}
