package ChatbotRan;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String contents, LocalDate deadline) {
        super(contents);
        this.deadline = deadline;
    }

    public Deadline(String contents, String deadline) {
        this(contents, LocalDate.parse(deadline));
    }


    public static Deadline parse(String line, int space) {
        String[] texts = Util.parse(line, space, "/by");
        return new Deadline(texts[0], texts[1]);
    }

    @Override
    String getType() {
        return "D";
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
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
