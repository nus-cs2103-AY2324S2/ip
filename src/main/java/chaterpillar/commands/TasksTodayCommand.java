package chaterpillar.commands;

import java.time.LocalDate;
import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;

public class TasksTodayCommand extends TasksByDateCommand {
    public TasksTodayCommand() throws ChaterpillarException {
        super(new DateTime(LocalDate.now()));
    }
}
