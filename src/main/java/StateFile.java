import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StateFile {
    private static String FILE_PATH = "saves/state.txt";
    //  Solution below (use of ObjectOutputStream and FileOutputStream) inspired by ChatGPT
    public void saveObject(ArrayList<Task> object) throws IOException {
        // Create directory if it does not exist
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        FileOutputStream fileStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(object);
        objectStream.close();
        fileStream.close();
    }
    public ArrayList<Task> loadObject() throws IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream(FILE_PATH);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        ArrayList<Task> list;
        try {
            list = (ArrayList<Task>) objectStream.readObject();
        } finally {
            objectStream.close();
            fileStream.close();
        }
        return list;
    }
}
