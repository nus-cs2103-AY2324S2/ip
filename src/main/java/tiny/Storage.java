package tiny;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tiny.exceptions.TinyException;

/**
 * Represents the storage to save and load data.
 */
public class Storage {
    protected String filePath;

    /**
     * Initializes Storage.
     *
     * @param filePath The file path to the file where the data is saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads (load) tasks data from a specified file path.
     *
     * @return An ArrayList that contains the details of the tasks in each entry.
     */
    public ArrayList<String> loadData() throws TinyException {
        try {
            if (!new File("../tinySaveFiles/").exists()) {
                new File("../tinySaveFiles/").mkdirs();
            }
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            ArrayList<String> data = new ArrayList<>();
            while (sc.hasNextLine()) {
                String d = sc.nextLine();
                data.add(d);
            }
            sc.close();
            return data;
        } catch (FileNotFoundException e) {
            throw new TinyException("File not found");
        }
    }

    /**
     * Writes (save) tasks data to a specified file path.
     *
     * @param dataToSave The list of data to be saved.
     */
    public void saveData(ArrayList<String> dataToSave) {
        try {
            new FileWriter(filePath).close();

            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < dataToSave.size(); i++) {
                fileWriter.write(dataToSave.get(i) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving.");
        }
    }
}
