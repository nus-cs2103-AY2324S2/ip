package Dao;

import Database.Database;
import Enums.TaskType;
import Models.Event;
import Models.Todo;
import Utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TodoDao extends TaskDao {
  public static final String NAME = TaskType.TODO.getCommand();
  public static Todo getFrom(String input) {
    String value = StringUtils.getValueOfCommand(input, TodoDao.NAME, null);
    return new Todo(value);
  }

  public static List<Todo> getTodos() {
    File table = Database.getTable(NAME);
    List<Todo> todos = new ArrayList<>();
    try {
      Files.lines(table.toPath()).forEach(line -> {
        Todo todo = Todo.fromDataString(line);
        todos.add(todo);
      });
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return todos;
  }
}
