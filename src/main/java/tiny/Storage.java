package tiny;

import tiny.exceptions.TinyException;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
    public ArrayList<String> load() throws TinyException {
        try {
            if (!new File("../../../data").exists()) {
                new File("../../../data").mkdirs();
            }
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            ArrayList<String> datas = new ArrayList<>();
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                datas.add(data);
            }
            sc.close();
            return datas;
        } catch (FileNotFoundException e) {
            throw new TinyException("File not found");
        }
    }

    /**
     * Writes (save) tasks data to a specified file path.
     *
     * @param tasksToSave The list of data to be saved.
     */
    public void save(ArrayList<String> tasksToSave) {
        try {
            new FileWriter(filePath).close();

            FileWriter myWriter = new FileWriter(filePath);
            for (int i = 0; i < tasksToSave.size(); i++) {
                myWriter.write(tasksToSave.get(i) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving.");
        }
    }
}
