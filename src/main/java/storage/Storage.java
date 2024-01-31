package storage;

import exceptions.DukeException;
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
 * Encapsulates the storage of Howie where data are retrieved and stored into user's local file.
 */
public class Storage {

    public static class InvalidFilePathException extends DukeException {
        public InvalidFilePathException(String message) {
            super(message);
        }
    }

    public static class InvalidFormatException extends DukeException {
        public InvalidFormatException(String message) {
            super(message);
        }
    }

    public static final String FILEPATH = "data/data.txt";

    public final Path path;

    /**
     * Constructor for storage.
     * @throws InvalidFilePathException
     */
    public Storage() throws InvalidFilePathException {
        this.path = Paths.get(FILEPATH);
        if (!path.toString().endsWith(".txt")) {
            throw new InvalidFilePathException("Storage must end with '.txt'");
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
     * Read and populate list of tasks from user's data.
     * @return TaskList
     * @throws DukeException Throws exception when input format is wrong, or file is corrupted.
     */
    public TaskList readFile() throws DukeException {
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
                        if (input[1].equals("1")) {
                            t.setDone();
                        }
                        tasks.add(t);
                    } else if (input[0].equals("D")) {
                        Deadline d = new Deadline(input[2],input[3]);
                        if (input[1].equals("1")) {
                            d.setDone();
                        }
                        tasks.add(d);
                    } else {
                        Event e = new Event(input[2], input[3], input[4]);
                        if (input[1].equals("1")) {
                            e.setDone();
                        }
                        tasks.add(e);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("I/O error detected...");
        }
    }

    /**
     * Saves files into users local hard disk.
     * @param taskLs TaskList that stores list of tasks.
     * @throws DukeException Throws Exception when
     * @throws IOException Throws IOException when file is not found.
     */
    public void saveFile(TaskList taskLs) throws DukeException, IOException {
        List<Task> tasks = taskLs.getList();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()));
            for (Task t : tasks) {
                bw.write(t.encode());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
           Files.createDirectories(Paths.get("data"));
           saveFile(taskLs);
        } catch (IOException e) {
            throw new DukeException("I/O Exception Detected");
        }
    }

}

