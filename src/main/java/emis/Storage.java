package main.java.emis;
import main.java.emis.exceptions.EmisException;
import main.java.emis.command.*;
import main.java.emis.task.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

/** The Storage class deals with loading tasks from and saving tasks to a file in the EMIS application. */
public class Storage {

    /** The file path of the storage file. */
    private String filePath;

    /** The list of tasks loaded from the storage file. */
    private ArrayList<Task> al;

    /**
     * Constructs a new Storage object with the specified file path.
     * 
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.al = new ArrayList<>();
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return The list of tasks loaded from the storage file.
     * @throws EmisException If an error occurs during loading tasks from the storage file.
     */
    public ArrayList<Task> load() throws EmisException {
        try {
            Scanner sT = new Scanner(new File(this.filePath));
            while (sT.hasNextLine()) {
                // read from file, add to arraylist al
                String input = sT.nextLine();
                String[] data = input.split("\\|");
                String taskType = data[0].trim();
                String retrieveStatus = data[1].trim();
                boolean convertStatus = retrieveStatus.equals("1");
                String retrieveDescription = data[2].trim();

                if (taskType.equals("T")) {
                    this.al.add(new ToDo(convertStatus, retrieveDescription));

                } else if (taskType.equals("D")) {
                    String finishBy = data[3].trim();
                    this.al.add(new Deadline(convertStatus, retrieveDescription, finishBy));

                } else if (taskType.equals("E")) {
                    String startFrom = data[3].trim();
                    String endBy = data[4].trim();
                    this.al.add(new Event(convertStatus, retrieveDescription, startFrom, endBy));

                } else {
                    throw new EmisException("Invalid input type.");
                }
            } 
            sT.close();
            
        } catch (FileNotFoundException FNFe) {
            // file does not exist yet, so create
            try {
                File emisTxt = new File(this.filePath);
                emisTxt.getParentFile().mkdirs();
                if (emisTxt.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException IOe) {
                System.out.println("An error occurred while creating the file.");
            }
            /*
            catch () {
                // handle folder-does-not-exist-yet case
                // handle data file being corrupted (i.e. content not in expected format)
            }
            */
        }
        return this.al;
    }

    /**
     * Updates the storage file with the current list of tasks.
     */
    public void updateStorage() {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String s = "";
            for (int i = 0; i < this.al.size(); i++) {
                s += this.al.get(i).storeString();
                s += "\n";
            }
            fw.write(s);
            fw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException IOe) {
            System.out.println("Error occurred while writing to the file.");
            IOe.printStackTrace();
        }
    }
}