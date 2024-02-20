package tofu;

import tofu.task.Task;
import tofu.task.TaskList;
import tofu.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

class Storage {
    private final File file;

    Storage(String filePath) {
        this.file = new File(filePath);
    }

    Storage(File file) {
        this.file = file;
    }

    /**
     * Reads the content of the file and returns a Task ArrayList.
     * If no existing file is found or if the file is corrupted, an empty ArrayList is returned.
     *
     * @return An ArrayList<Task> containing the content of the file.
     * @throws TofuException If the data in the file is corrupted.
     */
    ArrayList<Task> load() throws TofuException {
        ArrayList<Task> tasks = new ArrayList<>();
        // If file doesn't exist, create the parent directories and the file
        try {
            // Create parent directories if it does not exist
            file.getParentFile().mkdirs();
            // Create file if it does not exist
            file.createNewFile();
            // Read file
            assert file.exists();
            Scanner sc = new Scanner(file);
            String lineRead;
            while (sc.hasNext()) {
                lineRead = sc.nextLine();
                tasks.add(Parser.parseToTask(lineRead, "\\|"));
            }
        } catch (IOException | TofuException e) {
            throw new TofuException(Ui.corruptedDataError());
        }
        return tasks;
    }

    /**
     * Writes the content of the tasks into the file.
     *
     * @param tasks The TaskList that will be written into the file.
     * @throws TofuException If an I/O error occurs.
     */
    void save(TaskList tasks) throws TofuException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.toStore());
            fw.close();
        } catch (IOException ex) {
            throw new TofuException("Failed to save progress!");
        }
    }
}
