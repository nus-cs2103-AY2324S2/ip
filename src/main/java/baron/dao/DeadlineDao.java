package baron.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import baron.database.Database;
import baron.enums.TaskType;
import baron.models.Deadline;
import baron.utils.DateUtils;
import baron.utils.StringUtils;

/**
 * Manages all DB operations for deadlines.
 */
public class DeadlineDao extends TaskDao {
    public static final String NAME = TaskType.DEADLINE.getCommand();
    private static final String byString = "/by";

    /**
     * Creates a deadline object from the given input string
     *
     * @param input input string with the format deadline deadline_name /by arbitrary_date
     * @return Created deadline object
     */
    public static Deadline getFrom(String input) {
        String name = getName(input);
        LocalDateTime deadline = getDeadline(input);
        Deadline deadlineTask = new Deadline(name, deadline);
        return deadlineTask;
    }

    private static String getName(String input) {
        String name = StringUtils.getValueOfCommand(input, DeadlineDao.NAME, byString);
        return name;
    }

    private static LocalDateTime getDeadline(String input) {
        String deadlineString = StringUtils.getValueOfCommand(input, byString, null);
        LocalDateTime deadline = DateUtils.parseDate(deadlineString);
        return deadline;
    }

    /**
     * Gets all deadlines from the deadline.txt file
     *
     * @return Returns a list of deadlines that was parsed
     */
    public static List<Deadline> getDeadlines() {
        File table = Database.getTable(NAME);
        List<Deadline> deadlines = new ArrayList<>();
        try {
            Files.lines(table.toPath()).forEach(line -> {
                Deadline deadline = Deadline.fromDataString(line);
                deadlines.add(deadline);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return deadlines;
    }
}
