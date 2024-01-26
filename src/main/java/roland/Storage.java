package roland;

import roland.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Storage {

    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> load() throws RolandException {
        if (new File(filePath).length()!=0) {
            return deserializeArrayList(filePath);
        } else {
            throw new RolandException("Let's get started shall we?");
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Task> deserializeArrayList(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String getFilePath() {
        return this.filePath;
    }
}
