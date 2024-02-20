package morty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import morty.task.Deadline;
import morty.task.Event;
import morty.task.Task;
import morty.task.Todo;

/**
 * A class that deals with loading tasks from the file and saving tasks to the
 * file.
 */
public class Storage {

  /**
   * The path of the file to be saved to.
   */
  private String path;

  /**
   * Constructs a Storage object with the given path.
   *
   * @param path The path of the file to be saved to.
   */
  public Storage(String path) {
    this.path = path;
  }

  /**
   * Loads the list of tasks from the file.
   *
   * @return The list of tasks.
   * @throws MortyException If the file does not exist.
   */
  public List<Task> load() throws MortyException {
    try {
      File file = new File(this.path);
      boolean fileExists = file.exists();
      if (!fileExists) {
        file.getParentFile().mkdirs();
        file.createNewFile();
        throw new MortyException("File does not exist");
      }
      FileReader reader = new FileReader(this.path);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      List<Task> tasks = new ArrayList<Task>();
      while ((line = bufferedReader.readLine()) != null) {
        char taskType = line.charAt(0);
        switch (taskType) {
        case 'T':
          tasks.add(Todo.deserialize(line));
          break;
        case 'D':
          tasks.add(Deadline.deserialize(line));
          break;
        case 'E':
          tasks.add(Event.deserialize(line));
          break;
        default:
          break;
        }
      }
      bufferedReader.close();
      return tasks;
    } catch (Exception e) {
      return new ArrayList<Task>();
    }
  }

  /**
   * Saves the given list of tasks to the file.
   *
   * @param tasks The list of tasks to be saved.
   */
  public void save(TaskList tasks) {
    try {
      FileWriter writer = new FileWriter(this.path);
      for (Task task : tasks.getTasks()) {
        writer.write(task.serialize() + "\n");
      }
      writer.close();
    } catch (IOException e) {
    }
  }

}