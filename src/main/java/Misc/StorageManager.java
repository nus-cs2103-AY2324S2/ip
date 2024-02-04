package Misc;

import Irwyn.Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class StorageManager {
    private final String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public File load() throws Exception {
        try {
            File data = new File(filePath);
            if (!data.exists()) {
                System.out.println("Data file not found. A new file will be created.\n");
                data.getParentFile().mkdirs();
                data.createNewFile();
            }
            return data;
        } catch (Exception e) {
            System.out.println("couldn't create the new file exception");
            throw new Exception();
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for(Task t : tasks) {
                fw.write(t.toFileFormat()+ "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }
}