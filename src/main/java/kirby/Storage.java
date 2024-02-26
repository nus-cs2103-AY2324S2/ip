package kirby;

import kirby.tasks.TaskList;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    public static TaskList returnSave() {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "data", "kirby.txt");
        File saveFile = path.toFile();

        TaskList inputs = new TaskList(new ArrayList<>());

        try {
            saveFile.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            inputs = (TaskList) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println("Error: Input seems to not work");
        } catch (SecurityException e) {
            System.out.println("Error: Don't have permissions to create Save File");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Save File is corrupted");
        }

        return inputs;

    }


    public static void save(TaskList inputs){
        try {
            String home = System.getProperty("user.dir");
            java.nio.file.Path path = java.nio.file.Paths.get(home, "data", "kirby.txt");
            File saveFile = path.toFile();

            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(inputs);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch(IOException e){
            System.out.println("Error: Could not write to save file");
        }
    }
}
