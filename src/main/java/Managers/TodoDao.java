package Managers;

import Models.Todo;
import Utils.StringUtils;

public class TodoDao {
  public static final String name = "todo";
  public static Todo getFrom(String input) {
    String value = StringUtils.getValueOfCommand(input, TodoDao.name, null);
    return new Todo(value);
  }
}
