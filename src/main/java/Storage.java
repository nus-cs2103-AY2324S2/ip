import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

  public static List<Task> load() {
    try {
      FileReader reader = new FileReader("tasks.txt");
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

  public static void save(List<Task> tasks) {
    try {
      FileWriter writer = new FileWriter("tasks.txt");
      for (Task task : tasks) {
        writer.write(task.serialize() + "\n");
      }
      writer.close();
    } catch (IOException e) {
    }
  }

}