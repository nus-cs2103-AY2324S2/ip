package chaterpillar.commands;

import java.time.LocalDate;

import chaterpillar.datetime.DateTime;

/**
 * <code>Command</code> to list all the tasks that belongs to
 * the specified date.
 *
 * @author marclamp
 */
public class TasksTodayCommand extends TasksDueByDateCommand {

    /**
     * Constructor for this class.
     */
    public TasksTodayCommand() {
        super(new DateTime(LocalDate.now()));
    }
}
