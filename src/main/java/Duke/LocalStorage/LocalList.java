package Duke.LocalStorage;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

import java.io.*;
import java.util.ArrayList;

import static Duke.phrase.phrase.phraseCommand;

/**
 * The {@code LocalList} class is responsible for managing the persistence of {@link Activity} objects to a local file.
 * It supports loading activities from a specified file and saving activities back to that file. This class enables
 * the application to maintain state across sessions by reading from and writing to a file system.
 */
/**
 * The {@code LocalList} class is responsible for managing the persistence of {@link Activity} objects to a local file.
 * It supports loading activities from a specified file and saving activities back to that file. This class enables
 * the application to maintain state across sessions by reading from and writing to a file system.
 */
public class LocalList {

    /**
     * The file object representing the local file where activities are stored.
     */
    private final File FILE;

    /**
     * Constructs a {@code LocalList} instance with the specified file path.
     *
     * @param filePath The file path for the local file.
     */
    public LocalList(String filePath) {
        this.FILE = new File(filePath);
        initializeFile();
    }

    /**
     * Initializes the file, creating it and its parent directories if they do not exist.
     */
    private void initializeFile() {
        if (!FILE.exists()) {
            // If the file doesn't exist, create directory and file
            FILE.getParentFile().mkdirs(); // Create parent directories if not existing
            try {
                FILE.createNewFile(); // Create the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the given list of activities to the local file.
     *
     * @param list The array of strings representing the activities to be written.
     */
    public void write(String[] list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (String activity : list) {
                writer.write(activity);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads activities from the local file and updates the provided {@link ActivityList}.
     *
     * @param list The {@link ActivityList} to be updated based on the contents of the local file.
     */
    public void read(ActivityList list) {
        assert list != null : "ActivityList should not be null";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            ArrayList<String> lines = new ArrayList<>();
            int index = 0;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            if (lines.isEmpty() || lines.get(0).isEmpty()) {
                return;
            }

            for (String currentLine : lines) {
                String[] words = currentLine.split(" /isMarked ");
                assert words.length == 2 : "Unexpected format in file";
                if (words.length == 2) {
                    phraseCommand(words[0]).execute(list);
                    if (words[1].equals("1")) {
                        list.mark(index);
                    }
                    index++;
                } else {
                    throw new CommandException("Error reading from file");
                }
            }
        } catch (IOException | CommandException e) {
            e.printStackTrace();
        }
    }
}
