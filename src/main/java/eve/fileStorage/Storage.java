package eve.filestorage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import eve.tasks.Deadline;
import eve.tasks.Event;
import eve.tasks.Task;
import eve.tasks.Todo;


/**
 * Storage class is used to handle the file storage of the tasks,
 * it contains the methods to load the tasks from the file and to write the tasks into the file
 */
public class Storage {
    // private static final String directoryPath = "./data";
    private static final String filePath = "./data/Eve.txt";
    private static final Path path = Paths.get(filePath);

    /**
     * Constructor for the Storage class
     */
    public Storage() {

    }

    /**
     * This method is used to load the tasks from the file
     * The below functions are adapted from CS2103T website Week 3
     */
    public ArrayList<Task> loadFileContents() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            doesFileExist();
            BufferedReader br = Files.newBufferedReader(path);
            while (br.ready()) {
                String temp = br.readLine();
                String[] tempyArr = temp.split(" \\| ");
                Task tempTask = null;

                if (tempyArr[0].equals("T")) {
                    tempTask = new Todo(tempyArr[2], tempyArr[1]);
                    list.add(tempTask);
                } else if (tempyArr[0].equals("D")) {
                    tempTask = new Deadline(tempyArr[2], tempyArr[3], tempyArr[1]);
                    list.add(tempTask);
                } else if (tempyArr[0].equals("E")) {
                    tempTask = new Event(tempyArr[2], tempyArr[3], tempyArr[4], tempyArr[1]);
                    list.add(tempTask);
                } else {
                    //do nth
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    private boolean doesFileExist() throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return true;
        }
        return false;
    }


    /**
     * This method is used to write the tasks into the file
     * @param tasks is the list of tasks
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task: tasks) {
                fw.write(task.toStore());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
