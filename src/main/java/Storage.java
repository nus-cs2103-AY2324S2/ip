import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private final String path;

    Storage(String path) {
        this.path = path;
    }

    public void write(TaskList tasks) throws DookException {
        try {
            File f = new File(path);
            System.out.println("here");
            if (!f.exists()){
                f.createNewFile();
            }
            FileWriter writer = new FileWriter(path);
            writer.write(tasks.toString());
            writer.close();
            System.out.println("saved!");
        } catch (IOException e) {
            throw new DookException("An error occured when writing to your files...:(" + e);
        }
    }
}
