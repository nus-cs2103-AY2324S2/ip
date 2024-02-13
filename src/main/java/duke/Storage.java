package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeCeption;
import duke.tasks.TaskList;

/**
 * Class that is used to save and load data from file
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loads data from file and push it into the Task List
     * @param taskList TaskList contains all the tasks
     * @param ui Ui to print to system
     */
    public void loadData(TaskList taskList, Ui ui) {
        try {
            File file = this.retrieveFile(ui);
            ArrayList<String> dataStrings = this.dataToText(file);
            taskList.loadList(dataStrings);
        } catch (DukeCeption e) {
            ui.print(e.getMessage());
        }

    }

    /**
     * Read file and transform it into text for the TaskList
     * @param file File to be read
     * @return ArrayList of Task data
     * @throws DukeCeption when file is not found
     */
    public ArrayList<String> dataToText(File file) throws DukeCeption {
        try {
            ArrayList<String> dataStrings = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dataStrings.add(line);
            }
            scanner.close();
            return dataStrings;
            } catch (FileNotFoundException e) {
                throw new DukeCeption("File is not found!");
            }
        }

    /**
     * Saves Task data into the file
     * @param taskList Task List of tasks
     * @param ui Ui to print to system
     */
    public void saveData(TaskList taskList, Ui ui) {
        File file = this.retrieveFile(ui);
        ArrayList<String> dataToTexts = taskList.saveFormat();
        try {
            FileWriter writer = new FileWriter(file, false);
            for (String text : dataToTexts) {
                writer.write(text + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Retrive the file to be either read or save
     * @param ui Ui to print to system
     * @return File that was retrieved
     */
    public File retrieveFile(Ui ui) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
            ui.print("Created data folder as none was found");
        }

        assert parentDir.exists();
        
        try {
            if (file.createNewFile()) {
                ui.print("Created linus.txt to read files from");
            }
        } catch (IOException e) {
            ui.print("Could not create file :/");
        }
        return file;
    }

}