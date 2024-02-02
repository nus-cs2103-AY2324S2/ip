package duke.task;

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
    public String happenOn(LocalDate date) {
        if (date.isEqual(by.toLocalDate())) {
            return taskInfo();
        }
        return "";
    }

    @Override
    public String saveOutput() {
        return "D " + super.saveOutput() + String.format(" | %s", by);
    }

    @Override
    public String taskInfo() {
        String output = "";
        output += "[D]";
        output += super.taskInfo();
        return output + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs)"
                + System.lineSeparator();
    }
}
