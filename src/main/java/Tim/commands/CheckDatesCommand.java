package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;

import java.nio.file.Path;
import java.time.LocalDate;

import static Tim.exception.ErrorMessages.MESSAGE_NO_MATCH_FOUND;

public class CheckDatesCommand extends Command {
    public static final String COMMAND_WORD = "checkdates";

    private LocalDate fromDate;
    private LocalDate toDate;
    private Path filePath;

    /**
     * Create a CheckDatesCommand object to check for tasks within date period.
     * @param fromDate
     * @param toDate
     * @param filePath
     */
    public CheckDatesCommand(LocalDate fromDate, LocalDate toDate, Path filePath) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.filePath = filePath;
    }

    /**
     * Check for tasks that occur within the specified date period.
     * @param taskList TaskList containing all tasks
     * @return String containing matched tasks.
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException {
        TaskList tasks = new TaskList();
        taskList.checkInRange(tasks, fromDate, toDate);

        if (tasks.isEmpty()) {
            throw new TimException(MESSAGE_NO_MATCH_FOUND);
        }
        return GUI.showTaskInScheduleMsg(tasks);
    }
}
