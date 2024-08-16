package baron.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import baron.database.Database;
import baron.enums.Argument;
import baron.enums.TaskType;
import baron.models.Event;
import baron.utils.StringUtils;

/**
 * Manages all DB operations for events
 */
public class EventDao extends TaskDao {
    public static final String NAME = TaskType.EVENT.getTaskType();
    private static final String ARG_FROM = Argument.FROM.getArg();
    private static final String ARG_TO = Argument.TO.getArg();

    /**
     * Creates a event object from the given input string
     *
     * @param input input string with the format event EVENT NAME /from FROM DATE /by BY DATE
     * @return Created event object
     */
    public static Event getFrom(String input) {
        String name = getEventName(input);
        String startDate = getEventFrom(input);
        String endDate = getEventTo(input);
        Event event = new Event(name, startDate, endDate);
        return event;
    }

    private static String getEventName(String input) {
        return StringUtils.getValueOfCommand(input, EventDao.NAME, EventDao.ARG_FROM);
    }

    private static String getEventFrom(String input) {
        return StringUtils.getValueOfCommand(input, EventDao.ARG_FROM, EventDao.ARG_TO);
    }

    private static String getEventTo(String input) {
        return StringUtils.getValueOfCommand(input, EventDao.ARG_TO, null);
    }

    /**
     * Gets all deadlines from the deadline.txt file
     *
     * @return Returns a list of deadlines that was parsed
     */
    public static List<Event> getEvents() {
        File table = Database.getTable(NAME);
        List<Event> events = new ArrayList<>();
        try {
            Files.lines(table.toPath()).forEach(line -> {
                Event event = Event.fromDataString(line);
                events.add(event);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return events;
    }
}
