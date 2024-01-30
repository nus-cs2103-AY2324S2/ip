import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

  String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public ArrayList<Task> loadTaskList() throws FileNotFoundException, DukeException, IOException {
    ArrayList<Task> result = new ArrayList<Task>();
    File f = new File(this.filePath);
    Scanner s = new Scanner(f);
    while (s.hasNext()) {
      String line = s.nextLine();
      String[] parts = line.split(" \\| ");
      String type = parts[0];
      boolean isDone = parts[1].equals("1");
      String description = parts[2];
      Task task;
      switch (type) {
        case "T":
          task = new ToDo(description);
          break;
        case "D":
          task = new Deadline(description, LocalDate.parse(parts[3]));
          break;
        case "E":
          task = new Event(description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
          break;
        default:
          throw new DukeException("File contains invalid task type.");
      }
      if (isDone) {
        task.markAsDone();
      }
      result.add(task);
    }
    s.close();
    return result;
  }

  /*
   * Save task list to hard disk
   */
  public void saveTaskList(ArrayList<Task> inputList) throws DukeException {
    try {
      File file = new File(this.filePath);

      try (FileWriter fw = new FileWriter(file)) {
        for (Task task : inputList) {
          fw.write(task.toFileString() + System.lineSeparator());
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
      throw new DukeException("Error saving the task list file.");
    }
  }
}
