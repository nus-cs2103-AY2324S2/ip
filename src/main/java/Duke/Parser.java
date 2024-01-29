package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    public Parser(Ui ui) {
        this.ui = ui;
    }
    public Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            ui.taskFormatError(line);
            return null;
        }
        try {
            String type = parts[0];
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markDone();
                }
                return todo;
            case "D":
                if (parts.length < 4) {
                    ui.deadlineMissingBy(line);
                    return null;
                }
                String by = parts[3].trim();
                Deadline deadline = new Deadline(description, parseDateString(by));
                if (isDone) {
                    deadline.markDone();
                }
                return deadline;
            case "E":
                if (parts.length < 5) { // missing from/to or both
                    ui.eventMissingParam(line);
                    return null;
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                Event event = new Event(description, parseDateString(from), parseDateString(to));
                if (isDone) event.markDone();
                return event;
            default:
                ui.unknownTaskType(type);
                return null;
            }
        } catch (Exception e) {
            ui.genericTaskError(e, line);
            return null;
        }
    }
    public String parseDateString(String dateString) throws DateTimeParseException {
        try {
            LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            ui.parseDateError(dateString);
            return null;
        }
    }
}
