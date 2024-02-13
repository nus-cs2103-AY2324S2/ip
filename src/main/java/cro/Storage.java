package cro;

import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Represents a Storage in the program.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for a Storage object.
     * @param filePath Absolute file path that leads to the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns nothing. Saves the current tasks in the taskList into a simple text file.
     * @param taskList A TaskList that contains all the tasks to be saved.
     */
    public void updateSave(TaskList taskList) {
        try {
            FileWriter saveWriter = new FileWriter("saveFile.txt");
            for (int i = 0; i < taskList.taskList.size(); i++) {
                saveWriter.write(taskList.taskList.get(i).getSaveLine());
            }
            saveWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a list of Task objects from the save file specified in the constructor.
     * @return A List of Task objects to be passed into the program for use.
     */
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            File saveData = new File(filePath);
            if (saveData.createNewFile()) {
                System.out.println("New save file created");
            } else {
                System.out.println("Save file exists. Loading...");
            }
            Scanner saveReader = new Scanner(saveData);
            while (saveReader.hasNextLine()) {
                String data = saveReader.nextLine();
                List<String> splitStr = new LinkedList<>(Arrays.asList(data.trim().split("\\s+")));
                String type = splitStr.get(0);
                switch (type) {
                    case "T":
                        ToDo newToDo = new ToDo(splitStr);
                        loadedTasks.add(newToDo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(splitStr);
                        loadedTasks.add(newDeadline);
                        break;
                    case "E":
                        Event newEvent = new Event(splitStr);
                        loadedTasks.add(newEvent);
                        break;
                }
            }
        } catch (IOException | CroException e) {
            System.out.println("An error occurred while loading...");
            System.out.println(e.getMessage());
        }
        return loadedTasks;
    }
}
