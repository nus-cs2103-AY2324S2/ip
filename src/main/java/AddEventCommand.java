import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(TaskList tasks, Ui ui, Storage storage, String description, LocalDateTime start, LocalDateTime end) {
        super(tasks, ui, storage);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        Event event = new Event(description, start, end);
        tasks.addTask(event);
        ui.showTaskAdded(tasks);
    }
}
