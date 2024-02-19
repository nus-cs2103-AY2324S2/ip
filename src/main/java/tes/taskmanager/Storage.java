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
    private static final String FILE_PATH = "./data/Tes.txt"; // path of the file
    /** Format of the date and time in the command received */
    private static final DateTimeFormatter FORMATTER_RECEIVE = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /** Format of the date and time in database */
    private static final DateTimeFormatter FORMATTER_STORE = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private ArrayList<Task> storeList;

    /**
     * Constructs an empty list to load the tasks stored in local disk.
     */
    public Storage() {
        this.storeList = new ArrayList<>();
    }

    /**
     * Saves the recorded tasks into the local disk.
     * If there is no existing directory mentioned in the path, create one.
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
                        String[] split = line.split(" \\| ");
                        assert split.length == 4;
                        String temp = split[3].substring(6);
                        String[] time = temp.split(" to: ");
                        String from = time[0];
                        String to = time[1];
                        LocalDateTime from1 = LocalDateTime.parse(from, FORMATTER_STORE);
                        LocalDateTime to1 = LocalDateTime.parse(to, FORMATTER_STORE);
                        Event store = new Event(split[2], from1, to1);
                        if (split[1].equals("X")) {
                            store.mark();
                        }
                        storeList.add(store);
                    } else if (line.contains("by:")) {
                        String[] split = line.split(" \\| ");
                        assert split.length == 4;
                        String by = split[3].substring(4);
                        LocalDateTime by1 = LocalDateTime.parse(by, FORMATTER_STORE);
                        Deadline store = new Deadline(split[2], by1);
                        if (split[1].equals("X")) {
                            store.mark();
                        }
                        storeList.add(store);
                    } else {
                        String[] split = line.split(" \\| ");
                        assert split.length == 3;
                        ToDo store = new ToDo(split[2]);
                        if (split[1].equals("X")) {
                            store.mark();
                        }
                        storeList.add(store);
                    }
                } catch (Exception e) {
                    System.out.println("Error printing task from file: " + e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error printing task from file: " + e.getMessage());
        }
        return this.storeList;
    }
}
