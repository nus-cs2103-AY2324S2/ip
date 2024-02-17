package duke;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * A class used to write and load data.
 */
public class Storage {
    private TaskList tasks;
    private Ui input;
    private Command command;
    public Storage() {
        this.tasks = new TaskList();
        this.input = new Ui();
        this.command = Command.HELLO;
    }
    public Storage(TaskList taskList) {
        this.tasks = taskList;
        this.input = new Ui();
        this.command = Command.HELLO;
    }

    /**
     * Creates a file where data is saved.
     */
    public void createFile() {
        String directoryPath = "data";
        String filePath = directoryPath + File.separator + "Duke.txt";

        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes data in tasks to file.
     * @param tasks List of tasks given by user.
     */
    public void write(TaskList tasks) {
        String filePath = "data/Duke.txt";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
            bufferedWriter.write(tasks.getInputs());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes everything in file.
     */
    public static void clear() {
        String filePath = "data/Duke.txt";
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data in file to tasks.
     * @param tasks List to store tasks given in data.
     */
    public void load(TaskList tasks) {
        String filePath = "data/Duke.txt";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {

                try {
                    Task task = Parser.parseFromData(line);
                    tasks.add(task);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }


                line = bufferedReader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
