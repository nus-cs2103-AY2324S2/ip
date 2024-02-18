package Objects;

import main.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;
    public Deadline(String name, boolean mark, LocalDate deadline) {
        super(name,mark);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by : " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStringFile(){
        return "D|" + super.toStringFile() + "|" + this.deadline;
    }
}
