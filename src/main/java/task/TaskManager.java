package task;

import java.sql.*;
import java.util.ArrayList;
import duke.Parser;

public class TaskManager {
  public Task addTodoTask(String description) throws SQLException {
    String sql = "INSERT INTO tasks (type, description) VALUES (?, ?)";

    ResultSet rs = database.DB.insert(sql, Todo.type, description);
    int id = rs.getInt(1);

    return new Todo(id, description);
  }

  public Task addDeadlineTask(String description, java.time.LocalDate deadline) throws SQLException {
    String sql = "INSERT INTO tasks (type, description, deadline) VALUES (?, ?, ?)";

    ResultSet rs = database.DB.insert(sql, Deadline.type, description, deadline.toString());
    int id = rs.getInt(1);

    return new Deadline(id, description, deadline);
  }

  public Task addEventTask(String description, java.time.LocalDate startDate, java.time.LocalDate endDate) throws SQLException {
    String sql = "INSERT INTO tasks (type, description, startDate, endDate) VALUES (?, ?, ?, ?)";

    ResultSet rs = database.DB.insert(sql, Event.type, description, startDate.toString(), endDate.toString());
    int id = rs.getInt(1);

    return new Event(id, description, startDate, endDate);
  }

  /**
   * Marks the task as done.
   *
   * @param taskID the index of the task to be marked as done
   * @return true if the task is successfully marked as done, false otherwise
   */
  public void markTaskAsDone(int taskID) throws SQLException {
    checkTaskExists(taskID);
    String sql = "UPDATE tasks SET isDone = 1 WHERE id = ?";
    database.DB.execute(sql, String.valueOf(taskID));
  }

  /**
   * Unmarks the task as done.
   *
   * @param taskID the index of the task to be unmarked as done
   */
  public void unmarkTaskAsDone(int taskID) throws SQLException {
    checkTaskExists(taskID);
    String sql = "UPDATE tasks SET isDone = 0 WHERE id = ?";
    database.DB.execute(sql, String.valueOf(taskID));
  }

  public Task deleteTask(int taskID) throws SQLException {
    Task task = this.getTask(taskID);

    String sql = "DELETE FROM tasks WHERE id = ?";
    database.DB.execute(sql, String.valueOf(taskID));

    return task;
  }

  public Task getTask(int taskID) throws SQLException {
    String sql = "SELECT * FROM tasks WHERE id = ?";

    ResultSet rs = database.DB.select(sql, String.valueOf(taskID));
    if (rs.next()) {
      return this.sqlRowToTask(rs);
    } else {
      throw new SQLException("Task not found");
    }
  }


  public int getNumberOfTasks() throws SQLException {
    String sql = "SELECT COUNT(*) FROM tasks";
    ResultSet rs = database.DB.select(sql);
    return rs.getInt(1);
  }

  public ArrayList<Task> getTasks() throws SQLException {
    ArrayList<Task> tasks = new ArrayList<>();

    String sql = "SELECT * FROM tasks";
    ResultSet rs = database.DB.select(sql);
    while (rs.next()) {
      tasks.add(this.sqlRowToTask(rs));
    }

    return tasks;
  }


  public boolean isTaskDone(int taskID) throws SQLException {
    String sql = "SELECT isDone FROM tasks WHERE id = ?";
    ResultSet rs = database.DB.select(sql, String.valueOf(taskID));

    if (rs.next()) {
      return rs.getBoolean("isDone");
    } else {
      throw new SQLException("Task not found");
    }
  }

  private void checkTaskExists(int taskID) throws SQLException {
    String sql = "SELECT COUNT(*) FROM tasks WHERE id = ?";
    ResultSet rs = database.DB.select(sql, String.valueOf(taskID ));

    if (!rs.next()) {
      throw new SQLException("Task not found");
    } else if (rs.getInt(1) == 0) {
      throw new SQLException("Task not found");
    }
  }

  private Task sqlRowToTask(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String type = rs.getString("type");
    String description = rs.getString("description");
    String deadline = rs.getString("deadline");
    String startDate = rs.getString("startDate");
    String endDate = rs.getString("endDate");
    boolean isDone = rs.getBoolean("isDone");

    switch (type) {
      case Todo.type:
        return new Todo(
          id,
          description,
          isDone
        );
      case Deadline.type:
        return new Deadline(
          id,
          description,
          Parser.parseDate(deadline),
          isDone
        );
      case Event.type:
        return new Event(
          id,
          description,
          Parser.parseDate(startDate),
          Parser.parseDate(endDate),
          isDone
        );
      default:
        throw new IllegalArgumentException("Invalid task type: " + type);
    }
  }
}
