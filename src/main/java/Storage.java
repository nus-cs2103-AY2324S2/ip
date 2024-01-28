import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// Purpose is to save tasks and load tasks
public class Storage {

    private final String filePath;
    private final File f;

    // Constructor
    public Storage(String filePath) {
        // Recommended file path: "./data/toDoList.txt"
        this.filePath = filePath;
        f = new File(filePath);

        // If file does not exist, create the file
        if (!f.exists()) {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write("");
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void Load() {

    }

    public void Save() {


    }

    // Format of toDoList.txt: Each line represents a new todo
    // type |   1/0 for marked status | <event-name> |
    //  T   |   1/0   | <event-name>
    //  D   |   1/0   | <event-name>  | <date>
    //  E   |   1/0   | <event-name>  | <start-date> | <end-date>




}
