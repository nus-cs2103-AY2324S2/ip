package duke;

import duke.parser.FileParser;
import duke.tasks.Task;
import duke.exceptions.StorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createFile(File f) throws StorageException {
        try {
            f.getParentFile().mkdirs();
            boolean created = f.createNewFile();
            if (!created) {
                throw new StorageException("Failed to create file: " + f.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new StorageException("Error creating file: " + f.getAbsolutePath());
        }
    }

    public ArrayList<Task> loadFile() throws StorageException, FileNotFoundException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks;
        if (!f.exists()) {
            this.createFile(f);
            throw new FileNotFoundException();
        }
        try {
            tasks = FileParser.readFile(f);
        } catch (Exception e) {
            throw new StorageException("Error reading file: " + f.getAbsolutePath());
        }
        return tasks;
    }

    public void saveData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tasks.toString());
        fw.close();
    }

}
