import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private final String by;
    private final static Pattern formatter = Pattern.compile("(.+) /by (.+)");

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline fromStr(String input) {
        Matcher matcher = Deadline.formatter.matcher(input);

        if (matcher.find()) {
            String name = matcher.group(1);
            String by = matcher.group(2);

            return new Deadline(name, by);
        }

        return null; // should throw an error instead
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
