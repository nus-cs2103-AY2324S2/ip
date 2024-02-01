package duke.kbot;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * TaskFileManager class that encapsulates the saving of data onto the local
 * hard drive.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class TaskFileManager {

    /** Filepath of where to save to local disk for TASKS */
    private static final String FILEPATH = "src/main/java/duke/memory/output.txt";

    /** Storage format for date. */
    private static final DateTimeFormatter STORAGEFORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /** Printing format for date. */
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /**
     * Constructor for TaskFileManager.
     */
    public TaskFileManager() {
    }

    /**
     * Implementation to save tasks to file
     * 
     * @param tasks Tasks to be saved to the local drive.
     * @throws IOException Thrown when saving to file and file is not found.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (Task t : tasks) {
            String taskToStore = t.convertToStorageFormat();
            fw.write(taskToStore + "\n");
        }
        fw.close();
    }

    /**
     * Implementation to load tasks from local drive.
     * 
     * @throws FileNotFoundException Thrown when file cannot be found.
     * @throws IOException           Thrown when there is issue loading the file.
     * @return Tasks which are saved in the local drive as an ArrayList<Task>.
     */
    public static ArrayList<Task> loadTasksFromFile() throws FileNotFoundException, IOException {
        File f = new File(FILEPATH); // create a File for the given file path
        if (!f.exists()) { // Check if the file exists. If not, create a new file.
            f.createNewFile();
        }
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> data = new ArrayList<>();
        while (sc.hasNext()) { // TYPE | MARK | NAME \ BY \ FROM | TO
            String[] taskSegments = sc.nextLine().split(" \\| ", 2);
            String ins = taskSegments[0];
            String info = taskSegments[1];
            data.add(loadIns(ins, info));
        }
        sc.close();
        return data;
    }

    /**
     * Load the individual Task in the correct format using the instruction String
     * and the information stored in the instruction. Helper function for
     * loadTasksFromFile().
     * 
     * @param ins  String representing the different instructions.
     * @param info String representing the information stored in the Task.
     * @return Returns the Task that is loaded from the given instruction and info.
     */
    public static Task loadIns(String ins, String info) {
        Task t = null;
        switch (ins) {
            case "T":
                String[] td = info.split(" \\| ", 2);
                boolean tCompleted = (td[0].trim() != "");
                String tName = td[1];
                t = new ToDo(tName, tCompleted);
                break;
            case "D":
                String[] d = info.split(" \\| ", 3);
                boolean dCompleted = (d[0].trim() != "");
                String dName = d[1];
                String dDate = d[2];
                try {
                    LocalDate deadline = LocalDate.parse(dDate, STORAGEFORMAT);
                    t = new Deadline(dName, deadline, dCompleted);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                String[] e = info.split(" \\| ", 4);
                boolean eCompleted = (e[0].trim() != "");
                String eName = e[1];
                String eFrom = e[2];
                String eTo = e[3];
                try {
                    LocalDate from = LocalDate.parse(eFrom, STORAGEFORMAT);
                    LocalDate to = LocalDate.parse(eTo, STORAGEFORMAT);
                    t = new Event(eName, from, to, eCompleted);
                } catch (DateTimeParseException f) {
                    System.out.println(f.getMessage());
                }
                break;
        }
        return t;
    }
}
