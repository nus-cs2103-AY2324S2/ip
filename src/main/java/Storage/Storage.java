package Storage;

import Commands.Command;
import Parser.Parser;
import TaskList.Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import TaskList.TaskList;

public class Storage {
    private final String filePath = "./cache.txt";
    public Storage() {
    }
    public TaskList load(TaskList cachedTasks) throws IOException {
        File f = new File(this.filePath); // create a File for the given file path
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
    public void save(TaskList cachedTasks) throws IOException {
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
