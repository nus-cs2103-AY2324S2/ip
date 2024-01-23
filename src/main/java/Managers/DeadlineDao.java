package Managers;

import Models.Deadline;
 import Utils.StringUtils;

public class DeadlineDao {
  public static final String name = "deadline";
  private static final String byString = "/by";

  public static Deadline getFrom(String input) {
    String name = getName(input);
    String deadline = getDeadline(input);
    Deadline deadlineTask = new Deadline(name, deadline);
    return deadlineTask;
  }

  private static String getName(String input) {
    String name = StringUtils.getValueOfCommand(input, DeadlineDao.name, byString);
    return name;
  }

  private static String getDeadline(String input) {
    String deadline = StringUtils.getValueOfCommand(input, byString, null);
    return deadline;
  }
}
