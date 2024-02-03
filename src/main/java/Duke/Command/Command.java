package Duke.Command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

public abstract class Command {
    boolean isActive = true;
    public abstract void execute(Storage storage, TaskList taskList, Ui ui);
    static String formatDate(String byDate) {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate parsedDateTime = null;

        System.out.println("Input date: " + byDate);

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDateTime = LocalDate.parse(byDate, formatter);
                break;
            } catch (DateTimeParseException e) {
            }
        }
        if (parsedDateTime == null) {
            return byDate;
        }
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public boolean getIsActive() {
        return this.isActive;
    }
}
