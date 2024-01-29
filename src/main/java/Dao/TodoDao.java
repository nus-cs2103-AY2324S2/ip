package Dao;

import Database.Database;
import Models.Event;
import Models.Todo;
import Utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
  public static final String NAME = "todo";
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

  public static void add(Todo todo) {
    File table = Database.getTable(NAME);
    String data = todo.toDataString();
    Database.create(table.toPath().toString(), data);
  }
}
