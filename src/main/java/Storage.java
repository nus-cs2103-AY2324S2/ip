import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        // Load tasks from file
        // Solution below inspired by https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            list = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            try {
                // make directory first
                new File("./data").mkdirs();
                // then make file
                // Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
                File myObj = new File(filepath);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException i) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.println("Could not load tasks from file: " + e.getMessage());
        }
        return list;
    }

    public void saveList(ArrayList<Task> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Could not save tasks to file: " + e.getMessage());
        }
    }
}
