package fishstock;

class Event extends Task {
    protected final static String keyword = "event";
    private final static String fromKeyword = " /from ";
    private final static String toKeyword = " /to ";
    private String from;
    private String to;

    protected Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    protected static Event of(String input) {
        try {
            if (!FishStock.startsWith(keyword, input)) {
                throw new FishStockException("OH NOSE! This input is not event..");
            }
            int fromIdx = input.indexOf(fromKeyword);
            if (fromIdx == -1) {
                throw new FishStockException("OH NOSE! \"" + fromKeyword + "\" not found..");
            }
            if (keyword.length() + 1 > fromIdx) {
                throw new FishStockException("OH NOSE! The description of event cannot be empty..");
            }
            int toIdx = input.indexOf(toKeyword);
            if (toIdx == -1) {
                throw new FishStockException("OH NOSE! \"" + toKeyword + "\" not found..");
            }
            if (fromIdx > toIdx) {
                throw new FishStockException("OH NOSE! The from-date must be before the to-date..");
            }
            if (fromIdx + fromKeyword.length() > toIdx) {
                throw new FishStockException("OH NOSE! The from-date cannot be empty..");
            }
            if (toIdx + toKeyword.length() == input.length()) {
                throw new FishStockException("OH NOSE! The to-date cannot be empty..");
            }

            String description = input.substring(keyword.length() + 1, fromIdx);
            String from = input.substring(fromIdx + fromKeyword.length(), toIdx);
            String to = input.substring(toIdx + toKeyword.length());
            return new Event(description, from, to);

        } catch (FishStockException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected String toSaveString() {
        return keyword + " " + description + fromKeyword + from +
                toKeyword + to + "/" + boolToInt(isDone) + System.lineSeparator();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}