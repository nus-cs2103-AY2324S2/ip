package duke.storage;

import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading from and saving tasks to the file system. This class provides functionality
 * to read tasks from a file upon startup and write tasks to the same file when tasks are added
 * or modified.
 */
public class Storage {
    protected String filePath;
    protected Parser parser;
    
    /**
     * Creates an instance of Storage with a specified file path for task data and a parser
     * for converting task data between its textual representation and Task objects.
     *
     * @param filePath The path of the file where task data is stored.
     * @param parser The parser used to interpret and format task data.
     */
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
    
    /**
     * Loads the tasks from the file specified by filePath into the provided TaskList.
     * If the file does not exist, it will be created.
     *
     * @param list The TaskList to which the loaded tasks will be added.
     * @return The updated TaskList with tasks loaded from the file.
     * @throws IOException If an I/O error occurs during reading from the file.
     */
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
    
    /**
     * Adds a task to the task data file. This method appends the task's string representation
     * to the end of the file, ensuring it is saved persistently.
     *
     * @param task The task to be added to the file.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void addTaskToFile(Task task) throws IOException{
        FileWriter fw = new FileWriter(this.getFilePath(), true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }
    
    /**
     * Writes a given string to the task data file, overwriting its current contents.
     * This method is used for updating the entire task list in the file.
     *
     * @param textToAdd The complete string representation of the task list to write to the file.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void writeToFile(String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(this.getFilePath());
        fw.write(textToAdd);
        fw.close();
    }
    
    /**
     * Updates the task data file with the current contents of the TaskList. This method
     * generates the complete string representation of all tasks in the list and writes it
     * to the file, replacing any existing content.
     *
     * @param list The TaskList containing all current tasks.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void changeFileContent(TaskList list) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        for (Task t: list.getList()) {
            contentBuilder.append(t.toString()).append(System.lineSeparator());
        }
        writeToFile(contentBuilder.toString());
    }
}
