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
 * A class to handle fetching and storing of task list
 * to/from the "tasks.csv" file.
 * 
 * Each task is saved in the following format:
 * task type:           E, D, T         [Event, Deadline, ToDo respectively]
 * completion status:   0, 1            [1 if completed, else 0]
 * task description:    String of words
 * deadline:                            [only for tasks of type D]
 * start date:                          [only for tasks of type E]
 * end date:                            [only for tasks of type E]
 */
public class fileManager {

    private String filePath;

    public fileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read task details from the csv file and
     * converts it into an array.
     * @return an array of type Task.
     */
    public ArrayList<Task> fetchTasks() {
        Scanner scanner;
        Task currentTask;
        ArrayList<Task> taskList = new ArrayList<>();
        File infile = new File(this.filePath 
                    + File.separator + "data"
                    + File.separator + "tasks.csv");

        try {
            // Creates new directory if does not exist.
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
     * Takes in a list of task and writes it into the csv file.
     * @param taskList an array of type Task.
     */
    public void storeTasks(ArrayList<Task> taskList) {
        FileWriter outfile;
        try {
            outfile = new FileWriter(this.filePath
                        + File.separator + "data"
                        + File.separator + "tasks.csv");

        } catch (IOException e) { // honestly idk what to do about this exception
            System.out.println("Error. Unable to create fileWriter.");
            e.printStackTrace();
            return;
        }

        try {
            for (Task task: taskList) {
                outfile.write(task.toCSV());
            }

        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to save tasks to file.");
            e.printStackTrace();
        }

        System.out.println("Task list successfully saved to file.");
        try {
            outfile.flush();
            outfile.close();

        } catch (IOException e) { // for devs debugging
            System.out.println("Error. Unable to close file.");
            e.printStackTrace();
            return;
        }
        
    }

    private static Task lineToTask(String line) {
        Task task = null;
        String[] details = line.split(",");

        String taskType = details[0];
        boolean status = details[1].equals("1");
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

        task.setStatus(status);
        return task;
    }
}
