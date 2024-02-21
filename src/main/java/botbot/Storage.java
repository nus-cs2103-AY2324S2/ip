package botbot;

import botbot.exception.InvalidDateException;
import botbot.task.Task;
import botbot.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String path;
    private final File file;
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * Loads the tasks in storage to the list
     * @param list
     * @throws IOException
     * @throws InvalidDateException
     */
    public void load(TaskList list) throws IOException, InvalidDateException {
        boolean listCreated = file.createNewFile();
        if (!listCreated) {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String next = s.nextLine();
                list.addTaskInit(Task.parseTask(next));
            }
        }
    }

    /**
     * Saves the tasks in list to storage
     * @param list
     * @throws IOException
     */
    public void save(TaskList list) throws IOException {
        if (file.delete()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(path);

        try {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.getFileRep(i));
                fw.write(list.getFileRep(i));
                if (i != list.size() - 1) {
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
