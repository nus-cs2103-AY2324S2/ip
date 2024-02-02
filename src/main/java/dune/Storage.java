package dune;// deals with loading tasks from the file and saving tasks in the file
import dune.task.*;
import dune.DuneException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.List;

public class Storage {

    private String dir;
    private Path filePath;

    public Storage() {
        this.dir = System.getProperty("user.dir");
        this.filePath = Paths.get(dir, "data", "dune.txt");
    }

    public void loadTasks(TaskList tasks) {
        boolean fileExists = java.nio.file.Files.exists(this.filePath);
        // System.out.println(filePath);
        // System.out.println("*****" + fileExists);

        if (fileExists) {
            try {
                // 1 line -> 1 item in the list
                List<String> lines = Files.readAllLines(this.filePath);
                for (String line : lines) {
                    // remove empty lines
                    if (line.trim().equals("")) {
                        continue;
                    }
                    tasks.addTask(convertLineToTask(line));
                }
            } catch (IOException i) {
                System.out.println("Error reading from file");
            } catch (DuneException d) {
                System.out.println(d.getMessage());
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Event was formatted incorrectly in file");
            }
        } else {
            createFile(this.filePath);
            return;
        }
    }

    // Dates are in the format yyyy-mm-ddTHH:MM, unlike what's printed
    public Task convertLineToTask(String s) throws DuneException {
        // components = [type, T/F, task] for todo
        // [type, T/F, task, deadline] for deadline, [type, T/F, task, start, end] for event
        String[] components = s.split("\\|");
        // System.out.println(components.length);
        String eventType = components[0];
        // System.out.println(eventType);
        boolean isDone = (components[1].equals("1")) ? true: false;
        if (eventType.equals("T")) {
            return new ToDo(components[2], isDone);
        } else if (eventType.equals("D")) {
            return new Deadline(components[2], components[3], isDone);
        } else if (eventType.equals("E")) {
            return new Event(components[2], components[3], components[4], isDone);
        } else {
            // System.out.println("Dune exception!");
            throw new DuneException("Invalid task type");
        }
    }

    // Dates are in the format yyyy-mm-ddTHH:MM, unlike what's printed
    public String convertTaskToLine(Task t) throws DuneException {
        String ans = "";
        if (t instanceof ToDo) {
            ans = "T|" + (t.getIsDone() ? "1" : "0") + "|" + t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            ans = "D|" + (t.getIsDone() ? "1" : "0") + "|" + t.getDescription() + "|"
                    + d.getDeadline();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            ans = "E|" + (t.getIsDone() ? "1" : "0") + "|" + t.getDescription() + "|"
                    + e.getStart() + "|" + e.getEnd();
        } else {
            throw new DuneException("Invalid task type");
        }
        return ans;
    }

    public void saveTasks(TaskList tasks) {
        boolean fileExists = java.nio.file.Files.exists(this.filePath);

        if (!fileExists) {
            createFile(this.filePath);
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(convertTaskToLine(tasks.getTask(i)));
                writer.newLine();
            }
            writer.close();
        } catch (IOException i) {
            System.out.println("Error writing to file");
        } catch (DuneException d) {
            System.out.println(d.getMessage());
        }
    }

    public static void createFile(Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException i) {
            System.out.println("Error creating file");
        }
    }

    public static void print(String[] arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

}
