public class Deadline extends Task {
    private final String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public static Deadline parseCommand(String command) {
        String[] words = command.split(" ");
        StringBuilder descriptionBuilder = new StringBuilder();
        int i;
        for (i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                break;
            }
            descriptionBuilder.append(words[i]).append(" ");
        }
        descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

        StringBuilder dateBuilder = new StringBuilder();
        i++;
        for (; i < words.length; i++) {
            dateBuilder.append(words[i]).append(" ");
        }
        dateBuilder.deleteCharAt(dateBuilder.length() - 1);
        return new Deadline(descriptionBuilder.toString(), dateBuilder.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}