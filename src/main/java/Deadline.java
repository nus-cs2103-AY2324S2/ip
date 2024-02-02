import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task {
    private LocalDateTime deadlineDate;
    public Deadline(String descr, String deadline) {
        super(descr);
        String[] dateNumbers = deadline.split("[/ ]");
        deadlineDate = LocalDateTime.of(
                Integer.parseInt(dateNumbers[2]),
                Integer.parseInt(dateNumbers[1]),
                Integer.parseInt(dateNumbers[0]),
                Integer.parseInt(dateNumbers[3].substring(0, 2)),
                Integer.parseInt(dateNumbers[3].substring(2)));
        System.out.println(deadlineDate);
    }
    public Deadline(String status, String descr, String deadline) {
        super(descr);
        super.setStatus(status);
    }


    @Override
    public String writeObject() {
        return String.format("deadline %s | %s \n", super.writeObject(), this.deadlineDate);
    }
    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.deadlineDate);
    }
}
