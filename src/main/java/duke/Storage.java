package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The Storage class handles the loading and saving of task data to a file.
 *
 * The format of the saved data is a serialization of {@link Task} objects, where
 * each task is separated by "<2>", and task details are further divided using
 * "<0>" and "<1>" as separators.
 *
 * @see Task
 * @see TaskList
 */
public class Storage {

  private String filename;
  private String folderpath;

  /**
   * Constructs a Storage object with the specified folder path and file name.
   *
   * @param folderpath The folder path for storing the task data file.
   * @param filename   The name of the file for storing the task data.
   */
  Storage(String folderpath, String filename) {
    this.filename = filename;
    this.folderpath = folderpath;
  }

  private File getFileHandle() throws DukeException {
    try {
      Path baseFolder = Paths.get(System.getProperty("user.dir"));
      Path dataFolder = baseFolder.resolve(folderpath);
      if (!Files.exists(dataFolder) || !Files.isDirectory(dataFolder)) {
        dataFolder.toFile().mkdirs();
      }
      Path taskFile = dataFolder.resolve(filename);
      if (!Files.exists(taskFile)) {
        taskFile.toFile().createNewFile();
      }
      return taskFile.toFile();
    } catch (IOException e) {
      throw new DukeException("Storage.getFileHandle: %s" + e);
    }
  }

  /**
   * Loads tasks from the task data file.
   *
   * @return An ArrayList of Task objects representing the loaded tasks.
   * @throws DukeException
   */
  ArrayList<Task> load() throws DukeException {
    try {
      FileInputStream f = new FileInputStream(getFileHandle());
      String serialised = new String(f.readAllBytes());
      f.close();
      String[] tasksString = serialised.split("<2>");
      return Arrays
        .stream(tasksString)
        .filter(s -> s.length() > 0)
        .map(s -> {
          String[] xs = s.split("<1>");
          String[] taskData = xs[0].split("<0>");
          String[] auxData = xs.length == 1
            ? new String[0]
            : xs[1].split("<0>");
          String taskDesc = taskData[1];
          boolean isDone = taskData[2].equals("X");
          switch (taskData[0]) {
            case "T":
              return TaskList.createTodo(taskDesc, isDone);
            case "E":
              return TaskList.createEvent(
                taskDesc,
                auxData[0],
                auxData[1],
                isDone
              );
            default:
              return TaskList.createDeadline(taskDesc, auxData[0], isDone);
          }
        })
        .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      throw new DukeException("Storage.load: " + e.getMessage());
    }
  }

  /**
   * Saves the given ArrayList of tasks to the task data file.
   *
   * @param storedTasks The ArrayList of Task objects to be saved.
   * @throws DukeException
   */
  void save(ArrayList<Task> storedTasks) throws DukeException {
    try {
      String serialised = storedTasks
        .stream()
        .<String>map(t -> t.serialise())
        .collect(Collectors.joining("<2>"));

      FileWriter f = new FileWriter(getFileHandle());
      f.write(serialised);
      f.close();
    } catch (IOException e) {
      throw new DukeException("Storage.save: " + e.getMessage());
    }
  }
}
