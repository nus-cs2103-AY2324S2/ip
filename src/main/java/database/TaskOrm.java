package database;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;

import duke.Parser;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * A class that handles database operations for tasks.
 */
public class TaskOrm extends Database {
  /**
   * Creates a new todo.
   *
   * @param description
   *          the description of the todo
   * @return the newly created todo
   * @throws SQLException
   *           if a database error occurs
   */
  public Task createTodo(String description) throws SQLException {
    String sql = "INSERT INTO tasks (type, description) VALUES (?, ?)";

    ResultSet rs = this.insert(sql, Todo.type, description);
    int id = rs.getInt(1);

    return new Todo(id, description);
  }

  /**
   * Creates a new deadline.
   *
   * @param description
   *          the description of the deadline
   * @param deadline
   *          the deadline of the deadline
   * @return the newly created deadline
   * @throws SQLException
   *           if a database error occurs
   */
  public Task createDeadline(String description, java.time.LocalDate deadline) throws SQLException {
    String sql = "INSERT INTO tasks (type, description, deadline) VALUES (?, ?, ?)";

    ResultSet rs = this.insert(sql, Deadline.type, description, deadline.toString());
    int id = rs.getInt(1);

    return new Deadline(id, description, deadline);
  }

  /**
   * Creates a new event.
   *
   * @param description
   *          the description of the event
   * @param startDate
   *          the start date of the event
   * @param endDate
   *          the end date of the event
   * @return the newly created event
   * @throws SQLException
   *           if a database error occurs
   */
  public Task createEvent(String description, java.time.LocalDate startDate, java.time.LocalDate endDate)
      throws SQLException {
    String sql = "INSERT INTO tasks (type, description, startDate, endDate) VALUES (?, ?, ?, ?)";

    ResultSet rs = this.insert(sql, Event.type, description, startDate.toString(), endDate.toString());
    int id = rs.getInt(1);

    return new Event(id, description, startDate, endDate);
  }

  /**
   * Marks the task as done.
   *
   * @param taskID
   *          the index of the task to be marked as done
   * @throws SQLException
   *           if a database error occurs
   */
  public void mark(int taskID) throws SQLException {
    this.ensureExists(taskID);
    String sql = "UPDATE tasks SET isDone = 1 WHERE id = ?";
    this.execute(sql, String.valueOf(taskID));
  }

  /**
   * Unmarks the task as done.
   *
   * @param taskID
   *          the index of the task to be unmarked as done
   * @throws SQLException
   *           if a database error occurs
   */
  public void unmark(int taskID) throws SQLException {
    this.ensureExists(taskID);
    String sql = "UPDATE tasks SET isDone = 0 WHERE id = ?";
    this.execute(sql, String.valueOf(taskID));
  }

  /**
   * Deletes the task.
   *
   * @param taskID
   *          the index of the task to be deleted
   * @return the deleted task
   * @throws SQLException
   *           if a database error occurs
   */
  public Task delete(int taskID) throws SQLException {
    Task task = this.get(taskID);

    String sql = "DELETE FROM tasks WHERE id = ?";
    this.execute(sql, String.valueOf(taskID));

    return task;
  }

  /**
   * Gets the task.
   *
   * @param taskID
   *          the index of the task to be retrieved
   * @return the task
   * @throws SQLException
   *           if a database error occurs
   */
  public Task get(int taskID) throws SQLException {
    String sql = "SELECT * FROM tasks WHERE id = ?";

    ResultSet rs = this.select(sql, String.valueOf(taskID));
    if (rs.next()) {
      return this.sqlRowToTask(rs);
    } else {
      throw new SQLException("Task not found");
    }
  }

  /**
   * Gets the number of tasks.
   *
   * @return the number of tasks
   * @throws SQLException
   *           if a database error occurs
   */
  public int count() throws SQLException {
    String sql = "SELECT COUNT(*) FROM tasks";
    ResultSet rs = this.select(sql);
    return rs.getInt(1);
  }

  /**
   * Gets a list of all tasks in the database.
   *
   * @return a list of all tasks in the database
   * @throws SQLException
   *           if a database error occurs
   */
  public ArrayList<Task> list() throws SQLException {
    ArrayList<Task> tasks = new ArrayList<>();

    String sql = "SELECT * FROM tasks";
    ResultSet rs = this.select(sql);
    while (rs.next()) {
      tasks.add(this.sqlRowToTask(rs));
    }

    return tasks;
  }

  /**
   * Gets a list of all tasks in the database that match the keyword.
   *
   * @param keyword
   *          the keyword to match
   * @return a list of all tasks in the database that match the keyword
   * @throws SQLException
   *           if a database error occurs
   */
  public ArrayList<Task> list(String keyword) throws SQLException {
    ArrayList<Task> tasks = new ArrayList<>();

    String sql = "SELECT * FROM tasks WHERE description LIKE ?";
    ResultSet rs = this.select(sql, "%" + keyword + "%");
    while (rs.next()) {
      tasks.add(this.sqlRowToTask(rs));
    }

    return tasks;
  }

  /**
   * Checks if the task is done.
   * 
   * @param taskID
   *          the index of the task to be checked
   * @return true if the task is done, false otherwise
   * @throws SQLException
   *           if a database error occurs
   */
  public boolean isTaskDone(int taskID) throws SQLException {
    String sql = "SELECT isDone FROM tasks WHERE id = ?";
    ResultSet rs = this.select(sql, String.valueOf(taskID));

    if (rs.next()) {
      return rs.getBoolean("isDone");
    } else {
      throw new SQLException("Task not found");
    }
  }

  private void ensureExists(int taskID) throws SQLException {
    String sql = "SELECT COUNT(*) FROM tasks WHERE id = ?";
    ResultSet rs = this.select(sql, String.valueOf(taskID));

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
          isDone);
    case Deadline.type:
      return new Deadline(
          id,
          description,
          Parser.parseDate(deadline),
          isDone);
    case Event.type:
      return new Event(
          id,
          description,
          Parser.parseDate(startDate),
          Parser.parseDate(endDate),
          isDone);
    default:
      throw new IllegalArgumentException("Invalid task type: " + type);
    }
  }
}
