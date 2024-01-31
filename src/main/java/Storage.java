import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

  private String path;

  public Storage(String path) {
    this.path = path;
  }

  public List<Task> load() {
    try {
      File file = new File(this.path);
      boolean fileExists = file.exists();
      if (!fileExists) {
        file.getParentFile().mkdirs();
        file.createNewFile();
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

  public void save(List<Task> tasks) {
    try {
      FileWriter writer = new FileWriter(this.path);
      for (Task task : tasks) {
        writer.write(task.serialize() + "\n");
      }
      writer.close();
    } catch (IOException e) {
    }
  }

}