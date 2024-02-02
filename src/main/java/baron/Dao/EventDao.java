package baron.Dao;

import baron.Database.Database;
import baron.Enums.TaskType;
import baron.Models.Event;
import baron.Utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends TaskDao {
  public static final String NAME = TaskType.EVENT.getCommand();
  private static final String FROM_STRING = "/from";
  private static final String TO_STRING = "/to";
  public static Event getFrom(String input) {
    String name = getEventName(input);
    String startDate = getEventFrom(input);
    String endDate = getEventTo(input);
    Event event = new Event(name, startDate, endDate);
    return event;
  }

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

  private static String getEventName(String input) {
    return StringUtils.getValueOfCommand(input, EventDao.NAME, EventDao.FROM_STRING);
  }

  private static String getEventTo(String input) {
    return StringUtils.getValueOfCommand(input, EventDao.TO_STRING, null);
  }

  private static String getEventFrom(String input) {
    return StringUtils.getValueOfCommand(input, EventDao.FROM_STRING, EventDao.TO_STRING);
  }
}
