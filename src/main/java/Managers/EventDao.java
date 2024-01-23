package Managers;

import Models.Event;
import Utils.StringUtils;

public class EventDao {
  public static final String name = "event";
  private static final String fromString = "/from";
  private static final String toString = "/to";
  public static Event getFrom(String input) {
    String name = getEventName(input);
    String startDate = getEventFrom(input);
    String endDate = getEventTo(input);
    Event event = new Event(name, startDate, endDate);
    return event;
  } 

  private static String getEventName(String input) {
    return StringUtils.getValueOfCommand(input, EventDao.name, EventDao.fromString);
  }

  private static String getEventTo(String input) {
    return StringUtils.getValueOfCommand(input, EventDao.toString, null);
  }

  private static String getEventFrom(String input) {
    return StringUtils.getValueOfCommand(input, EventDao.fromString, EventDao.toString);
  }
}
