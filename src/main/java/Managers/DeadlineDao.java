package Managers;

import Models.Deadline;
 import Utils.StringUtils;

public class DeadlineDao {
  private static final String byString = "/by";

  public static Deadline getFrom(String input) {
    String name = getName(input);
    String deadline = getDeadline(input);
    Deadline deadlineTask = new Deadline(name, deadline);
    return deadlineTask;
  }

  private static String getName(String input) {
    String value = TaskManager.getValue(input);
    // Get the first space in the input and throws error if the /by is next to the first token, because it means no description in between
    if (StringUtils.getIndexOf(input, " ") + 1 == StringUtils.getIndexOf(input, byString)) throw new IllegalArgumentException("Missing description");
    String name = value.substring(0, StringUtils.getIndexOf(value, byString) - 1).trim();
    return name;
  }

  private static String getDeadline(String input) {
    String value = TaskManager.getValue(input);
    if (StringUtils.getIndexOf(value, byString) + byString.length() >= value.length()) throw new IllegalArgumentException("No deadline given");
    String deadline = value.substring(StringUtils.getIndexOf(value, byString) + byString.length()).trim();
    return deadline;
  }
}
