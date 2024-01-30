import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    private Task task;
    private LocalDateTime from;
    private LocalDateTime to;
    public Events(String name, LocalDateTime from, LocalDateTime to, Boolean status) {
        super(name, status);
        this.from = from;
        this.to = to;

    }

    @Override
    public void happenOn(LocalDate date) {
        if ((date.isAfter(from.toLocalDate()) && date.isBefore(to.toLocalDate()))
                || date.isEqual(from.toLocalDate()) || date.isEqual(to.toLocalDate())) {
            taskInfo();
        }
    }

    @Override
    public String saveOutput() {
        return "E " + super.saveOutput() + String.format(" | %s/%s", from, to);
    }

    @Override
    public void taskInfo() {
        System.out.print("[E]");
        super.taskInfo();
        System.out.println(" (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + ")");
    }
}
