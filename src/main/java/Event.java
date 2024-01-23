public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event parseCommand(String command) {
        String[] words = command.split(" ");
        StringBuilder descriptionBuilder = new StringBuilder();
        int i;
        for (i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                break;
            }
            descriptionBuilder.append(words[i]).append(" ");
        }
        descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

        StringBuilder fromBuilder = new StringBuilder();
        i++;
        for (; i < words.length; i++) {
            if (words[i].equals("/to")) {
                break;
            }
            fromBuilder.append(words[i]).append(" ");
        }
        fromBuilder.deleteCharAt(fromBuilder.length() - 1);

        StringBuilder toBuilder = new StringBuilder();
        i++;
        for (; i < words.length; i++) {
            toBuilder.append(words[i]).append(" ");
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