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
        try {
            if (!startsWith(keyword, input)) {
                throw new BobException("OH NOSE! This input is not event..");
            }
            int fromIdx = input.indexOf(fromKeyword);
            if (fromIdx == -1) {
                throw new BobException("OH NOSE! \"" + fromKeyword + "\" not found..");
            }
            if (keyword.length() + 1 > fromIdx) {
                throw new BobException("OH NOSE! The description of event cannot be empty..");
            }
            int toIdx = input.indexOf(toKeyword);
            if (toIdx == -1) {
                throw new BobException("OH NOSE! \"" + toKeyword + "\" not found..");
            }
            if (fromIdx > toIdx) {
                throw new BobException("OH NOSE! The from-date must be before the to-date..");
            }
            if (fromIdx + fromKeyword.length() > toIdx) {
                throw new BobException("OH NOSE! The from-date cannot be empty..");
            }
            if (toIdx + toKeyword.length() == input.length()) {
                throw new BobException("OH NOSE! The to-date cannot be empty..");
            }

            String description = input.substring(keyword.length() + 1, fromIdx);
            String from = input.substring(fromIdx + fromKeyword.length(), toIdx);
            String to = input.substring(toIdx + toKeyword.length());
            return new Event(description, from, to);

        } catch (BobException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}