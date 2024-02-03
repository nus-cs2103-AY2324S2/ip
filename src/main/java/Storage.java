import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class Storage {
    private String filePath = "/Users/leedoye/ip/src/data/duke_tasks.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException{
        try (ObjectInputStream intput = new ObjectInputStream(new FileInputStream("filePath"))) {
            return (List<Task>) intput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing tasks or file.");
            throw new DukeException("Error loading tasks from storage");
            //return new ArrayList<>();
        }
    }

    public void save(List<Task> tasks) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("filePath"))) {
            output.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
