package jade.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import jade.data.Deadline;
import jade.data.Event;
import jade.data.RecurringTask;
import jade.data.Task;
import jade.data.TaskList;
import jade.data.Todo;
import jade.exception.JadeException;
import jade.parser.Parser;

/**
 * The <code>Storage</code> object for loading user tasks from local file
 * and saving changes to the same file after program exits.
 */
public class Storage {
    private static final String DIR_NOT_EXIT_MSG = "Dir does not exist";
    private static final String FILE_NOT_EXIT_MSG = "File does not exist";
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
        createJadeDir(jadeDirStr);
        String jadeFileDirStr = System.getProperty("user.dir") + "/" + filePath;
        File jadeFile = createJadeFile(jadeFileDirStr);
        return readFromLocal(jadeFile);
    }
    /**
     * Creates directory for saving the local file if not exists.
     */
    private void createJadeDir(String dirStr) throws JadeException {
        File jadeDir = new File(dirStr);
        if (!jadeDir.exists()) {
            jadeDir.mkdir();
            throw new JadeException(DIR_NOT_EXIT_MSG);
        }
    }
    /**
     * Creates the local file for saving user tasks if not exists.
     */
    private File createJadeFile(String fileDirStr) throws JadeException {
        try {
            File jadeFile = new File(fileDirStr);
            if (!jadeFile.exists()) {
                jadeFile.createNewFile();
                throw new JadeException(FILE_NOT_EXIT_MSG);
            }
            return jadeFile;
        } catch (IOException e) {
            throw new JadeException(e.getMessage());
        }
    }
    /**
     * Reads all strings and add tasks from local file which saves all user tasks.
     */
    private List<Task> readFromLocal(File file) throws JadeException {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> savedTaskList = new ArrayList<>();
            while (sc.hasNext()) {
                addTask(savedTaskList, sc.nextLine());
            }
            sc.close();
            return savedTaskList;
        } catch (IOException e) {
            throw new JadeException(e.getMessage());
        }
    }
    /**
     * Adds all local saved task to the task list.
     */
    private void addTask(ArrayList<Task> taskList, String line) throws JadeException {
        String[] task = line.split(" \\| ");
        assert task.length > 0 : "Saved task strings should not be empty.";
        // local tasks indicate done status in binary
        boolean isDone = task[1].equals("1");
        switch (task[0]) {
        case "T":
            taskList.add(new Todo(task[2], isDone));
            break;
        case "D":
            taskList.add(new Deadline(task[2], Parser.parseDateTime(task[3], "MMM d uuuu hh:mm a"), isDone));
            break;
        case "E":
            String[] dateTimes = task[3].split(" - ");
            taskList.add(new Event(task[2], Parser.parseDateTime(dateTimes[0], "MMM d uuuu hh:mm a"),
                    Parser.parseDateTime(dateTimes[1], "MMM d uuuu hh:mm a"), isDone));
            break;
        case "RT":
            String[] dates = task[3].split(" - ");
            String[] times = task[4].split(" - ");
            taskList.add(new RecurringTask(task[2],
                    Parser.parseDate(dates[0], "MMM d uuuu"),
                    Parser.parseDate(dates[1], "MMM d uuuu"),
                    Parser.parseTime(times[0]),
                    Parser.parseTime(times[1]),
                    RecurringTask.LOOK_UP_TABLE.get(task[5]), isDone));
            break;
        default:
            break;
        }
    }
    /**
     * Modifies the local file to store updated user tasks.
     *
     * @param taskList The list of updated user tasks.
     * @throws JadeException If IOException is caught
     */
    public void saveChange(TaskList<Task> taskList) throws JadeException {
        try {
            Path dataFilePath = java.nio.file.Paths.get(System.getProperty("user.dir"), filePath.split("/"));
            File jadeList = new File(dataFilePath.toString());
            FileWriter jadeListWriter = new FileWriter(jadeList);
            jadeListWriter.write(taskList.listFormatter());
            jadeListWriter.close();
        } catch (IOException e) {
            throw new JadeException("IO Exception");
        }
    }

}
