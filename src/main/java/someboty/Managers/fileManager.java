package someboty.Managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import someboty.Tasks.Deadline;
import someboty.Tasks.Event;
import someboty.Tasks.Task;
import someboty.Tasks.ToDo;


/**
 * fileManager handles the fetching and storing of a task list
 * to/from a "tasks.csv" file.
 * 
 * Each task is saved as a row in the file in the following format:
 * task type:           E, D, T         [Event, Deadline, ToDo respectively]
 * completion status:   0, 1            [1 if completed, else 0]
 * task description:    String of words
 * deadline:                            [only for tasks of type D]
 * start date:                          [only for tasks of type E]
 * end date:                            [only for tasks of type E]
 */
public class FileManager {

    private String filePath;

    /**
     * Constructor for fileManager
     * @param filePath Path to find the "data/tasks.csv" file.
     */
    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read saved tasks from the csv file and converts it into a list.
     * @return A list of tasks.
     */
    public ArrayList<Task> fetchTasks() {
        Scanner scanner;
        Task currentTask;
        ArrayList<Task> taskList = new ArrayList<>();
        File infile = new File(this.filePath 
                    + File.separator + "data"
                    + File.separator + "tasks.csv");

        try {
            // Creates new directory if it does not exist.
            new File(filePath + File.separator + "data").mkdir(); 
            
            // Creates new file if it does not exist.
            infile.createNewFile(); 

            scanner = new Scanner(infile);
            
        } catch (IOException e) { // not supposed to happen.
            e.printStackTrace();
            return null;
        }

        while (scanner.hasNextLine()) {
            currentTask = lineToTask(scanner.nextLine());
            taskList.add(currentTask);
        }

        scanner.close();
        return taskList;
    }

    /**
     * Takes in a list of task and overwrites it into the tasks.csv file.
     * Note: This method does not append to the file, but overwrites it instead.
     * @param taskList A list of tasks to be saved into file.
     */
    public boolean storeTasks(ArrayList<Task> taskList) {
        FileWriter outfile;

        try { // create/open tasks.csv file.
            outfile = new FileWriter(this.filePath
                        + File.separator + "data"
                        + File.separator + "tasks.csv");

        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to create fileWriter.");
            e.printStackTrace();
            return false;
        }

        try { // write all the tasks in the task list into the csv file.
            for (Task task: taskList) {
                outfile.write(task.toCSV());
            }

        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to save tasks to file.");
            e.printStackTrace();
        }

        try { // close the csv file after writing.
            outfile.flush();
            outfile.close();
            
        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to close file.");
            e.printStackTrace();
            return false;
        }

        return true;
        
    }

    /**
     * Parses a row in the csv file into a task.
     * @param line A row in the csv file.
     * @return A task object created from the descriptions parsed.
     */
    private static Task lineToTask(String line) {
        Task task = null;
        String[] details = line.split(",");

        // every row in the file should follow 
        assert details.length < 3 : "FileManager::lineToTask. A line in the file is broken.";

        String taskType = details[0];
        boolean isCompleted = details[1].equals("1");
        String description = details[2];
        
        switch(taskType) {
            
        case "T":
            task = new ToDo(description);
            break;
        
        case "D":
            String deadline = details[3];
            task = new Deadline(description, deadline);
            break;

        case "E":
            String from = details[3];
            String to = details[4];
            task = new Event(description, from, to);
            break;
        }

        task.setStatus(isCompleted);
        return task;
    }
}
