package jmsandiegoo.tyrone.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the class that handles Storage related tasks.
 */
public class StorageHelper {
    private static final String directoryPath = "data";
    private static final String fileName = "task_list.txt";

    private File checkOrCreateFile() throws IOException {
        // check if the directory exists if not create it
        File directory = new File(directoryPath);
        directory.mkdir();
        assert directory.exists() : "directory should be created if not exist";

        // check if the file exists if not create it
        File file = new File(directoryPath + "/" + fileName);
        file.createNewFile();
        assert file.exists() : "file should be created if not exist";

        return file;
    }

    /**
     * Returns the file that is read from the storage.
     * Throws IOException, if creating a new file fails if not exists.
     *
     * @return File
     * @throws IOException - thrown if create when not exist errors.
     */
    public File loadFile() throws IOException {
        return checkOrCreateFile();
    }

    /**
     * Saves the given content to storage.
     * Throws IOException, if creating a new file fails if not exists.
     *
     * @throws IOException - thrown if create when not exist errors.
     */
    public void saveFile(String content) throws IOException {
        File file = checkOrCreateFile();

        // write into the file
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}