import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalDate deadline;
    private String timing;

    public DeadlineTask(String name, String deadline) {
        super(name);

        String[] dateTime = deadline.split(" ");
        this.deadline = LocalDate.parse(dateTime[0]);

        if (Parser.isNumber(dateTime[1])) {
            int hours = Integer.valueOf(dateTime[1].substring(0, 2));
            int minutes = Integer.valueOf(dateTime[1].substring(2));

            if (hours == 0) {
                this.timing = "12" + (minutes > 0 ? "." + minutes : "") + "am";
            } else if (hours < 12) {
                this.timing = String.valueOf(hours) + (minutes > 0 ? "." + minutes : "") + "am";
            } else {
                this.timing = String.valueOf(hours - 12) + (minutes > 0 ? "." + minutes : "") + "pm";
            }
        } else {
            this.timing = dateTime[1];
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.timing);
    }
}