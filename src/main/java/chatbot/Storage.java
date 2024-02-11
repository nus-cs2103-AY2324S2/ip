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
}
