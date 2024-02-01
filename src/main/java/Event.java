import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
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

//        StringBuilder fromBuilder = new StringBuilder();
        LocalDate fromDate = null;
        i++;
        for (; i < arguments.length; i++) {
            if (arguments[i].equals("/to")) {
                break;
            }
            // check if in yyyy-mm-dd format
            fromDate = Parser.parseDate(arguments[i]);
            if (fromDate == null) {
                throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
            }
//            fromBuilder.append(arguments[i]).append(" ");
        }
        if (fromDate == null) { // if the /from field is empty
            throw new CoDriverException("Error! The /from field is empty!");
        }
//        fromBuilder.deleteCharAt(fromBuilder.length() - 1);

        if (i >= arguments.length - 1) { // if the last word is /to or there is no /to
            throw new CoDriverException("Error! You must provide a /to date/time for an event!");
        }

//        StringBuilder toBuilder = new StringBuilder();
        LocalDate toDate = null;
        i++;
        for (; i < arguments.length; i++) {
            // check if in yyyy-mm-dd format
            toDate = Parser.parseDate(arguments[i]);
            if (toDate == null) {
                throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
            }
//            toBuilder.append(arguments[i]).append(" ");
        }
        if (toDate == null) { // if the /to field is empty
            throw new CoDriverException("Error! The /to field is empty!");
        }
//        toBuilder.deleteCharAt(toBuilder.length() - 1);

        return new Event(descriptionBuilder.toString(), fromDate, toDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from
                + " to: " + this.to + ")";
    }

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + this.from + "~" + this.to;
    }
}