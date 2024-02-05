package Victor.Storage;

import Victor.TaskType.Deadline;
import Victor.TaskType.Event;
import Victor.TaskType.Task;
import Victor.TaskType.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    File dataFile;
    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> currentList = new ArrayList<>();
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                String[] inputs = nextLine.split("\\|");
                String fileDataType = inputs[0].trim();
                switch (fileDataType) {
                    case "T" -> {
                        Task newTodo = new Todo(inputs[2].trim(), Boolean.parseBoolean(inputs[1].trim()));
                        currentList.add(newTodo);
                    }
                    case "D" -> {
                        Task newDeadline = new Deadline(inputs[2].trim(), Boolean.parseBoolean(inputs[1].trim()), inputs[3].trim());
                        currentList.add(newDeadline);
                    }
                    case "E" -> {
                        Task newEvent = new Event(inputs[2].trim(), Boolean.parseBoolean(inputs[1].trim()), inputs[3].trim(), inputs[4].trim());
                        currentList.add(newEvent);
                    }

                }
            }
        } catch (FileNotFoundException e) {
            try {
                boolean isCreated = dataFile.createNewFile();
            } catch (IOException e2) {
                System.out.println("Error: Cannot create hard drive file.");
                System.out.println("Data will not be saved after session end.");
            }
        }
        return currentList;
    }
}
