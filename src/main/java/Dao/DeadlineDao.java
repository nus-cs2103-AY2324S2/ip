package Dao;

import Database.Database;
import Enums.TaskType;
import Models.Deadline;
import Models.Event;
import Utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DeadlineDao extends TaskDao {
  public static final String NAME = TaskType.DEADLINE.getCommand();
  private static final String byString = "/by";

  public static Deadline getFrom(String input) {
    String name = getName(input);
    String deadline = getDeadline(input);
    Deadline deadlineTask = new Deadline(name, deadline);
    return deadlineTask;
  }

  private static String getName(String input) {
    String name = StringUtils.getValueOfCommand(input, DeadlineDao.NAME, byString);
    return name;
  }

  private static String getDeadline(String input) {
    String deadline = StringUtils.getValueOfCommand(input, byString, null);
    return deadline;
  }

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
