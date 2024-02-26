package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * The type Storage.
 * Used for managing storage of tasks in flat file
 */
public class Storage {
    private String filePath="";

    /**
     * Instantiates a new Storage.
     */
    public Storage(){
    }

    /**
     * Instantiates a new Storage.
     *
     * @param filepath the path of the text file storage
     */
    public Storage(String filepath){
        this.filePath = filepath;
    }

    /**
     * Loads list of tasks in string format from file storage.
     *
     * @return the list of Strings
     */
    public List<String> load(){
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            return lines;
        }catch (IOException io){
            throw new RuntimeException("Error while loading data file");
        }
    }

    /**
     * Store the tasks into flat file.
     *
     * @param content the details of tasks in the task list
     * @throws DukeException the duke exception
     */
    public void Store(String content) throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException(e.getMessage());
        }
    }
}
