package tars;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles the file saving and loading
 */

public class Storage {

    private static final String FILE_PATH = "data/tars.txt";
    File f = new File(FILE_PATH);

    /**
     * This method loads the file if it exists, else creates a new file
     */

    public void load(TaskList tlist) {
        try {
            if (!f.exists()) {

                if (f.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("Unable to create file");
                }
            } else {
                System.out.println("Loading data...");
            }
        } catch (
                IOException e) {
            System.out.println("Error " + e.getMessage());
        }

        try {
            tlist.retrieveData(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method write the new task data to the file
     * @param filePath location of file
     * @param tasks List of tasks
     * @throws IOException
     */


    public void write(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toString() + System.lineSeparator());

        }
        fw.close();
    }

    public void addData(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }


}
