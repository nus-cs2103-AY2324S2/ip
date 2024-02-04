package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Deadline extends Task {
    protected String by;

    private LocalDateTime dueDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.parseCaseInsensitive();
        builder.appendPattern("yyyy-MM-dd hh:mma");
        DateTimeFormatter format = builder.toFormatter();
        this.dueDate = LocalDateTime.parse(by, format);
    }

    public String formatter(LocalDateTime dueDate) {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mma");
        return customFormat.format(dueDate);
    }

    public void reminder() {
        LocalDateTime now = LocalDateTime.now();

        if (now.plusDays(1).toLocalDate().equals(this.dueDate.toLocalDate())) {
            System.out.println("One day before " + description + "is due.");
        } else if (now.toLocalDate().equals(this.dueDate.toLocalDate())) {
            System.out.println(description + "is due today at " + this.dueDate.getHour() +
                    ":" + this.dueDate.getMinute());
        }
    }

    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + formatter(this.dueDate);
    }
}
