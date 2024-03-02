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

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the tasks in storage to the list
     * @param list
     * @throws InvalidDateException
     */
    public void load(TaskList list) throws InvalidDateException {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String next = s.nextLine();
                list.addTaskInit(Task.parseTask(next));
            }
        } catch (IOException e) {
            System.out.println("Something Went Wrong!");
        }
    }

    /**
     * Saves the tasks in list to storage
     * @param list
     * @throws IOException
     */
    public void save(TaskList list) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
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
