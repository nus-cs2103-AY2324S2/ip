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
public class TasksOnDateCommand extends Command {
    private LocalDate date;

    public TasksOnDateCommand(TaskList tasks, Ui ui, Storage storage, LocalDate date) {
        super(tasks, ui, storage);
        this.date = date;
    }

    @Override
    public void execute() {
        ui.showTasksOnDate(tasks, date);
    }
}
