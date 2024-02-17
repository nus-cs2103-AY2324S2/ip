package reacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import reacher.task.Task;


public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
        createFile();
    }

    public boolean isEmpty() {
        File file = new File(path);
        return file.length() == 0;
    }
    public void storeList(List<Task> taskList) {
        try {
            File file = new File(path);
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(taskList);

            o.close();
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Task> loadList() {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!isEmpty()) {
            try {
                File file = new File(path);
                FileInputStream f = new FileInputStream(file);
                ObjectInputStream o = new ObjectInputStream(f);

                //noinspection unchecked
                taskList = (ArrayList<Task>) o.readObject();

                o.close();
                f.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            }
        }
        return taskList;
    }
    private void createFile() {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
