package tes.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list to store the tasks.
 */
public class Storage {
    private static final String FILE_PATH = "./data/Tes.txt";

    /** Format of the date and time in database */
    private static final DateTimeFormatter FORMATTER_STORE = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private ArrayList<Task> storageList;

    /**
     * Constructs an empty list to load the tasks stored in local disk.
     */
    public Storage() {
        this.storageList = new ArrayList<>();
    }

    /**
     * Saves the recorded tasks into the local disk.
     * If there is no existing directory mentioned in the path, a new one is created.
     */
    public static void saveToFile(ArrayList<Task> tasks) {
        assert tasks != null;
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(file);

            for (Task task : tasks) {
                writer.println(task.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Loads existing task list from the local disk if there is one.
     * If no, a new empty task list is used.
     */
    public ArrayList<Task> loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    if (line.contains("from:")) {
                        String[] partsOfEventTask = line.split(" \\| ");
                        assert partsOfEventTask.length == 4;
                        String statusOfEvent = partsOfEventTask[1];
                        String eventDescription = partsOfEventTask[2];
                        String period = partsOfEventTask[3].substring(6);
                        String[] fromAndTo = period.split(" to: ");
                        String from = fromAndTo[0];
                        String to = fromAndTo[1];
                        LocalDateTime formattedFrom = LocalDateTime.parse(from, FORMATTER_STORE);
                        LocalDateTime formattedTo = LocalDateTime.parse(to, FORMATTER_STORE);
                        Event store = new Event(eventDescription, formattedFrom, formattedTo);
                        if (statusOfEvent.equals("X")) {
                            store.mark();
                        }
                        storageList.add(store);
                    } else if (line.contains("by:")) {
                        String[] partsOfDeadlineTask = line.split(" \\| ");
                        assert partsOfDeadlineTask.length == 4;
                        String statusOfDeadline = partsOfDeadlineTask[1];
                        String deadlineDescription = partsOfDeadlineTask[2];
                        String by = partsOfDeadlineTask[3].substring(4);
                        LocalDateTime formattedBy = LocalDateTime.parse(by, FORMATTER_STORE);
                        Deadline store = new Deadline(deadlineDescription, formattedBy);
                        if (statusOfDeadline.equals("X")) {
                            store.mark();
                        }
                        storageList.add(store);
                    } else {
                        String[] partsOfToDoTask = line.split(" \\| ");
                        assert partsOfToDoTask.length == 3;
                        String statusOfToDo = partsOfToDoTask[1];
                        String toDoDescription = partsOfToDoTask[2];
                        ToDo store = new ToDo(toDoDescription);
                        if (statusOfToDo.equals("X")) {
                            store.mark();
                        }
                        storageList.add(store);
                    }
                } catch (Exception e) {
                    System.out.println("Error printing task from file: " + e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error printing task from file: " + e.getMessage());
        }
        return this.storageList;
    }
}
