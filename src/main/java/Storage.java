import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    private static Task createTaskFromLine(String line) {
      String[] parts = line.split("\\s*\\|\\s*");
      if (parts.length >= 3) {
          String taskType = parts[0].trim();
          String status = parts[1].trim();
          String description = parts[2].trim();
          
          Task task;
          switch (taskType) {
              case "T":
                  task = new Todo(description);
                  break;
              case "D":
                  if (parts.length >= 4) {
                      String dateTimeString = parts[3].trim();
                      if (dateTimeString.contains(" ")) {
                          LocalDateTime byDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                          task = new Deadline(description, byDateTime);
                      } else {
                          LocalDate byDate = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                          task = new Deadline(description, byDate);
                      }
                  } else {
                      task = null;
                  }
                  break;
              case "E":
                  if (parts.length >= 4) {
                      String dateAndTime = parts[3].trim();
                      String[] event = dateAndTime.split("-");
                      if (event.length == 2) {
                          String start = event[0].trim();
                          String end = event[1].trim();
                          // Parse the event start and end times
                          LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                          LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                          task = new Event(description, startTime, endTime);
                      } else {
                          task = null;
                      }
                  } else {
                      task = null;
                  }
                  break;
              default:
                  task = null;
                  break;
          }

          if (task != null) {
              if (status.equals("1")) {
                  task.markAsDone();
              }
              return task;
          }
      }
      return null;
    }
}
