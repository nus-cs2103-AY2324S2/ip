import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;

public class Storage {

  String path;

  public Storage(String path) {
    String wd = System.getProperty("user.dir");
    try {
      // Create directory
      Path dir_ = Paths.get(wd + "/" + path);
      //System.out.println("Attempting to create: " + dir_);
      Files.createDirectories(dir_);
      this.path = wd + "/" + path + "/log.txt";
      //System.out.println("Creating file at: " + this.path);
    } catch (IOException e) {
      System.out.println("Problem setting up file manager: " + e.getMessage());
    }
  }

  public void createLog() {
    File log_file = new File(this.path);
    try {
      boolean response = log_file.createNewFile();
    } catch (IOException e) {
      System.out.println("Problem creating log! " + e.getMessage());
    }
  }

  public LocalDateTime restoreDateTime(String date_string) {
    String[] decomposed_date_string = date_string.split("T");
    String hhmm = decomposed_date_string[1];
    String yymmdd = decomposed_date_string[0];
    String[] decomposed_yymmdd = yymmdd.split("-");
    String[] decomposed_hhmm = hhmm.split(":");
    int year = Integer.parseInt(decomposed_yymmdd[0]);
    int month = Integer.parseInt(decomposed_yymmdd[1]);
    int day = Integer.parseInt(decomposed_yymmdd[2]);
    int hour = Integer.parseInt(decomposed_hhmm[0]);
    int minute = Integer.parseInt(decomposed_hhmm[1]);
    return LocalDateTime.of(year, month, day, hour, minute);
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
    boolean completeStatus = entry[1].equals("T");
    //System.out.println("Entry " + entry[1] + ": " + completeStatus);
    String desc = entry[2];
    switch (entry[0]) {
      case "T":
        Task ret = new ToDos(desc);
        ret.setCompletion(completeStatus);
        return ret;
      case "D":
        ret = new Deadlines(desc, restoreDateTime(entry[3]));
        ret.setCompletion(completeStatus);
        return ret;
      case "E":
        ret = new Events(desc, restoreDateTime(entry[3]),
          restoreDateTime(entry[4]));
        ret.setCompletion(completeStatus);
        return ret;
      default:
        return null;
    }
  }
}
