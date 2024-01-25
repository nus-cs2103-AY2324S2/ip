import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

  String path;

  public FileManager(String path) {
    String wd = System.getProperty("user.dir");
    this.path = wd.toString() + "/" + path;
    //System.out.println("Creating file at: " + this.path);
  }
  public void createLog() {
    File log_file = new File(this.path);
    try {
      boolean response = log_file.createNewFile();
      if (response) {
        System.out.println("No original log was found, a new file has been created.");
      }
    } catch (IOException e) {
      System.out.println("Problem creating log! " + e.getMessage());
    }
  }

  public void writeLog(ArrayList<Task> current_list) {
    File log_file = new File(this.path);
    StringBuilder sb = new StringBuilder();
    for (Task t : current_list) {
      sb.append(t.getLogRepresentation() + "\n");
    }
    try {
      FileWriter fw = new FileWriter(log_file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(sb.toString());
      bw.close();
    } catch (IOException e) {
      System.out.println("Problem writing to log! " + e.getMessage());
    }
  }

  public void loadHistory(ArrayList<Task> current_list) throws CorruptedLogException {
    File log_entry = new File(this.path);
    try {
      Scanner sc = new Scanner(log_entry);
      while (sc.hasNextLine()) {
        String next_entry = sc.nextLine();
        Task loaded_task = this.parseEntry(next_entry);
        current_list.add(loaded_task);
      }
      sc.close();
    } catch (IOException e) {
      throw new CorruptedLogException(e.getMessage());
    }

  }

  public Task parseEntry(String log_entry) {
    String[] entry = log_entry.split(",");
    boolean completeStatus = entry[1] .equals("T");
    //System.out.println("Entry " + entry[1] + ": " + completeStatus);
    String desc = entry[2];
    switch (entry[0]) {
      case "T":
        Task ret = new ToDos(desc);
        ret.setCompletion(completeStatus);
        return ret;
      case "D":
        ret = new Deadlines(desc, entry[3]);
        ret.setCompletion(completeStatus);
        return ret;
      case "E":
        ret = new Events(desc, entry[3], entry[4]);
        ret.setCompletion(completeStatus);
        return ret;
      default:
        return null;
    }
  }
}
