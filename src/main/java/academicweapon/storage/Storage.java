package academicweapon.storage;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for handling storage-related operations in the Duke application.
 * The Storage class is responsible for loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor for creating a Storage instance with a specified file path.
     *
     * @param fileName The file path for the task list data file
     */
    public Storage(String fileName) {
        assert fileName != null : "File name should not be null";

        this.filePath = "./data/" + fileName;
        this.file = new File(fileName);
        try {
            File parentDirectory = file.getParentFile();
            if (!parentDirectory.exists()) {
                boolean directoryCreated = parentDirectory.mkdirs();
                assert directoryCreated : "Failed to create parent directory";
                if (directoryCreated) {
                    System.out.println("Parent directory created successfully.");
                } else {
                    System.out.println("Failed to create parent directory");
                }
            }

            if (file.exists()) {
                this.file = file;
            } else {
                file.createNewFile();
                this.file = file;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a list of strings.
     *
     * @return ArrayList containing tasks read from the file
     * @throws DukeExceptions If there is an error in loading or parsing the file
     */
    public ArrayList<String> load() throws DukeExceptions {
        assert file != null : "File should not be null";

        ArrayList<String> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            System.out.println("This is retrieved from the file.");
            while (sc.hasNext()) {
                String currentLine = sc.nextLine();
                DukeExceptions.checkCorruptedFile(currentLine);
                System.out.println(currentLine);
                lst.add(currentLine);
            }
            System.out.println("____________________________________________________________");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I can't seem to find the file that you want.");
        } catch (DukeExceptions e) {
            throw new DukeExceptions(e.getMessage());
        }
        return lst;
    }

    /**
     * Saves the provided list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task t: tasks) {
                writer.write(t.fileToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
