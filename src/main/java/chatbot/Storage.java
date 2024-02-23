package chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class manages the persistent storage of task data in a file.
 * It is responsible for creating the file (if it doesn't exist) and writing the tasks
 * from a TaskList into this file. This allows for persisting the tasks across different
 * sessions of the application.
 */

public class Storage {

  private String filePath;

  /**
   * Constructs a new Storage object.
   *
   * @param filePath The path of the file where tasks will be stored and retrieved.
   */
  public Storage(String filePath) {
    this.filePath = filePath;
    createFileIfNeeded();
  }

  /**
   * Ensures that the storage file exists. If the file does not exist, this method
   * creates the necessary directories and the file itself. This method is called during
   * the initialization of the Storage object to prepare the file for reading and writing tasks.
   */
  public void createFileIfNeeded() {
    try {
      Path path = Paths.get(filePath);
      if (!Files.exists(path)) {
        Files.createDirectories(path.getParent());
        Files.createFile(path);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Rewrites the entire task list to the storage file. This method is typically called
   * when there is a change in the tasks list (like adding, removing, or modifying tasks)
   * to update the persistent storage.
   *
   * @param tasks The list of tasks to be written to the file.
   */
  public void rewriteFile(ArrayList<Task> tasks) {
    assert tasks != null : "Tasks list cannot be null";
    try (
      BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))
    ) {
      for (Task task : tasks) {
        bw.write(task.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads tasks from the specified file path into an ArrayList of Task  objects.
   * <p>
   * This method reads the file line by line, converting each line into a Task  object using
   * the {@code parseLineToTask} method (which needs to be implemented based on your task format).
   * If the file does not exist, this method returns an empty list. It is designed to be called at
   * the initialization of the application to populate the task list with previously saved tasks.
   * </p>
   *
   * @return An ArrayList containing Task objects loaded from the file. If the file
   * does not exist or an error occurs during reading, this method will return an empty list.
   * @throws IOException If an I/O error occurs reading from the file. The exception is caught
   * within the method and printed to the standard error stream, but not rethrown.
   */
  public ArrayList<Task> loadTasks() {
    ArrayList<Task> loadedTasks = new ArrayList<>();
    try {
      Path path = Paths.get(filePath);
      if (Files.exists(path)) {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
          Task task = parseLineToTask(line);
          loadedTasks.add(task);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return loadedTasks;
  }

  /**
   * Parses a single line from the stored task data into a Task object.
   * This method identifies the task type (Todo, Deadline, Event) based on
   * prefixes in the line and extracts the necessary information to create
   * an instance of the corresponding task type. It also checks whether the
   * task was marked as done and sets its status accordingly.
   *
   * The expected format for each task type is as follows:
   * - Todo: "[T][X] description" or "[T][ ] description" where 'X' indicates the task is done.
   * - Deadline: "[D][X] description (by: date)" or "[D][ ] description (by: date)".
   * - Event: "[E][X] description (from: start time to: end time)" or
   *          "[E][ ] description (from: start time to: end time)".
   *
   * @param line A string representing a single line from the task data file.
   * @return A Task object corresponding to the data represented by the line.
   *         Returns null if the line format does not match any known task type.
   */
  private Task parseLineToTask(String line) {
    Task task = null;
    boolean isDone = line.charAt(3) == 'X'; // Check if the task is marked as done

    if (line.startsWith("[T]")) {
      // Extract description for Todo
      String description = line.substring(6); // Skip over the [T][ ] part
      Todo todo = new Todo(description);
      if (isDone) {
        todo.markAsDone();
      }
      task = todo;
    } else if (line.startsWith("[D]")) {
      // Extract description and by date for Deadline
      int byIndex = line.indexOf("(by: ");
      String description = line.substring(6, byIndex - 1).trim();
      String by = line.substring(byIndex + 5, line.length() - 1); // Extract date
      Deadline deadline = new Deadline(description, by);
      if (isDone) {
        deadline.markAsDone();
      }
      task = deadline;
    } else if (line.startsWith("[E]")) {
      // Extract description, from, and to for Event
      int fromIndex = line.indexOf("(from: ");
      int toIndex = line.indexOf("to: ");
      String description = line.substring(6, fromIndex - 1).trim();
      String from = line.substring(fromIndex + 7, toIndex - 2).trim();
      String to = line.substring(toIndex + 4, line.length() - 1).trim();
      Event event = new Event(description, from, to);
      if (isDone) {
        event.markAsDone();
      }
      task = event;
    }

    return task;
  }
}
