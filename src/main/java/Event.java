import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private String start;
    private String end;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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
        if (Integer.parseInt(dateNumbers[3]) >= 2400) {
            return false;
        }
        return true;
    }
    public Event(String desc, String start, String end) {
        super(desc);
        if (isValidDateFormat(start) && isValidDateFormat(end)) {
            String[] dateNumbers = start.split("[/ ]");
            this.startDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
            dateNumbers = end.split("[/ ]");
            this.endDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.start = start;
            this.end = end;
        }
    }
    public Event(String status, String description, String start, String end) {
        super(description);
        super.setStatus(status);
        start = start.trim();
        end = end.trim();
        if (isValidDateFormat(start) && isValidDateFormat(end)) {
            String[] dateNumbers = start.split("[/ ]");
            this.startDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
            dateNumbers = end.split("[/ ]");
            this.endDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.start = start;
            this.end = end;
        }
    }
    @Override
    public String writeObject() {
        if (startDate != null && endDate != null) {
            return String.format("event %s | %s | %s\n",
                    super.writeObject(),
                    this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                    this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        }
        return String.format("event %s | %s | %s \n", super.writeObject(), this.start, this.end);
    }
    @Override
    public String toString() {
        if (startDate != null && endDate != null) {
            return String.format("[E]%s(from: %s to: %s)",
                    super.toString(),
                    this.startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")),
                    this.endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
        }
        return String.format("[E]%s(from: %s to: %s)",
                super.toString(),start, end);
    }
}
