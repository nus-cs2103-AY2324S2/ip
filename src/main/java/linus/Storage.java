package linus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import linus.exceptions.LinusCeption;
import linus.tasks.TaskList;

/**
 * Class that is used to save and load data from file
 */
public class Storage {

    private String filePath;

    private Ui ui;

    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.filePath = filePath;
    }

    /**
     * loads data from file and push it into the Task List
     * @param taskList TaskList contains all the tasks
     * @param ui Ui to print to system
     */
    public void loadData(TaskList taskList) {
        try {
            File file = this.retrieveFile();
            ArrayList<String> dataStrings = dataToText(file);
            taskList.loadList(dataStrings);
        } catch (LinusCeption e) {
            ui.add(e.getMessage());
            ui.createNewFileForUser();
            taskList.reset();
        }

    }

    /**
     * Read file and transform it into text for the TaskList
     * @param file File to be read
     * @return ArrayList of Task data
     * @throws LinusCeption when file is not found
     */
    public ArrayList<String> dataToText(File file) throws LinusCeption {
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
                throw new LinusCeption("File is not found!");
            } catch (Exception e) {
                throw e;
            }
        }

    /**
     * Saves Task data into the file
     * @param taskList Task List of tasks
     * @param ui Ui to print to system
     */
    public void saveData(TaskList taskList) {
        File file = retrieveFile();
        ArrayList<String> dataToTexts = taskList.saveFormat();
        try {
            FileWriter writer = new FileWriter(file, false);
            for (String text : dataToTexts) {
                writer.write(text + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.add(e.getMessage());
        }
    }

    /**
     * Retrive the file to be either read or save
     * @param ui Ui to print to system
     * @return File that was retrieved
     */
    public File retrieveFile() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
            ui.makeNewDirectoryForUser();
        }

        assert parentDir.exists();
        
        try {
            if (file.createNewFile()) {
                ui.createNewFileForUser();
            } else {
                ui.fileFoundForUser();
            }
        } catch (IOException e) {
            ui.add(e.getMessage());
        }
        return file;
    }

}