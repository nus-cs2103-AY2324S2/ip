package duke;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
  private static final String FILE_PATH = "./data/duke.txt";

  public static void saveTasksToFile(ArrayList<Task> taskList) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (Task task : taskList) {
        bufferedWriter.write(task.toFileString());
        bufferedWriter.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void loadTasksFromFile(ArrayList<Task> taskList) {
    try {
      FileReader fileReader = new FileReader(FILE_PATH);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        Task task = createTaskFromLine(line);
        taskList.add(task);
      }

      bufferedReader.close();
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static Task createTaskFromLine(String line) {
    String[] parts = line.split(" \\| ");

    if(parts.length > 2) {
      String type = parts[0];
      String status = parts[1];
      String description = parts[2];

      Task task;

      switch (type) {
        case "T":
          task = new Todos(description);
          break;
        case "D":
          String byString = parts[3];

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
          LocalDateTime byDateTime = LocalDateTime.parse(byString, formatter);

          task = new Deadline(description, byDateTime);
          break;
        case "E":
          String fromString = parts[3];
          String toString = parts[4];

          DateTimeFormatter formatterE = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
          LocalDateTime fromDateTime = LocalDateTime.parse(fromString, formatterE);
          LocalDateTime toDateTime = LocalDateTime.parse(toString, formatterE);

          task = new Events(description, fromDateTime, toDateTime);
          break;
        default:
          task = new Task(description);
          break;
      }

      if (status.equals("1")) {
        task.markAsDone();
      }

      return task;
    } else {
      String status = parts[0];
      String description = parts[1];

      Task task = new Task(description);

      if (status.equals("1")) {
        task.markAsDone();
      }

      return task;
    }
  }
}
