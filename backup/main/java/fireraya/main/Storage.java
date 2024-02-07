package fireraya.main;

import fireraya.exception.FirerayaException;
import fireraya.task.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() {
        File f = new File("current_list.txt");
        if (f.exists() == false) {
            try {
                // Create the empty file
                if (f.createNewFile()) {
                    System.out.println("File created successfully: " + f.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
            }
            System.out.println("is Directory?: " + f.isDirectory());
        }
    }

    public void saveToFile(ArrayList<Task> tasks) throws FirerayaException {
        try {
            File file = new File(filePath);

            BufferedWriter write = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks) {
                write.append(t.saveFormat());
                write.append("\n");
            }
            write.close();
        } catch (IOException e) {
            throw new FirerayaException("An IOException occurred. " + e);
        }
    }
}



