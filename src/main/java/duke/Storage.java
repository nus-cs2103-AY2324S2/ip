package duke;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private File file;
    private List<Task> items;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.items = new ArrayList<>();
    }

    public void save(MyList mylist) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task t : mylist.getItemsForSaving()) {
                writer.write(t.toSave());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing task to file: " + e.getMessage());
        }
    }

    public List<Task> load() throws FileNotFoundException, DukeException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] loadInput = line.split("\\|");
            String type = loadInput[0].trim();
            String done = loadInput[1].trim();
            String taskString, byString, dateTimePattern, fromString, toString;
            Task task;

            switch (type) {
                case "T":
                    taskString = loadInput[2].trim();
                    task = new Todo(taskString);

                    if (done.equals("1")) {
                        task.markAsDone();
                    }

                    this.items.add(task);
                    break;
                case "D":
                    taskString = loadInput[2].trim();
                    byString = loadInput[3].trim();
                    dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                    if (Pattern.matches(dateTimePattern, byString)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(byString, formatter);
                        task = new Deadline(taskString, dateTime);
                        this.items.add(task);
                    } else {
                        throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                    }

                    if (done.equals("1")) {
                        task.markAsDone();
                    }
                    break;
                case "E":
                    taskString = loadInput[2].trim();
                    fromString = loadInput[3].trim();
                    toString = loadInput[4].trim();
                    dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                    if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
                        LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);
                        task = new Event(taskString, dateTimeFrom, dateTimeTo);
                        this.items.add(task);
                    } else {
                        throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                    }

                    if (done.equals("1")) {
                        task.markAsDone();
                    }
                    break;
            }
        }
        return this.items;
    }
}
