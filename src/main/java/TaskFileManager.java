import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TaskFileManager class that encapsulates the saving of data onto the local
 * hard drive.
 */
public class TaskFileManager {

    /** Filepath of where to save to local disk for TASKS */
    private static final String FILEPATH = "src/main/java/temp/output.txt";

    /**
     * Constructor for TaskFileManager.
     */
    public TaskFileManager() {
    }

    /**
     * Implementation to save tasks to file
     * 
     * @param tasks Tasks to be saved to the local drive.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (Task t : tasks) {
            String taskToStore = t.convertToFormat();
            fw.write(taskToStore + "\n");
        }
        fw.close();
    }

    /**
     * Implementation to load tasks from local drive.
     * 
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
            String[] taskSegments = sc.nextLine().split(" | ", 2);
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
                t = new ToDo(info);
                break;
            case "D":
                String[] d = info.split(" | ", 2);
                String dName = d[0];
                String dDate = d[1];
                t = new Deadline(dName, dDate);
            case "E":
                String[] e = info.split(" | ", 3);
                String eName = e[0];
                String eFrom = e[1];
                String eTo = e[2];
                t = new Event(eName, eFrom, eTo);
        }
        return t;
    }
}
