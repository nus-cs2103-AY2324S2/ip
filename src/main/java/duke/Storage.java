package duke;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final File FILE = new File("./data/logfile.txt");

    public Storage(String filePath) throws IOException {
        createFile();
    }

    public static void createFile() throws IOException {

        if (!FILE.getParentFile().exists()) {
            FILE.getParentFile().mkdirs();
        }
        if (!FILE.exists()) {
            FILE.createNewFile();
        }
    }

    public List<Task> readFromFile() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Task> l = new ArrayList<>();
        try {
            if (!FILE.exists()) {
                createFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileReader reader = new FileReader(FILE);
        Scanner sc = new Scanner(reader);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] array_split = line.split(" \\| ");
            boolean isDone = array_split[1].trim().equals("1");
            if (array_split[0].equals("T")) {
                ToDo todo = new ToDo(array_split[2]);
                if (isDone) {
                    todo.markDone();
                }
                l.add(todo);
            } else if (array_split[0].equals("D")) {
                String description = array_split[2];
                LocalDateTime endTime;
                try {
                    endTime = LocalDateTime.parse(array_split[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (DateTimeParseException e) {
                    LocalDate date = LocalDate.parse(array_split[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    endTime = date.atStartOfDay();
                }
                Deadline deadline = new Deadline(description, endTime);
                if (isDone) {
                    deadline.markDone();
                }
                l.add(deadline);
            } else if (array_split[0].equals("E")) {
                //Event event = new Event(array_split[1], array_split[2], array_split[3]);
                String description = array_split[2];
                String[] dates = array_split[3].split(" to ");
                LocalDate startTime = LocalDate.parse(dates[0], formatter);
                LocalDate endTime = LocalDate.parse(dates[1], formatter);
                Event event = new Event(description, startTime, endTime);
                if (isDone) {
                    event.markDone();
                }
                l.add(event);
            }
        }
        return l;
    }

    private String formatTaskForFile(Task task) {
        String status = task.getStatusIcon();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.description;
        }
        else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String formattedEndTime = deadline.endTime.format(formatter);
            return "D | " + status + " | " + deadline.description + " | " + formattedEndTime;
        }
        else if (task instanceof Event) {
            Event event = (Event) task;
            String formattedStartTime = event.startTime.format(formatter);
            String formattedEndTime = event.endTime.format(formatter);
            return "E | " + status + " | " + event.description + " | " + formattedStartTime + " to " + formattedEndTime;
            // return "E | " + status + " | " + task.description + " | " + ((Event) task).startTime + "-" + ((Event) task).endTime + task.getExactTime();
        }
        return "";
    }

    public void saveToFile(List<Task> l) throws IOException {
        createFile();

        try (FileWriter fw = new FileWriter(FILE, false)) {
            for (Task task : l) {
                String taskString = formatTaskForFile(task) + System.lineSeparator();
                fw.write(taskString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    protected static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd, e.g., 2023-03-15");
            return null;
        }
    }

    protected static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
            return null;
        }
    }
}
