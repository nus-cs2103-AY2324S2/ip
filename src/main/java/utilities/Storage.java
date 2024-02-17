package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import commands.PatternParser;
import exceptions.RyanGoslingException;
import tasks.Task;



/**
 * The Storage class is responsible for loading and saving tasks to/from the hard drive.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses and loads tasks from the hard drive.
     *
     * @return An ArrayList of Task objects representing the loaded tasks.
     * @throws FileNotFoundException If the file specified by filePath is not found.
     * @throws RyanGoslingException  If the task data on the hard drive is not in the expected format.
     */
    public ArrayList<Task> parseAndLoadTasks() throws FileNotFoundException, RyanGoslingException {
        File file = new File(filePath);
        //We first check if the file directory exists. We will create the folder for user instead.
        if (!file.exists()) {
            try {
                Files.createDirectories(file.toPath().getParent());
                Files.createFile(file.toPath());
                System.out.println("File created successfully\n"
                                           + "It will appear in data folder. " + filePath);
            } catch (IOException e) {
                System.err.println("Failed to create file.\n"
                                           + "Do try again!: " + e.getMessage());
            }
        }

        Scanner s = new Scanner(file);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            PatternParser.addTasktoList(listOfTasks, currentLine);
        }
        return listOfTasks;
    }

    /**
     * Writes the provided task list to the hard drive.
     *
     * @param taskList The ArrayList of Task objects to be written to the hard drive.
     */
    public void writeToTaskList(ArrayList<Task> taskList) {
        StringBuilder toAdd = new StringBuilder();
        for (int i = 0; i < taskList.size(); i += 1) {
            Task taskToAdd = taskList.get(i);
            toAdd.append(taskToAdd.getTaskType());
            int taskDone = taskToAdd.isIstaskDone() ? 1 : 0;
            String[] possibleDates = taskToAdd.getDates();
            String[] possibleTimes = taskToAdd.getTimes();
            toAdd.append(" | ")
                    .append(taskDone).append(" | ")
                    .append(taskToAdd.getTaskName()).append(" | ")
                    .append(possibleDates[0]).append(" | ")
                    .append(possibleTimes[0]).append(" | ")
                    .append(possibleDates[1]).append(" | ")
                    .append(possibleTimes[1]);
            if (i != taskList.size() - 1) {
                toAdd.append(System.lineSeparator());
            }
        }
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(toAdd.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing! This a Level 5 bruh moment!");
        }
    }

}
