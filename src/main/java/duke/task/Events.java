package duke.task;

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
    public String happenOn(LocalDate date) {
        if ((date.isAfter(from.toLocalDate()) && date.isBefore(to.toLocalDate()))
                || date.isEqual(from.toLocalDate()) || date.isEqual(to.toLocalDate())) {
            return taskInfo();
        }
        return "";
    }

    @Override
    public String saveOutput() {
        return "E " + super.saveOutput() + String.format(" | %s/%s", from, to);
    }

    @Override
    public String taskInfo() {
        String output = "";
        output += "[E]";
        output += super.taskInfo();
        return output + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs)" + System.lineSeparator();
    }
}
