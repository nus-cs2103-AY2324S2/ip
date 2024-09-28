package chad.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chad.exceptions.ChadException;
import chad.task.Task;

/**
 * Stores task list into files and reads task list from files to load into chad.
 */
public class Storage {
    private String archivePath = "data/archive.txt";
    private String storePath;
    public Storage(String storePath) {
        this.storePath = storePath;
    }

    /**
     * Stores the current task list into the filepath.
     *
     * @param list the task list
     * @throws IOException if there is a problem with the file.
     */
    public void storeTaskList(ArrayList<Task> list) throws IOException {
        File taskList = new File(this.storePath);
        createFile(taskList);
        FileWriter fw = new FileWriter(taskList);
        writeNumOfTask(fw, list);
        writeList(fw, list);
        fw.close();
    }

    /**
     * Loads the task list in the file to Chad.
     *
     * @return Task list.
     * @throws IOException if there is a problem with the file.
     * @throws ChadException if the task list is of the wrong format.
     */
    public ArrayList<Task> load() throws IOException, ChadException {
        File taskList = new File(storePath);
        createFile(taskList);
        Scanner sc = new Scanner(taskList);
        ArrayList<Task> list = new ArrayList<>();
        loadList(sc, list);
        return list;
    }

    /**
     * Archives the current TaskList into data/archive.txt.
     *
     * @param list The current TaskList
     * @throws IOException if there is a problem with the file.
     */
    public void archiveTaskList(TaskList list) throws IOException {
        File archiveFile = new File(archivePath);
        createFile(archiveFile);
        FileWriter fw = new FileWriter(archiveFile);
        writeNumOfTask(fw, list.getList());
        writeList(fw, list.getList());
        fw.close();
    }

    private void createFile(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private void writeList(FileWriter fw, ArrayList<Task> list) throws IOException {
        for (Task t : list) {
            fw.append(t.toString() + "\n");
        }
    }

    private void writeNumOfTask(FileWriter fw, ArrayList<Task> list) throws IOException {
        if (list.size() == 1) {
            fw.write("There is 1 task in the list.\n");
        } else if (list.isEmpty()) {
            fw.write("The list is empty.\n");
        } else {
            fw.write("There are " + list.size() + " tasks in the list.\n");
        }
    }

    private void loadList(Scanner sc, ArrayList<Task> list) throws ChadException {
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == "\n") {
                break;
            }
            Task t = Parser.parseFile(line);
            list.add(t);
        }
    }
}
