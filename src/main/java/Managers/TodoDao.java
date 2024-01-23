package Managers;

import Models.Todo;

public class TodoDao {
  public static Todo getFrom(String input) {
    String value = TaskManager.getValue(input);
    return new Todo(value);
  }
}
