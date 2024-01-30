public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event createEvent(String commandLine) throws CoDriverException {
        String[] arguments = commandLine.split(" ");
        if (arguments.length < 2) {
            throw new CoDriverException("Error! You cannot provide an event with no parameters!");
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        int i;
        for (i = 1; i < arguments.length; i++) {
            if (arguments[i].equals("/from")) {
                break;
            }
            descriptionBuilder.append(arguments[i]).append(" ");
        }
        if (descriptionBuilder.length() == 0) {
            throw new CoDriverException("Error! You cannot provide an event with no description!");
        }
        descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

        if (i >= arguments.length - 1) { // if the last word is /from or there is no /from
            throw new CoDriverException("Error! You must provide a /from date/time for an event!");
        }

        StringBuilder fromBuilder = new StringBuilder();
        i++;
        for (; i < arguments.length; i++) {
            if (arguments[i].equals("/to")) {
                break;
            }
            fromBuilder.append(arguments[i]).append(" ");
        }
        if (fromBuilder.length() == 0) { // if the /from field is empty
            throw new CoDriverException("Error! The /from field is empty!");
        }
        fromBuilder.deleteCharAt(fromBuilder.length() - 1);

        if (i >= arguments.length - 1) { // if the last word is /to or there is no /to
            throw new CoDriverException("Error! You must provide a /to date/time for an event!");
        }

        StringBuilder toBuilder = new StringBuilder();
        i++;
        for (; i < arguments.length; i++) {
            toBuilder.append(arguments[i]).append(" ");
        }
        toBuilder.deleteCharAt(toBuilder.length() - 1);

        return new Event(descriptionBuilder.toString(), fromBuilder.toString(),
                toBuilder.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from
                + " to: " + this.to + ")";
    }
}