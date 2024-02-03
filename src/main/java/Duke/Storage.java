package Duke;

import java.io.*;

public class Storage {
    String filepath;
    public Storage(String filepath){
        this.filepath = filepath;
    }

    public TaskList load() {
        try{
            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream input = new ObjectInputStream(file);
            TaskList taskList = (TaskList) input.readObject();
            input.close();
            return taskList;
        } catch (IOException | ClassNotFoundException error) {
            // return empty TaskList on exceptions
            TaskList taskList = new TaskList();
            return taskList;
        }
    }
    public void save(TaskList taskList) throws IOException {
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(taskList);
        output.close();
    }
}
