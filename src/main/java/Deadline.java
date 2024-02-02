import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDateTime deadlineDate;
    private String deadline;
    private static boolean isValidDateFormat(String deadline) {
        if (deadline.length() <= 13 || deadline.length() >= 16) {
            return false;
        }
        String[] dateNumbers = deadline.split("[/ ]");
        if (dateNumbers.length != 4) {
            return false;
        }
        try {
            for (String i : dateNumbers) {
                Integer.parseInt(i);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (time >= 2400 || time < 0) {
            return false;
        }
        return true;
    }
    public Deadline(String descr, String deadline) {
        super(descr);
        if (isValidDateFormat(deadline)) {
            String[] dateNumbers = deadline.split("[/ ]");
            this.deadlineDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.deadline = deadline;
        }
    }
    public Deadline(String status, String descr, String deadline) {
        super(descr);
        super.setStatus(status);
        deadline = deadline.trim();
        if (isValidDateFormat(deadline)) {
            String[] dateNumbers = deadline.split("[/ ]");
            this.deadlineDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.deadline = deadline;
        }
    }
    @Override
    public String writeObject() {
        if (deadlineDate != null) {
            return String.format("deadline %s | %s\n",
                    super.writeObject(),
                    this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        } else {
            return String.format("deadline %s | %s\n", super.writeObject(), this.deadline);
        }
    }
    @Override
    public String toString() {
        if (deadlineDate != null) {
            return String.format("[D]%s(by: %s)", super.toString(),
                    this.deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
        }
        return String.format("[D]%s(by: %s)", super.toString(), this.deadline);
    }
}
