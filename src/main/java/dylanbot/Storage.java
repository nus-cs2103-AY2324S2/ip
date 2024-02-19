package dylanbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

// deals with loading tasks from the file and saving tasks in the file

/**
 * Represents a Storage object that handles loading and saving of tasks to/from a file
 */
public class Storage {
    private final String filePath;

    private final Ui ui;

    /**
     * Creates a Storage object that reads/writes to the file at the specified file path
     *
     * @param filePath The file path of the desired file
     * @param ui The Ui to be used
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Loads and processes data from a specified file, returning it as an ArrayList of Tasks
     *
     * @return ArrayList of Tasks
     * @throws IOException If file cannot be found at the specified file path
     */
    public ArrayList<Task> loadDataFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String nextLine;
        ArrayList<Task> res = new ArrayList<>();
        try {
            while ((nextLine = reader.readLine()) != null) {
                String[] tokens = nextLine.split(Pattern.quote(" | "));
                assert tokens.length > 0 : "Saved data is empty";
                Task curr;
                if (tokens[0].equals("T")) {
                    curr = new TodoTask(tokens[2]);
                } else if (tokens[0].equals("D")) {
                    curr = new DeadlineTask(tokens[2], convertStringToDateTime(tokens[3]));
                } else {
                    curr = new EventTask(tokens[2], convertStringToDateTime(tokens[3]),
                            convertStringToDateTime(tokens[4]));
                }
                if (tokens[1].equals("true")) {
                    curr.mark();
                }
                res.add(curr);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     * Writes data to a specified file based on the provided ArrayList of Tasks
     *
     * @param tl The data to be saved
     * @throws IOException If file path provided is invalid for a file to be created at
     */
    public void saveDataToFile(TaskList tl) throws IOException {
        ArrayList<Task> tasks = tl.getTasks();
        File newFile = new File(filePath);
        boolean isDirectoryCreated = newFile.getParentFile().mkdirs();
        assert isDirectoryCreated : "Directory should be created if needed";
        boolean isFileCreated = newFile.createNewFile();
        assert isFileCreated : "New file should be created";

        FileWriter writer = new FileWriter(filePath);
        for (Task t : tasks) {
            String data = "";
            data += t.getType() + " | " + t.checkCompleted() + " | " + t.getDesc();
            if (t.getType().equals("D")) {
                data += " | " + ((DeadlineTask) t).getDeadline();
            } else if (t.getType().equals("E")) {
                data += " | " + ((EventTask) t).getFrom() + " | " + ((EventTask) t).getTo();
            }
            writer.write(data);
            writer.write("\n");
        }
        writer.close();
    }

    /**
     * Converts an input String to LocalDateTime and returns it
     *
     * @param input String to be converted
     * @return The converted String as a LocalDateTime
     */
    public static LocalDateTime convertStringToDateTime(String input) {
        LocalDateTime deadline;
        if (input.length() < 11) {
            deadline = LocalDate.parse(input).atStartOfDay();
        } else {
            deadline = LocalDateTime.parse(input);
        }
        return deadline;
    }
}

