package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.time.LocalDate;

import java.util.Scanner;

import java.io.FileWriter;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Class used to load and store data externally.
 */
public class Storage {
    // Parses file and outputs it as a list
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private String path;

    /**
     * Constructor for Storage.
     * @param path Path to file to be loaded or saved
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Returns tasklist after reading external txt file according to path.
     * 
     * @return taskList based on external txt file
     * @throws FileNotFoundException If path does not lead to the correct file.
     * @throws IOException If the file cannot be created if not found.
     */
    public TaskList load() throws FileNotFoundException, IOException {
        TaskList taskList = new TaskList();
        Task task;
        File file = new File(path);
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File creation failed.");
            }
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // Split by first space

                String parts[] = sc.nextLine().split(" ", 2);
                if (parts[0].equals(TODO)) {
                    parts = parts[1].split(" ", 2);
                    task = new Todo(parts[1], parts[0]);

                } else if (parts[0].equals(DEADLINE)) {
                    parts = parts[1].split(" ", 3);
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    task = new Deadline(parts[2], parts[0], date);

                } else {
                    parts = parts[1].split(" ", 3);
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    task = new Event(parts[2], parts[0], date);

                } 
                taskList.addTask(task);
            
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return taskList;
        }
    }

    /**
     * Saves task list into a txt file
     * 
     * @param taskList to be saved
     * @throws IOException If file cannot be created.
     */
    public void save(TaskList taskList) throws IOException {
        int length = taskList.getLength();
        String finalOutput = "";
        File file = new File(path);

        for (int i = 0; i < length; i++) {
            finalOutput = finalOutput + taskList.getTask(i).getAttributes() + "\n";
        }

        file.delete();
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(path, false);
        fileWriter.write(finalOutput);
        fileWriter.close();
    }
}
