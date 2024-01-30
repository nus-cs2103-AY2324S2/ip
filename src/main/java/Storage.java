import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private static final String directoryPath = "./data";
    private static final String filePath = "./data/dude.txt";

    private boolean createStorageIfNotExists() {
        new File(Storage.directoryPath).mkdirs();
        File storageFile = new File(Storage.filePath);
        if (!storageFile.exists()) {
            System.out.println("Storage file not found. Creating storage file...");
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred creating the storage file.");
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public Storage() {
        boolean success = createStorageIfNotExists();
        if (!success) {
            throw new DudeNoStorageException();
        }
    }

    public boolean createRow(Task task) {
        try {
            FileWriter fw = new FileWriter(Storage.filePath, true);
            fw.write(task.getStorageString() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to the storage file.");
            return false;
        }
        return true;
    }

    public boolean createRows(ArrayList<Task> tasks) {
        boolean success = this.clearStorage();
        if (!success) {
            return false;
        }

        for (int i = 0; i < tasks.size(); i++) {
            success = this.createRow(tasks.get(i));
            if (!success) {
                return false;
            }
        }
        return true;
    }

    public boolean clearStorage() {
        try {
            FileWriter fw = new FileWriter(Storage.filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred clearing the storage file.");
            return false;
        }
        return true;
    }

    public ArrayList<Task> listRows() {
        ArrayList<Task> output = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] rowSplit = s.nextLine().split(" \\| ", 2);
                String taskType = rowSplit[0];
                String[] rowData = rowSplit[1].split(" \\| ");
                boolean done = Objects.equals(rowData[0], "1");
                switch (taskType) {
                case "T":
                    output.add(new Todo(rowData[1], done));
                    break;
                case "D":
                    output.add(new Deadline(rowData[1], done, rowData[2]));
                    break;
                case "E":
                    output.add(new Event(rowData[1], done, rowData[2], rowData[3]));
                    break;
                default:
                    System.out.println("Invalid task type found");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred reading from the storage file.");
            return new ArrayList<>();
        }
        return output;
    }
}
