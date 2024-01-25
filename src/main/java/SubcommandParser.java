import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubcommandParser {
    public static List<Pair<String, String>> parseSubcommands(String text, String initializer) {
        List<Pair<String, String>> subcommandList = new ArrayList<>();
        List<Pair<String, List<String>>> subcommandInternalList = new ArrayList<>();
        String[] words = text.split(" ");
        Pattern p = Pattern.compile("\\" + initializer + "\\w*");
        int i = 0;
        while (i < words.length) {
            String word = words[i];
            i++;
            Matcher m = p.matcher(word);
            if (m.matches()) {
                Pair<String, List<String>> subcommand = new Pair<String, List<String>>(word, new ArrayList<String>());
                subcommandInternalList.add(subcommand);
                while (i < words.length && (!p.matcher(word).matches())) {
                    subcommand.getSecond().add(word);
                    i++;
                }
            }
        }
        for (Pair<String, List<String>> subcommand : subcommandInternalList) {
            String argument = String.join(" ", subcommand.getSecond());
            subcommandList.add(new Pair<String, String>(subcommand.getFirst(), argument));
        }
        return subcommandList;
    }
}
