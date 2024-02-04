package Duke.activityAndUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code LocalList} class is responsible for managing the persistence of {@link Activity} objects to a local file.
 * It supports loading activities from a specified file and saving activities back to that file. This class enables
 * the application to maintain state across sessions by reading from and writing to a file system.
 */
public class LocalList {
    private final String filePath;

    /**
     * Constructs a {@code LocalList} instance with the specified file path for loading and saving activities.
     *
     * @param filePath the path to the file used for storing activity data.
     */
    public LocalList(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of {@link Activity} objects from the file specified by {@code filePath}. This method parses
     * the file line by line, creating instances of {@code Todo}, {@code Deadline}, and {@code Event} based on the
     * information in each line. It expects lines to be formatted with parts separated by " | ".
     *
     * @return an {@code ArrayList<Activity>} containing all activities loaded from the file. If the file is not found
     * or is corrupted, it prints an error message and returns an empty list.
     */
    public ArrayList<Activity> load() {
        ArrayList<Activity> loadedActivities = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Activity activity = null;
                switch (parts[0]) {
                    case "T":
                        activity = new Todo(parts[1], parts[2]);
                        break;
                    case "D":
                        activity = new Deadline(parts[1], parts[2], parts[3]);
                        break;
                    case "E":
                        activity = new Event(parts[1], parts[2], parts[3], parts[4]);
                        break;
                }
                loadedActivities.add(activity);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("File is not there or has been corrupted");
        }
        return loadedActivities;
    }

    /**
     * Saves the provided list of {@link Activity} objects to the file specified by {@code filePath}. It formats
     * each activity into a string according to its type (Todo, Deadline, Event) and writes each one as a line
     * in the file. If an error occurs during the save operation, it prints an error message.
     *
     * @param activities the list of {@link Activity} objects to be saved to the file.
     */
    public void save(ArrayList<Activity> activities) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Activity activity : activities) {
                if (activity instanceof Todo) {
                    writer.write("T | " + ((Todo) activity).act.get(0) + " | " + ((Todo) activity).act.get(1) + "\n");
                } else if (activity instanceof Deadline) {
                    writer.write("D | " + ((Deadline) activity).act.get(0) + " | " + ((Deadline) activity).act.get(1)
                            + " | " + ((Deadline) activity).act.get(2) + "\n");
                } else if (activity instanceof Event) {
                    writer.write("E | " + ((Event) activity).act.get(0) + " | " + ((Event) activity).act.get(1)
                            + " | " + ((Event) activity).act.get(2) + " | " + ((Event) activity).act.get(3) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the activities.");
        }
    }
}
