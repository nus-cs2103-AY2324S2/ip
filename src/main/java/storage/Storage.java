package storage;

import exceptions.HowieException;
import task.Deadline;
import task.Event;
import task.Task;
import tasklists.TaskList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

/**
 * Storage class is responsible for fetching/saving data from/to local storage.
 */
public class Storage {

    public static final String FILEPATH = "data/data.txt";
    public final Path path;

    /**
     * Constructor for Storage.
     * @throws InvalidFileException Throws the exception when file is not txt document.
     */
    public Storage() throws InvalidFileException {
        this.path = Paths.get(FILEPATH);
        checkIfTextFile();
    }

    private void checkIfTextFile() throws InvalidFileException {
        if (!path.toString().endsWith(".txt")) {
            throw new InvalidFileException("Storage must end with '.txt'");
        }
    }

    private boolean checkFormat(String[] input) {
        if (input[0].equals("T")) {
            return input.length == 3;
        } else if (input[0].equals("D")) {
            return input.length == 4;
        } else if (input[0].equals("E")) {
            return input.length == 5;
        } else {
            return false;
        }
    }

    /**
     * Reads the file and encoded input from local storage.
     * @return A task list containing tasks in storage.
     * @throws HowieException Throws an exception when I/O error is detected.
     */
    public TaskList readFile() throws HowieException {
        if (!Files.exists(path)) {
            return new TaskList();
        }
        try {
            List<String> lines = Files.readAllLines(path);
            TaskList tasks = new TaskList();
            for (int i=0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] input = line.split("\\|");
                if (!checkFormat(input)) {
                    throw new InvalidFormatException("Invalid format detected at line: " + i);
                } else {
                    if (input[0].equals("T")) {
                        Task t = new Task(input[2]);
                        checkAndSetTaskStatus(input, t);
                        tasks.add(t);
                    } else if (input[0].equals("D")) {
                        Deadline d = new Deadline(input[2],input[3]);
                        checkAndSetTaskStatus(input, d);
                        tasks.add(d);
                    } else {
                        Event e = new Event(input[2], input[3], input[4]);
                        checkAndSetTaskStatus(input, e);
                        tasks.add(e);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new HowieException("I/O error detected...");
        }
    }

    private static void checkAndSetTaskStatus(String[] input, Task t) {
        if (input[1].equals("1")) {
            t.setDone();
        }
    }

    /**
     * Saves all tasks into local storage.
     * @param taskLs Task list containing all tasks.
     * @throws IOException Throws an error when I/O error is detected.
     */
    public void saveFile(TaskList taskLs) throws IOException {
        List<Task> tasks = taskLs.getList();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()));
            for (Task t : tasks) {
                bw.write(t.encode());
                bw.newLine();
            }
           bw.flush();
           bw.close();
        } catch (FileNotFoundException e) { //file not found
           Files.createDirectories(Paths.get("data")); //create file
           saveFile(taskLs); //run function again
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * An exception class encapsulating a file error.
     */
    public static class InvalidFileException extends HowieException {
        public InvalidFileException(String message) {
            super(message);
        }
    }

    /**
     * An exception class for invalid format found in local data.
     */
    public static class InvalidFormatException extends HowieException {
        public InvalidFormatException(String message) {
            super(message);
        }
    }

}

