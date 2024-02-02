package task;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {
    private final String filePath;
    private static final String BASE_PATH = "./data/users/";

    public FileManager(String username) {
        File userDirectory = new File(BASE_PATH + username);
        if (!userDirectory.exists() && !userDirectory.mkdirs()) {
            System.out.println(" ");
        }
        this.filePath = BASE_PATH + username + "/duke.txt";
    }

    public List<Task> loadTasks(List<Task> tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            //
        }

        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : tasks) {
                String status = task.isCompleted ? "1" : "0";
                String taskIcon = getTaskIcon(task);
                String taskDetails = getTaskDetails(task);

                writer.write(taskIcon + " | " + status + " | " + taskDetails + "\n");
            }
        } catch (IOException e) {
            //
        }
    }

    private String getTaskDetails(Task task) {
        if (task instanceof Todo) {
            return ((Todo) task).getTaskDescription();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getEventDescription() + " | " + formatDateTime(event.getFrom()) + " | " + formatDateTime(event.getTo());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getDeadlineDescription() + " | " + formatDateTime(deadline.getBy());
        } else {
            return "";
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }


    private Task parseTaskLine(String line) {
        String regex = "\\[(T|D|E)\\] \\| (0|1) \\| (.+?)(?: \\| (.+?)(?: \\| (.+))?)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            String taskType = matcher.group(1);
            boolean isMarked = "1".equals(matcher.group(2));
            String taskDescription = matcher.group(3);

            switch (taskType) {
                case "T":
                    return new Todo(taskDescription, isMarked);
                case "D":
                    String deadlineDate = matcher.group(4);
                    LocalDateTime by = LocalDateTime.parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Deadline(taskDescription, isMarked, by);
                case "E":
                    String from = matcher.group(4);
                    String to = matcher.group(5);
                    LocalDateTime fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Event(taskDescription, isMarked, fromDate, toDate);
                default:
                    System.err.println("Sorry, there's no such task in my system. Try these: todo, deadline, event");
                    break;
            }
        }

        return null;
    }


    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }


    private String getTaskIcon(Task task) {
        return task.getTaskIcon();
    }
}
