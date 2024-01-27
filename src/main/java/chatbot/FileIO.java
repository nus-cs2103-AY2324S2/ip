package chatbot;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    public FileIO() throws IOException {
        File file = new File("store.ser");
        if (!file.exists()) {
            FileOutputStream fileOut = new FileOutputStream("store.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new ArrayList<Task>());
            out.close();
            fileOut.close();
        }
    }

    public ArrayList<Task> readFromStore() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("store.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<Task> taskList = (ArrayList<Task>) in.readObject();
        in.close();
        fileIn.close();

        return taskList;
    }

    public void saveToStore(ArrayList<Task> taskList) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("store.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(taskList);
        out.close();
        fileOut.close();
    }
}

