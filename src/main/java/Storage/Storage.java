package Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

public class Storage {
    private String pathName;

    public Storage(String pathName) {
        this.pathName = pathName;
    }

    public LocalDate readAsDate(String time) {
        String[] parts = time.split(" ");
        String datePart = parts[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(datePart, formatter);
        return localDate;
    }

    public void autoUpdate(ArrayList<Task> TodoList) {
        File file = new File(this.pathName);
        file.getParentFile().mkdirs();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task t : TodoList) {
                if (t instanceof Todo) {
                    writer.write("T | " + t.getIsDone() + " | " + t.getDescription());
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    writer.write("D | " + d.getIsDone() + " | " + d.getDescription() + " | " + d.getBy());
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    writer.write("E | " + e.getIsDone() + " | " + e.getDescription() + " | " + e.getFrom() + " | "
                            + e.getTo());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getHistory() {
        String fileName = "./data/duke.txt";
        ArrayList<Task> historyList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task t = createTaskFromLine(line);
                if (t != null) {
                    historyList.add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    private Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            boolean isDone = Boolean.parseBoolean(parts[1]);
            String description = parts[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            switch (parts[0]) {
                case "T":
                    return new Todo(description, isDone);
                case "D":
                    if (parts.length >= 4) {
                        String by = parts[3];
                        LocalDate localDate = LocalDate.parse(by, formatter);
                        return new Deadline(description, localDate, isDone);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        String from = parts[3];
                        String to = parts[4];
                        LocalDate localDateFrom = LocalDate.parse(from, formatter);
                        LocalDate localDateTo = LocalDate.parse(to, formatter);
                        return new Event(description, localDateFrom, localDateTo, isDone);
                    }
                    break;
                default:
                    break;
            }
        }
        return null;
    }
}