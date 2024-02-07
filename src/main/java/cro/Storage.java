package cro;

import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void updateSave(TaskList taskList) {
        try {
            FileWriter saveWriter = new FileWriter("saveFile.txt");
            for (int i = 0; i < taskList.taskList.size(); i++) {
                saveWriter.write(taskList.taskList.get(i).getSaveLine());
            }
            saveWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
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
