package anita;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Database class handles operations related to the file.
 * The file is used for storing and reading task data.
 */
public class Database {
    private File myFile;
    private Duke duke;

    /**
     * The constructor for the database class.
     *
     * @param duke The main class object.
     */
    public Database(Duke duke) {
        myFile = new File("./data/saved-data");
        this.duke = duke;
    }

    /**
     * Creates the file if it does not exist, else does nothing.
     */
    public void createFile() {
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the message passed in into the text file.
     *
     * @param msg The message to be written.
     */
    public void writeFile(String msg) {
        try {
            FileWriter myWritter = new FileWriter(myFile, true);
            myWritter.write(msg + "\n");
            myWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents of the file and returns each line as a member of an ArrayList.
     *
     * @return The ArrayList containing the contents of the file.
     */
    public ArrayList<String> readFile() {
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            clearFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Takes the ArrayList of data read from the text file, and processes it.
     * Creates a list of tasks from the data.
     *
     * @param data The data used to create tasks.
     */
    public void loadData(ArrayList<String> data) {
        for (String i : data) {
            String[] tokens = i.split("/");
            String command = tokens[0].split(" ")[0];
            try {
                duke.addingTask(command, i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Used to delete the corresponding data line in the file after deleting a task.
     *
     * @param index The index of the deleted task.
     */
    public void deleteLine(int index) {
        ArrayList<String> data = readFile();
        data.remove(index - 1);
        clearFile();
        for (String i : data) {
            writeFile(i);
        }
    }

    /**
     * Used to change the corresponding data line in the file after updating its status.
     *
     * @param index The index of the changed task.
     * @param status The new status string.
     */
    public void changeLine(int index, String status) {
        ArrayList<String> data = readFile();
        String removedTask = data.remove(index - 1);
        removedTask = removedTask.split("\\|")[0];
        data.add(removedTask + status);
        clearFile();
        for (String i : data) {
            writeFile(i);
        }
    }

    /**
     * Clears the content of the text file.
     */
    public void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(myFile);
            pw.print("");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
