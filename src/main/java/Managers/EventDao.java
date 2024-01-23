package Managers;

import Models.Event;
import Utils.StringUtils;

public class EventDao {
  private static final String fromString = "/from";
  private static final String toString = "/to";
  public static Event getFrom(String input) {

    String value = TaskManager.getValue(input);
    String name = getEventName(input);
    String startDate = getEventFrom(value);
    String endDate = getEventTo(value);
    Event event = new Event(name, startDate, endDate);
    return event;
  } 

  // TODO: Refactor all these string util methods into a single function in the Utils class
  private static String getEventName(String input) {
    String value = TaskManager.getValue(input);
    // Checks if /from is the start of the value
    if (StringUtils.getIndexOf(value, fromString) == 0) throw new IllegalArgumentException("Missing description");
    return value.substring(0, StringUtils.getIndexOf(value, fromString) - 1);
  }

  private static String getEventTo(String input) {
    String value = TaskManager.getValue(input);
    // Checks if there is an empty string after the /to
    if (value.substring(StringUtils.getIndexOf(value, toString) + toString.length()).trim().equals("")) throw new IllegalArgumentException("No to date specified for event");
    return value.substring(StringUtils.getIndexOf(value, toString) + toString.length());
  }

  private static String getEventFrom(String input) {
    String value = TaskManager.getValue(input);
    // Checks if there is an empty string between /from and /to
    if (value.substring(
        StringUtils.getIndexOf(value, fromString, fromString.length()), 
        StringUtils.getIndexOf(value, toString, -1)).trim().length() == 0
    ) throw new IllegalArgumentException("No from date given");
    return value.substring(StringUtils.getIndexOf(value, fromString) + fromString.length(), StringUtils.getIndexOf(value, toString) - 1);
  }
}
