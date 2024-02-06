package commands;

import java.time.LocalDate;
import datetime.DateTime;

public class TasksTodayCommand extends TasksByDateCommand {
    public TasksTodayCommand() {
        super(new DateTime(LocalDate.now()));
    }
}
