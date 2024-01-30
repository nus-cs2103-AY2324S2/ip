import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Task{
    private Task task;
    private LocalDateTime by;
    public Deadlines(String name, LocalDateTime by, Boolean status) {
        super(name, status);
        this.by = by;
    }

    @Override
    public void happenOn(LocalDate date) {
        if (date.isEqual(by.toLocalDate())) {
            this.taskInfo();
        }
    }

    @Override
    public String saveOutput() {
        return "D " + super.saveOutput() + String.format(" | %s", by);
    }

    @Override
    public void taskInfo() {
        System.out.print("[D]");
        super.taskInfo();
        System.out.println(" (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs )");
    }
}
