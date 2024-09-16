package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final File FILE = new File("./data/logfile.txt");
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String TODO_PREFIX = "T |";
    private static final String DEADLINE_PREFIX = "D |";
    private static final String EVENT_PREFIX = "E |";

    /**
     * Constructs a Storage object using a specified file path.
     * Ensures the necessary file and directories exist.
     *
     * @param filePath The path to the file used for storage.
     * @throws IOException If an I/O error occurs while creating file or directories.
     */
    public Storage(String filePath) throws IOException {
        createFile();
    }

    /**
     * Ensures the storage file and its parent directories exist.
     * Creates them if they do not exist.
     *
     * @throws IOException If an I/O error occurs while creating file or directories.
     */
    public static void createFile() throws IOException {

        if (!FILE.getParentFile().exists()) {
            FILE.getParentFile().mkdirs();
        }
        if (!FILE.exists()) {
            FILE.createNewFile();
        }
    }

    /**
     * Reads tasks from the storage file and constructs a list of Task objects.
     *
     * @return A list of Task objects read from the file.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public List<Task> readFromFile() throws IOException {
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
            Task task = processLine(line);
            if (task != null) {
                l.add(task);
            }
        }
        return l;
    }

    private Task processLine(String line) {
        String[] array_split = line.split(" \\| ");
        boolean isDone = array_split[1].trim().equals("1");
        if (array_split[0].equals("T")) {
            return createToDo(array_split[2], isDone);
        } else if (array_split[0].equals("D")) {
            return createDeadline(array_split[2], array_split[3], isDone);
        } else if (array_split[0].equals("E")) {
            return createEvent(array_split[2], array_split[3], isDone);
        }
        return null;
    }

    private ToDo createToDo(String description, boolean isDone) {
        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markDone();
        }
        return todo;
    }

    private Deadline createDeadline(String description, String endTimeStr, boolean isDone) {
        LocalDateTime endTime;
        try {
            endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            LocalDate date = LocalDate.parse(endTimeStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            endTime = date.atStartOfDay();
        }
        Deadline deadline = new Deadline(description, endTime);
        if (isDone) {
            deadline.markDone();
        }
        return deadline;
    }

    private Event createEvent(String description, String timeRange, boolean isDone) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String[] dates = timeRange.split(" to ");
        LocalDate startTime = LocalDate.parse(dates[0], formatter);
        LocalDate endTime = LocalDate.parse(dates[1], formatter);
        Event event = new Event(description, startTime, endTime);
        if (isDone) {
            event.markDone();
        }
        return event;
    }


    /**
     * Formats a Task object into a string representation for file storage.
     *
     * @param task The Task object to format.
     * @return A string representation of the Task object.
     */
    protected String formatTaskForFile(Task task) {
        String status = task.getStatusIcon();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        if (task instanceof ToDo) {
            return TODO_PREFIX + status + " | " + task.DESCRIPTION;
        }
        else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String formattedEndTime = deadline.END_TIME.format(formatter);
            return DEADLINE_PREFIX + status + " | " + deadline.DESCRIPTION + " | " + formattedEndTime;
        }
        else if (task instanceof Event) {
            Event event = (Event) task;
            String formattedStartTime = event.START_TIME.format(formatter);
            String formattedEndTime = event.END_TIME.format(formatter);
            return EVENT_PREFIX + status + " | " + event.DESCRIPTION + " | " + formattedStartTime + " to " + formattedEndTime;
        }
        return "";
    }

    /**
     * Saves a list of Task objects to the storage file.
     *
     * @param l The list of Task objects to write.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
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

}