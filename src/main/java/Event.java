public class Event extends Task {
    protected static String keyword = "event";
    protected static String fromKeyword = " /from ";
    protected static String toKeyword = " /to ";
    protected String from;
    protected String to;

    private Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event of(String input) {
        if (!startsWith(keyword, input)) {
            System.out.println("OH NOSE! This input is not event..");
            return null;
        }
        int fromIdx = input.indexOf(fromKeyword);
        if (fromIdx == -1) {
            System.out.println("OH NOSE! \"" + fromKeyword + "\" not found..");
            return null;
        }
        if (keyword.length() + 1 > fromIdx) {
            System.out.println("OH NOSE! The description of event cannot be empty..");
            return null;
        }
        int toIdx = input.indexOf(toKeyword);
        if (toIdx == -1) {
            System.out.println("OH NOSE! \"" + toKeyword + "\" not found..");
            return null;
        }
        if (fromIdx > toIdx) {
            System.out.println("OH NOSE! The from-date must be before the to-date..");
            return null;
        }
        if (fromIdx + fromKeyword.length() > toIdx) {
            System.out.println("OH NOSE! The from-date cannot be empty..");
            return null;
        }
        if (toIdx + toKeyword.length() == input.length()) {
            System.out.println("OH NOSE! The to-date cannot be empty..");
            return null;
        }

        String description = input.substring(keyword.length() + 1, fromIdx);
        String from = input.substring(fromIdx + fromKeyword.length(), toIdx);
        String to = input.substring(toIdx + toKeyword.length());
        return new Event(description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}