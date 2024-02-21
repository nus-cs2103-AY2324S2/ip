package jojo;

import exceptions.JojoException;

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
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a String of tasks to be written to the hard-coded file.
     * @param tl TaskList
     * @return String listItems
     */
    public String storeList(TaskList tl) {
        StringBuilder listItems = new StringBuilder();
        for (int j = 0; j < tl.size(); j++) {
            if (j > 0) {
                listItems.append(System.lineSeparator()); // Add newline before each task except the first one
            }
            String task = tl.get(j).simpleToString();
            listItems.append(task);
        }
        return listItems.toString();
    }

    /**
     * Writes the list of items to the hard-coded file.
     * @param ls list of items
     * @throws JojoException if there is an error when writing to the file
     */
    public void save(String ls) throws JojoException {
        Path path = Paths.get(this.filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path); // Create the file if it doesn't exist
            }
            Files.writeString(path, ls, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new JojoException("Error writing to file: " + e.getMessage());
        }
    }


    /**
     * Prints the list from the file upon restarting the program.
     * @throws JojoException when there are errors with the file
     */
    public String printList() throws JojoException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(System.lineSeparator());
                sb.append(s);
            }
        } catch (FileNotFoundException e) {
            throw new JojoException("File not found: " + filePath);
        } catch (IOException e) {
            throw new JojoException("Error reading file: " + e.getMessage());
        }
        return sb.toString();
    }


    /**
     * Returns an ArrayList<Task> which represents an array of tasks that is loaded from the file.
     * @return ArrayList<Task> taskArr
     * @throws JojoException where there are errors in the file
     */
    public ArrayList<Task> load() throws JojoException {
        ArrayList<Task> taskArr = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskArr;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s = br.readLine()) != null) {
                String[] splitStr = s.split(" \\| ");
                Task task = loadTaskType(splitStr);
                if (splitStr[1].equals("1")) {
                    task.setDone();
                }
                taskArr.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new JojoException("File not found: " + filePath);
        } catch (IOException e) {
            throw new JojoException("Error reading file: " + e.getMessage());
        }
        return taskArr;
    }

    /**
     * Returns a task of the correct type based on what is saved in the file
     * @param splitStr String[]
     * @return task that is either Todo, Deadline or Event
     * @throws JojoException when the file given is invalid
     */
    public Task loadTaskType(String[] splitStr) throws JojoException {
        Task task;
        switch (splitStr[0].strip()) {
        case "T":
            task = new ToDo(splitStr[2].strip());
            break;
        case "D":
            LocalDateTime by = LocalDateTime.parse(splitStr[3].strip(),
                    DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            task = new Deadline(splitStr[2].strip(), by);
            break;
        case "E":
            String[] toFrom = splitStr[3].split("-");
            task = new Event(splitStr[2].strip(), toFrom[0].strip(), toFrom[1].strip());
            break;
        default:
            throw new JojoException("Sorry! There was an error parsing the file.");
        }
        return task;
    }
}
