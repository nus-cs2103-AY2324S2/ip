package virtue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Contains the methods needed to load a task list from a file and save a task list to a file. */
public class Storage {
    private final File DIRECTORY = new File("data");
    private final File FILE = new File("data/virtue.txt");

    /**
     * Loads the task list from the designated file.
     *
     * @return The task list saved in the file.
     * @throws VirtueDateTimeException If a date in one of the tasks is invalid or in the wrong format.
     */
    public VirtueTaskList loadTaskList() throws VirtueDateTimeException {
        VirtueTaskList taskList = new VirtueTaskList();

        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
        }

        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS! An error occurred while creating the file: " + e.getMessage());
            }
        }

        try {
            Scanner sc = new Scanner(FILE);
            while (sc.hasNext()) {
                taskList.addFromFile(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while reading the file: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves the task list to the designated file.
     *
     * @param taskList The task list to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void saveToFile(VirtueTaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE);
        fileWriter.write(taskList.fileFormat());
        fileWriter.close();
    }
}
