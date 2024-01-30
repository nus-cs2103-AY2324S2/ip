import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {



    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.printOpeningDottedLine();
                Command c = Parser.parse(command);
                c.excuteCommand(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printClosingDottedLine();
            }
        }


    }




    public static void main(String[] args) {
        new Duke("duke").run();
    }




}

class Task {

    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}

class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]["+ super.getStatusIcon() +"] " + super.getDescription();
    }
}

class Deadline extends Task {
    LocalDateTime by;
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );
        String byString = "";
        for (DateTimeFormatter formatter : formatters) {
            try {
                byString = this.by.format(formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        return "[D]["+ super.getStatusIcon() +"] " + super.getDescription() + " (by: " + byString + ")";
    }
}

class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );
        String startString = "";
        String endString = "";

        for (DateTimeFormatter formatter : formatters) {
            try {
                startString = this.start.format(formatter);
                endString = this.end.format(formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        return "[E]["+ super.getStatusIcon() +"] " + super.getDescription() + " (from: " + startString + " to: " + endString + ")";
    }
}

