package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

class Storage {
    private final File file;

    Storage(String filePath) {
        this.file = new File(filePath);
    }

    Storage(File file) {
        this.file = file;
    }

    /**
     * Read the content of the file and returns a Task ArrayList.
     * If no existing file is found or file is corrupted, returns empty ArrayList.
     *
     * @return ArrayList<Task> of the content of the file.
     */
    ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        // If file doesn't exist, create the parent directories and the file
        try {
            // Create parent directories if it does not exist
            file.getParentFile().mkdirs();
            // Create file if it does not exist
            file.createNewFile();
            // Read file
            assert file.exists();
            Scanner sc = new Scanner(file);
            String lineRead;
            while (sc.hasNext()) {
                lineRead = sc.nextLine();
                tasks.add(Parser.parseToTask(lineRead, "\\|"));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.\n" +
                    "Program will run without saved file.");
        } catch (DukeException ex) {
            System.out.println("An error occurred while reading the file.\n" +
                    "File may contain corrupted data!\n" +
                    "Program will run without saved file.");
        }
        return tasks;
    }

    /**
     * Write the content of the tasks into the file.
     *
     * @param tasks the TaskList that will be written into the file.
     */
    void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.toStore());
            fw.close();
        } catch (IOException ex) {
            System.out.println("Failed to save progress!");
        }
    }
}
