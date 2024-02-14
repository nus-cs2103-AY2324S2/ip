package duke.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Stores task list into files and reads task list from files to load into Duke.
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Stores the current task list into the filepath.
     *
     * @param list the task list
     * @throws IOException if there is a problem with the file.
     */
    public void storeTaskList(ArrayList<Task> list) throws IOException {
        File taskList = new File(filePath);

        if (!taskList.getParentFile().exists()) {
            taskList.getParentFile().mkdirs();
        }
        if (!taskList.exists()) {
            taskList.createNewFile();
        }

        FileWriter fw = new FileWriter(taskList);

        if (list.size() == 1) {
            fw.write("There is 1 task in the list.\n");
        } else {
            fw.write("There are " + list.size() + " tasks in the list.\n");
        }

        for (Task t : list) {
            fw.append(t.toString() + "\n");
        }
        fw.close();
    }

    /**
     * Loads the task list in the file to Duke.
     *
     * @return Task list.
     * @throws IOException if there is a problem with the file.
     * @throws DukeException if the task list is of the wrong format.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        File taskListFile = new File(filePath);

        if (!taskListFile.getParentFile().exists()) {
            taskListFile.getParentFile().mkdirs();
        }
        if (!taskListFile.exists()) {
            taskListFile.createNewFile();
        }

        Scanner sc = new Scanner(taskListFile);
        if (sc.hasNextLine()) {
            sc.nextLine();
        }

        ArrayList<Task> list = new ArrayList<Task>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == "\n") {
                break;
            }
            Task t = Parser.parseFile(line);
            list.add(t);
        }
        return list;
    }
}
