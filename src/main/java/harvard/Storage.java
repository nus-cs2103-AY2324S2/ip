package harvard;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private String filePath;
    public Storage(String fP) {
        this.filePath = fP;
    }

    public BufferedReader load() {
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/harvard.txt"));
            return buffReader;
        } catch (FileNotFoundException ex) {
            CreateTextFile();
        }
        return null;
    }


    public void CreateTextFile() {
        try {
            File file = new File(System.getProperty("user.dir") + "/data/harvard.txt");
            if (file.getParentFile().mkdir()) {
                file.createNewFile();
            } else {
                throw new IOException("Failed to create directory " + file.getParent());
            }
        } catch (IOException e) {

        }
    }

    public void store(TaskList tasks) {
        String textToSave = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            textToSave += tasks.getTask(i).saveString() + "\n";
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/data/harvard.txt"));
            writer.write(textToSave);
            writer.close();
        } catch (Exception e) {

        }
    }
}