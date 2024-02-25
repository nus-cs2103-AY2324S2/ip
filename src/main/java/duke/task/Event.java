package duke.task;

import java.time.LocalDate;

import duke.utils.Util;

public class Event extends Task {
    final LocalDate start;
    final LocalDate end;

    public Event(String task, LocalDate start, LocalDate end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public Event(String task, LocalDate start, LocalDate end, TodoState todoState) {
        super(task, todoState);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (todoState == TodoState.DONE ? "1" : "0") + " | " + task + " | " + Util.formatDate(start)
                + " | " + Util.formatDate(end);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Event) {
            return this.start.compareTo(((Event) o).start);
        } else {
            return super.compareTo(o);
        }
    }
}
