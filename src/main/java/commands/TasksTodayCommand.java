package commands;

import java.time.LocalDate;
import datetime.DateTime;
import exceptions.ChaterpillarException;

public class TasksTodayCommand extends TasksByDateCommand {
    public TasksTodayCommand() throws ChaterpillarException {
        super(new DateTime(LocalDate.now()));
    }
}
