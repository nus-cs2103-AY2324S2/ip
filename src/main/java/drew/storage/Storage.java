package drew.storage;

import drew.task.Deadline;
import drew.task.Event;
import drew.task.Task;
import drew.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the Drew. It contains the methods to save and load files.
 */
public class Storage {
    /**
     * Contains the file path that the storage will read and write from.
     */
    protected String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Saves the task list into a txt file, with a standard format.
     * @param ls ArrayList containing the tasks.
     * @return Boolean value indicating whether the save was successful.
     */
    public boolean save(ArrayList<Task> ls) {
        File savedTasks = new File(filePath);
        try {
            //Creates missing directories and files
            boolean mkdirSuccess = savedTasks.getParentFile().mkdirs();
            boolean createFileSuccess = savedTasks.createNewFile();

            //For debug
            System.out.println("Directories created: " + mkdirSuccess);
            System.out.println("File created: " + createFileSuccess);

            //File write happens here
            FileWriter fw = new FileWriter(filePath);
            int listLength = ls.size();
            for (int i = 0; i < listLength; i++) {
                String task = ls.get(i).toSaveFormatString();
                fw.write(task);
            }
            fw.close();
            System.out.println("Save successful!");
            return true;
        } catch (SecurityException e) {
            System.out.println("Save error: User does not have the correct privilege.");
            return false;
        } catch (IOException e) {
            System.out.println("Save error: IO method fault.");
            return false;
        }
    }

    /**
     * Loads the task list from a txt file.
     * @param ls Arraylist that will store the loaded tasks.
     * @return Boolean value that indicates whether load was successful.
     */
    public boolean load(ArrayList<Task> ls) {
        File savedTasks = new File(filePath);
        // Debug
//        System.out.println(savedTasks.getAbsolutePath());

        try {
            Scanner fileReader = new Scanner(savedTasks);
            System.out.println("Load status: File found");
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
//                System.out.println(line);
                Task task = parseSave(line);
//                System.out.println(task.toStatusString());
                ls.add(task);
            }
            return true;
        } catch (FileNotFoundException e){
            System.out.println("Load status: File not found");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Converts the input string into a task object.
     * @param input Encoded string that contains saved task details.
     * @return Task object that corressponds to the input.
     * @throws IllegalArgumentException when input arguments are invalid and cannot be read.
     */
    public Task parseSave(String input) throws IllegalArgumentException {
        //Splits input string into components
        String[] args = input.split("\\|");
        int numberOfArguments = args.length;

        //Removes whitespace from save string. Whitespace was used to increase readability of save txt file.
        for (int i = 0; i < numberOfArguments; i ++) {
            args[i] = args[i].trim();
        }

        Task task = null;
        switch (args[0]) {
        case "T":
            if (numberOfArguments != 3) {
                throw new IllegalArgumentException("Load Error: File Corrupted");
            }
            task = new Todo(args[2]);
            break;
        case "D":
            if (numberOfArguments != 4) {
                throw new IllegalArgumentException("Load Error: File Corrupted");
            }
            task = new Deadline(args[2], LocalDate.parse(args[3]));
            break;
        case "E":
            if (numberOfArguments != 5) {
                throw new IllegalArgumentException("Load Error: File Corrupted");
            }
            task = new Event(args[2], LocalDate.parse(args[3]), LocalDate.parse(args[4]));
            break;
        default:
            throw new IllegalArgumentException("Load Error: Unknown task type.");
        }

        if (args[1] == "1") {
            task.setDone();
        } else if (args[1] != "0") {
            throw new IllegalArgumentException("Load Error: File Corrupted");
        }

        return task;
    }
}
