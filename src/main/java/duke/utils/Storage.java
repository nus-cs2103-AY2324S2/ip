package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import duke.exceptions.TaskCreationException;


/**
 * This class implements the saving and reading of bot data into a text file.
 * 
 * @author delishad21
 */
public class Storage {
    private File f;
    
    /**
     * Constructor for a storage object, takes in a filepath for the file in which data
     * should be saved.
     * 
     * @param filePath File to save data to and load data from.
     * @throws IOException 
     */
    public Storage(String filePath) throws IOException {
        this.f = new File(filePath);
        checkAndCreateFile();
    }

    /**
     * Method for checking and creating file based on filepath.
     * 
     * @throws IOException
     */
    private void checkAndCreateFile() throws IOException {
        // Reading and creating data save file
        // making data folder
        if (!f.getParentFile().exists()) {
            if (!f.getParentFile().mkdir()) {
                throw new IOException("Unable to make directory");
            };
        }

        // making data file
        if (!f.exists()) {
            f.createNewFile();
         }
    }

    /**
     * Method for reading save data from save file.
     * 
     * @param ui Used for printing information.
     * @return TaskList read from save data.
     * @throws FileNotFoundException
     */
    public TaskList readSaveData(Ui ui) throws FileNotFoundException{
        TaskList taskList = new TaskList();

        Scanner s = new Scanner(f);
        int count = 0;
        while (s.hasNext()) {
            try {
                taskList.add(parseTaskFromSave(s.nextLine()));
                count++;
            } catch (TaskCreationException e) {
                System.out.println("Error in reading task: " + e.getMessage());
            }
        }
        ui.botPrint(count + " tasks loaded from save");
        s.close();

        return taskList;

    }

    /**
     * Method for saving data from bot back to save file.
     * 
     * @param data The data from the bot.
     * @param ui Used for printing information.
     * @throws IOException
     */
    public void saveTodoData(TaskList data, Ui ui) throws IOException {
        this.checkAndCreateFile();

        FileWriter fw = new FileWriter(f);
        
        String dataString = "";
        
        for (int i = 1; i <= data.size(); i++) {
            dataString = dataString + data.get(i).toSave() + "\n";
        }
        
        fw.write(dataString);
        fw.close();

        ui.botPrint(data.size() + " tasks saved");
    }

    /**
     * Method for individually parsing each line from save file into Tasks.
     * 
     * @param task Each line read from save file.
     * @return A Task object generated with information parsed from input.
     * @throws TaskCreationException
     */

    private Task parseTaskFromSave(String task) throws TaskCreationException {
        String[] taskSplit = task.split("\\|");
        boolean isDone;
        if (taskSplit[1].equals("[X]")) {
            isDone = true;
        } else if (taskSplit[1].equals("[ ]")) {
            isDone = false;
        } else {
            throw new TaskCreationException("Unable to determine if task (" + task + ") is done or not");
        }
        switch (taskSplit[0]) {
        case "[T]":
            return new Todo(isDone, taskSplit[2]);
        case "[D]":
            return new Deadline(isDone, taskSplit[2], 
                                LocalDateTime.parse(taskSplit[3], Parser.INPUT_DT_FORMATTER));
        case "[E]":        
            return new Event(isDone, taskSplit[2],  
                             LocalDateTime.parse(taskSplit[3], Parser.INPUT_DT_FORMATTER),  
                             LocalDateTime.parse(taskSplit[4], Parser.INPUT_DT_FORMATTER));
        default:
            throw new TaskCreationException("No such task: " + taskSplit[0] + " for " + task);
        }
    }

}
