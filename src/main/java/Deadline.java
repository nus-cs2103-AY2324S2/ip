public class Deadline extends Task {
    private final String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public static Deadline createDeadline(String commandLine) throws CoDriverException {
        String[] arguments = commandLine.split(" ");
        if (arguments.length < 2) {
            throw new CoDriverException("Error! You cannot provide a deadline with no parameters!");
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        int i;
        for (i = 1; i < arguments.length; i++) {
            if (arguments[i].equals("/by")) {
                break;
            }
            descriptionBuilder.append(arguments[i]).append(" ");
        }
        if (descriptionBuilder.length() == 0) {
            throw new CoDriverException("Error! You cannot provide a deadline with no description!");
        }
        descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

        if (i >= arguments.length - 1) { // if the last word is /by or there is no /by
            throw new CoDriverException("Error! You must provide a /by date/time for a deadline!");
        }

        StringBuilder dateBuilder = new StringBuilder();
        i++;
        for (; i < arguments.length; i++) {
            dateBuilder.append(arguments[i]).append(" ");
        }
        dateBuilder.deleteCharAt(dateBuilder.length() - 1);

        return new Deadline(descriptionBuilder.toString(), dateBuilder.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}