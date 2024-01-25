import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

  String path;

  public FileManager(String path) {
    this.path = path;
  }
  public void createLog() {
    File log_file = new File(this.path);
    try {
      log_file.createNewFile();
    } catch (IOException e) {
      System.out.println("Problem reading log! " + e.getMessage());
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

  public Task parseEntry(String log_entry) {
    String[] entry = log_entry.split(",");
    boolean completeStatus = entry[1] == "T" ? true : false;
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
    }

  }

}
