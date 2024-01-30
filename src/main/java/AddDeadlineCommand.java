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

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(TaskList tasks, Ui ui, Storage storage, String description, LocalDateTime by) {
        super(tasks, ui, storage);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute() {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTaskAdded(tasks);

    }
}
