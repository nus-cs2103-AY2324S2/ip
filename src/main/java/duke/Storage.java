package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Storage class contains methods for reading and writing from a text file.
 */
public class Storage {
    private String directoryPath;
    private String filePath;

    /**
     * Constructor for creating a Storage object.
     *
     * @param directoryPath Directory where file is located.
     * @param filePath Path to file to read from/write to.
     */
    public Storage (String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Returns a String ArrayList that contains the contents of the file that was read.
     * Each element in the ArrayList corresponds to an individual line in the file.
     *
     * @return String ArrayList containing contents of file.
     * @throws IOException If error occurs trying to read from file.
     */
    public ArrayList<String> readTaskListData() throws IOException {
        File read = new File(this.filePath);
        Path directory = Paths.get(this.directoryPath);

        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        if (!read.exists()) {
            read.createNewFile();
        }

        Scanner sc = new Scanner(read);
        ArrayList<String> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            tasks.add(data);
        }

        return tasks;
    }

    /**
     * Takes in a TaskList and writes each task to a file.
     * Each task is written to a line in the file containing the task details.
     *
     * @param taskList TaskList containing tasks to be written to file.
     * @throws IOException If error occurs trying to write to file.
     */
    public void writeTaskListData(TaskList taskList) throws IOException {
        File save = new File(this.filePath);

        try {
            if (!save.exists()) {
                save.createNewFile();
            }

            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                fw.write(taskList.getTask(i).toTaskSaveString() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.getStackTrace();
        }
    }
}
