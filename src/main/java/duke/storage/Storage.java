package duke.storage;

import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected Parser parser;
    
    public Storage(String filePath, Parser parser) {
        this.filePath = filePath;
        this.parser = parser;
    }
    
    public Parser getParser() {
        return this.parser;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    public TaskList loadFile(TaskList list) throws IOException {
        String filePath = this.getFilePath();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String taskContent = sc.nextLine();
            Task t = this.getParser().parseStringToTask(taskContent);
            list.add(t);
        }
        return list;
    }
    
    public void addTaskToFile(Task task) throws IOException{
        FileWriter fw = new FileWriter(this.getFilePath(), true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }
    
    public void writeToFile(String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(this.getFilePath());
        fw.write(textToAdd);
        fw.close();
    }
    
    public void changeFileContent(TaskList list) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        for (Task t: list.getList()) {
            contentBuilder.append(t.toString()).append(System.lineSeparator());
        }
        writeToFile(contentBuilder.toString());
    }
}
