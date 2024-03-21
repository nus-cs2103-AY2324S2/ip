package virtue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Contains the methods needed to load a task list from a file and save a task list to a file. */
public class Storage {
    private final File directory = new File("data");
    private final File file = new File("data/virtue.txt");

    /**
     * Loads the task list from the designated file.
     *
     * @return The task list saved in the file.
     * @throws VirtueDateTimeException If a date in one of the tasks is invalid or in the wrong format.
     */
    public VirtueTaskList loadTaskList() throws VirtueDateTimeException {
        VirtueTaskList taskList = new VirtueTaskList();

        if (!directory.exists()) {
            directory.mkdir();
        }

        assert directory.exists() : "directory should be created if it does not exist";

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS! An error occurred while creating the file: " + e.getMessage());
            }
        }

        assert file.exists() : "file should be created if it does not exist";

        try {
            Scanner sc = new Scanner(file);
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
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(taskList.fileFormat());
        fileWriter.close();
    }
}
