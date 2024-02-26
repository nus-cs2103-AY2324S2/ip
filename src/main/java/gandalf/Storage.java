package gandalf;

import gandalf.tasktypes.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Stores two files: 1 stored as input streams not meant to be read but for easier loading, the other is stored
 * in readable format
 */
public class Storage {
    private String filePathMeta;
    private String filePathRead;

    public Storage(String filePathMeta, String filePathRead) {
        this.filePathMeta = filePathMeta;
        this.filePathRead = filePathRead;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> data = new ArrayList<>(100);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filePathMeta))) {
            Object storedData = ois.readObject();
            if (storedData instanceof ArrayList<?>) { //handles if user breaks the metadata textfile
                data = (ArrayList<Task>) storedData;
            }
        } catch (FileNotFoundException e) {
            try {
                // Create directory if it doesn't exist
                Path dirPath = Paths.get(this.filePathMeta).getParent();
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                // Create the file if it doesn't exist
                if (!Files.exists(Paths.get(this.filePathMeta))) {
                    Files.createFile(Paths.get(this.filePathMeta));
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            return data;
        }
        return data;
    }
    public void store(ArrayList<Task> arrayList) {
        try {
            // Create directory if it doesn't exist
            Path dirPath = Paths.get(this.filePathMeta).getParent();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Create the file if it doesn't exist
            if (!Files.exists(Paths.get(this.filePathMeta))) {
                Files.createFile(Paths.get(this.filePathMeta));
            }

            // Write object to file
            try (ObjectOutputStream oos = new ObjectOutputStream(
                                                new FileOutputStream(this.filePathMeta, false))) {
                oos.writeObject(arrayList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Create directory if it doesn't exist
            Path dirPath = Paths.get(this.filePathRead).getParent();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Create the file if it doesn't exist
            if (!Files.exists(Paths.get(this.filePathRead))) {
                Files.createFile(Paths.get(this.filePathRead));
            }
            //store as readable format
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePathRead))) {
                for (int i = 0; i < arrayList.size(); i++) {
                    Task action = arrayList.get(i);
                    writer.write((i + 1) + ". " + action);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}