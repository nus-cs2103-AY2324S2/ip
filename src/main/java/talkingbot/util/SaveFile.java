package talkingbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import talkingbot.exception.TalkingBotException;
import talkingbot.task.Task;

/**
 * Class representing the save file.
 */
public class SaveFile {
    private static final String IO_EXCEPTION_MSG = "ERROR! Cannot read from file!";
    private static final String FILE_NOT_FOUND_EXCEPTION_MSG = "ERROR! File not found!";
    private final File saveFile;
    private final String path;

    /**
     * Constructor for the SaveFile class.
     *
     * @param path Relative path to the save file.
     */
    public SaveFile(String path) {
        this.saveFile = new File(path);
        this.path = path;
        if (!this.saveFile.exists()) {
            try {
                this.saveFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Retrieves the tasks saved in the file as a list of tasks.
     *
     * @return A TaskList indicating the list of tasks currently saved.
     * @throws TalkingBotException If there is any exception.
     */
    public TaskList getTasksFromFile() throws TalkingBotException {
        try {
            Scanner fileScanner = new Scanner(this.saveFile);
            TaskList tasks = new TaskList();
            while (fileScanner.hasNextLine()) {
                String curLine = fileScanner.nextLine();
                tasks.addTask(Task.generateTaskFromFile(curLine));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new TalkingBotException(FILE_NOT_FOUND_EXCEPTION_MSG);
        } catch (Exception e) {
            throw new TalkingBotException(IO_EXCEPTION_MSG);
        }
    }

    /**
     * Saves the task list into a file.
     *
     * @param tasks Task list to be saved.
     * @throws TalkingBotException If IOException occurs.
     */
    public void saveTasksToFile(TaskList tasks) throws TalkingBotException {
        try {
            FileWriter fileWriter = new FileWriter(this.saveFile);
            StringBuilder strBuild = new StringBuilder();
            for (int i = 0; i < tasks.getSize(); i++) {
                Task curTask = tasks.getTask(i);
                strBuild.append(curTask.getSaveFileString());
                if (i < tasks.getSize() - 1) {
                    strBuild.append("\n");
                }
            }
            fileWriter.write(strBuild.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new TalkingBotException("ERROR! IOException occurred!");
        }
    }

    /**
     * Returns the path.
     *
     * @return Path.
     */
    public String getFileName() {
        return this.path;
    }
}
