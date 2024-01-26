import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task implements Serializable {
    private final String by;
    private final static Pattern formatter = Pattern.compile("(.+) /by (.+)");

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline fromStr(String input) throws MissMinutesException {
        Matcher matcher = Deadline.formatter.matcher(input);

        if (matcher.find()) {
            String name = matcher.group(1);
            String by = matcher.group(2);

            return new Deadline(name, by);
        } else {
            throw new MissMinutesException("Deadlines have to be created with the following format: deadline <desc> /by <end>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
