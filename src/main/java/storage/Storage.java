package storage;

import commands.Command;
import parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import TaskList.TaskList;

/**
 * Represents a storage to store and load tasks from a file.
 * A <code>Storage</code> object corresponds to a storage with a file path
 * e.g., <code>"./cache.txt"</code>
 */
public class Storage {
    private final String filePath = "./cache.txt";
    public Storage() {
    }

    /**
     * Loads tasks from the file.
     * @param cachedTasks the task list to be loaded with tasks from the file
     * @return the task list loaded with tasks from the file
     * @throws IOException if an I/O error occurs
     */
    public TaskList loadCache(TaskList cachedTasks) throws IOException {
        File f = new File(this.filePath); // create a File for the given file path
        assert f.exists() && !f.isDirectory() : "Cache file does not exist or is a directory.";
        if (f.exists() && !f.isDirectory()) {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            Parser parser = new Parser();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] str = line.split(" ");
                switch (str[0]) {
                    case "completed":
                        for (int i = 1; i < str.length; i++) {
                            cachedTasks.getTask(Integer.parseInt(str[i])).markCompleted();
                        }
                        break;
                    default:
                        Command command = parser.parseCommand(line);
                        command.setData(cachedTasks);
                        command.execute();
                }
            }
        }
        return cachedTasks;
    }

    /**
     * Saves tasks to the file.
     * @param cachedTasks the task list to be saved to the file
     * @throws IOException if an I/O error occurs
     */
    public void saveToCache(TaskList cachedTasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < cachedTasks.size(); i++) {
            fw.write(cachedTasks.getTask(i).save()+ "\n");
        }
        fw.write("completed");
        for (int i = 0; i < cachedTasks.size(); i++) {
            if (cachedTasks.getTask(i).isCompleted()) {
                fw.write(" " + i);
            }
        }
        fw.close();
    }
}
