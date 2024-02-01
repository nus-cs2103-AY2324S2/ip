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

public class Storage {

  private String fileName;
  private String folderPath;

  Storage(String folderPath, String fileName) {
    this.fileName = fileName;
    this.folderPath = folderPath;
  }

  private File getFileHandle() throws DukeException {
    try {
      Path baseFolder = Paths.get(System.getProperty("user.dir"));
      Path dataFolder = baseFolder.resolve(folderPath);
      if (!Files.exists(dataFolder) || !Files.isDirectory(dataFolder)) {
        dataFolder.toFile().mkdirs();
      }
      Path taskFile = dataFolder.resolve(fileName);
      if (!Files.exists(taskFile)) {
        taskFile.toFile().createNewFile();
      }
      return taskFile.toFile();
    } catch (IOException e) {
      throw new DukeException("Storage.getFileHandle: %s" + e);
    }
  }

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
