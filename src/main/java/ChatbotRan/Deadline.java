package ChatbotRan;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String contents, String deadline) {
        super(contents);
        this.deadline = deadline;
    }

    public static Deadline parse(String line, int space) {
        String[] texts = Util.parse(line, space, "/by");
        if (texts != null) {
            return new Deadline(texts[0], texts[1]);
        }
        return null;
    }

    @Override
    String getType() {
        return "D";
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    String writeTask() {
        return String.format("D\\%b\\%s\\%s", completed, contents, deadline);
    }
}
