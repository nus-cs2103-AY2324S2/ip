package drew.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import drew.task.Deadline;
import drew.task.Event;
import drew.task.Task;
import drew.task.Todo;


/**
 * Represents the storage of the drew.Drew. It contains the methods to save and load files.
 */
public class Storage {
    /**
     * Contains the file path that the storage will read and write from.
     */
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list into a txt file, with a standard format.
     * @param list TaskList object containing the tasks.
     * @return Boolean value indicating whether the save was successful.
     */
    public boolean save(TaskList list) {
        assert !list.equals(null);
        ArrayList<Task> ls = list.getList();
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
     * @return ArrayList that contains the list of tasks read.
     */
    public ArrayList<Task> load() throws FileNotFoundException,
            IllegalArgumentException, DateTimeParseException {

        File savedTasks = new File(filePath);
        ArrayList<Task> ls = new ArrayList<>();

        Scanner fileReader = new Scanner(savedTasks);
        System.out.println("Load status: File found");
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            Task task = parseSave(line);
            ls.add(task);
        }
        return ls;
    }

    /**
     * Converts the input string into a task object.
     * @param input Encoded string that contains saved task details.
     * @return Task object that corressponds to the input.
     * @throws IllegalArgumentException when input arguments are invalid and cannot be read.
     */
    public Task parseSave(String input) throws IllegalArgumentException, DateTimeParseException {
        //Splits input string into components
        String[] args = input.split("\\|");
        int numberOfArguments = args.length;

        //Removes whitespace from save string. Whitespace was used to increase readability of save txt file.
        for (int i = 0; i < numberOfArguments; i++) {
            args[i] = args[i].trim();
        }

        Task task;
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

        if (args[1].equals("1")) {
            task.setDone();
        } else if (!args[1].equals("0")) {
            throw new IllegalArgumentException("Load Error: File Corrupted");
        }
        return task;
    }
}
