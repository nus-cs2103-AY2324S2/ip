package chaterpillar.commands;

import java.time.LocalDate;
import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;

/**
 * <code>Command</code> to list all the tasks that belongs to
 * the specified date.
 */
public class TasksTodayCommand extends TasksByDateCommand {

    /**
     * Constructor for this class.
     */
    public TasksTodayCommand() {
        super(new DateTime(LocalDate.now()));
    }
}
