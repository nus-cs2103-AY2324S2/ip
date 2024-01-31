package botbot.task;

import botbot.exception.InvalidDateException;

import java.time.LocalDateTime;
public class Deadline extends Task {
    String dueDate;
    LocalDateTime time;
    public Deadline(String task, String dueDate) throws InvalidDateException {
        super(task);
        this.dueDate = dueDate; // format: "by <date>"
        this.time = Task.parseDate(dueDate);
    }
    @Override
    public String getRep() {
        return String.format("[D]%s (by: %s)", super.getRep(), time.format(Task.TIME_FORMAT_OUT));
    }
    @Override
    public String fileRep() { return "D|" + super.fileRep() + "|" + dueDate; }
}
