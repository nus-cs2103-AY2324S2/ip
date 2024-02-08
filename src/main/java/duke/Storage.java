package duke;

import exceptions.DukeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Saves the tasks in a hard-coded file and loads up the tasks from the hard-coded file upon restarting the program.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a String of tasks to be written to the hard-coded file.
     * @param tl
     * @return String listItems
     */
    public String storeList(TaskList tl) {
        StringBuilder listItems = new StringBuilder();
        for (int j = 0; j < tl.size(); j++) {
            if (j > 0) {
                listItems.append(System.getProperty("line.separator")); // Add newline before each task except the first one
            }
            String task = tl.get(j).simpleToString();
            listItems.append(task);
        }
        return listItems.toString();
    }

    /**
     * Writes the list of items to the hard-coded file.
     * @param ls list of items
     * @throws DukeException if there is an error when writing to the file
     */
    public void save(String ls) throws DukeException {
        Path path = Paths.get(this.filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path); // Create the file if it doesn't exist
            }
            Files.writeString(path, ls, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + e.getMessage());
        }
    }


    /**
     * Prints the list from the file upon restarting the program.
     * @throws DukeException when there are errors with the file
     */
    public void printList() throws DukeException {
        System.out.println("Here are the tasks in your list:");
        BufferedReader br = null;
        Path path = Paths.get(this.filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path); // Create the file if it doesn't exist
            }
            File file = new File(this.filePath);
            br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + this.filePath);
        } catch (IOException e) {
            throw new DukeException("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new DukeException("Error closing file reader: " + e.getMessage());
            }
        }
    }

    /**
     * Returns an ArrayList<Task> which represents an array of tasks that is loaded from the file.
     * @return ArrayList<Task> taskArr
     * @throws DukeException where there are errors in the file
     */
    public ArrayList<Task> load() throws DukeException {
        BufferedReader br = null;
        ArrayList<Task> taskArr = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                return taskArr;
            }
            br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null) {
                String[] splitStr = s.split(" \\| ");
                Task task;
                switch(splitStr[0].strip()) {
                    case "T":
                        task = new ToDo(splitStr[2].strip());
                        break;
                    case  "D":
                        LocalDateTime by = LocalDateTime.parse(splitStr[3].strip(), DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                        task = new Deadline(splitStr[2].strip(), by);
                        break;
                    case "E":
                        String[] toFrom = splitStr[3].split("-");
                        task = new Event(splitStr[2].strip(), toFrom[0].strip(), toFrom[1].strip());
                        break;
                    default:
                        throw new DukeException("Sorry! There was an error parsing the file.");
                }
                if (splitStr[1].equals("1")) {
                    task.setDone();
                }
                taskArr.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + this.filePath);
        } catch (IOException e) {
            throw new DukeException("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new DukeException("Error closing file reader: " + e.getMessage());
            }
        }
        return taskArr;
    }
}
