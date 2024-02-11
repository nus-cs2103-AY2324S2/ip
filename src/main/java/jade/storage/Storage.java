package jade.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import jade.data.Deadline;
import jade.data.Event;
import jade.data.Task;
import jade.data.TaskList;
import jade.data.Todo;
import jade.exception.JadeException;

/**
 * The <code>Storage</code> object for loading user tasks from local file
 * and save changes to the same file after program exits.
 */
public class Storage {
    private final String filePath; // the file path storing all user tasks

    /**
     * Class constructor specifying the local file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns all saved user tasks in the local file.
     * If the directory or file does not exit, an empty list is returned.
     *
     * @return A list of user tasks.
     * @throws JadeException If directory or file does not exit
     */
    public List<Task> load() throws JadeException {
        String[] dirs = filePath.split("/");
        String jadeDirStr = System.getProperty("user.dir") + "/" + String.join("/", Arrays
                .copyOfRange(dirs, 0, dirs.length - 1));
        try {
            File jadeDir = new File(jadeDirStr);
            if (!jadeDir.exists()) {
                jadeDir.mkdir();
                throw new JadeException("Dir does not exist");
            }
            String jadeFileDirStr = System.getProperty("user.dir") + "/" + filePath;
            File jadeFile = new File(jadeFileDirStr);
            if (!jadeFile.exists()) {
                jadeFile.createNewFile();
                throw new JadeException("File does not exist");
            }
            Scanner sc = new Scanner(jadeFile);
            ArrayList<Task> savedTaskList = new ArrayList<>();
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split(" \\| ");
                assert task.length > 0 : "Saved task strings should not be empty.";
                boolean isDone = task[1].equals("1");
                switch (task[0]) {
                case "T":
                    savedTaskList.add(new Todo(task[2], isDone));
                    break;
                case "D":
                    savedTaskList.add(new Deadline(task[2], LocalDateTime.parse(task[3],
                            DateTimeFormatter.ofPattern("MMM d yyyy hmma")), isDone));
                    break;
                case "E":
                    String[] dateTimes = task[3].split(" - ");
                    savedTaskList.add(new Event(task[2], LocalDateTime.parse(dateTimes[0], DateTimeFormatter
                            .ofPattern("MMM d yyyy hmma")), LocalDateTime.parse(dateTimes[1], DateTimeFormatter
                            .ofPattern("MMM d yyyy hmma")), isDone));
                    break;
                default:
                    assert false : task[0];
                    break;
                }
            }
            sc.close();
            return savedTaskList;
        } catch (IOException e) {
            throw new JadeException("IO Exception");
        }
    }

    /**
     * Modifies the local file to store updated user tasks.
     *
     * @param taskList The list of updated user tasks.
     * @throws JadeException If IOException is caught
     */
    public void saveChange(TaskList taskList) throws JadeException {
        try {
            Path dataFilePath = java.nio.file.Paths.get(System.getProperty("user.dir"), "data", "jadeList.txt");
            File jadeList = new File(dataFilePath.toString());
            FileWriter jadeListWriter = new FileWriter(jadeList);
            jadeListWriter.write(taskList.listFormatter());
            jadeListWriter.close();
        } catch (IOException e) {
            throw new JadeException("IO Exception");
        }
    }

}
