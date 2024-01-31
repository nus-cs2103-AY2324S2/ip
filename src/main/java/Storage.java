import java.io.BufferedReader;
import java.io.IOException;

import java.lang.reflect.Array;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    static String HOME = System.getProperty("user.home");
    static Path FILEPATH = Paths.get(HOME, "Downloads", "NUS_CS",
            "CS2103", "duke.txt");

    public static ArrayList<Task> retrieveList() {
        if (Files.exists(FILEPATH)) {
            ArrayList<Task> taskList = new ArrayList<>();
            try (BufferedReader br = Files.newBufferedReader(FILEPATH)) {
                String currentLine = null;
                while ((currentLine = br.readLine()) != null) {
                    String[] inputs = currentLine.split(",");
                    try {
                        boolean isDone = Boolean.parseBoolean(inputs[1]);

                        switch (inputs[0]) {
                            case "T":
                                ToDo t = new ToDo(inputs[2], isDone);
                                taskList.add(t);
                                break;
                            case "D":
                                Deadline d = new Deadline(inputs[2], isDone, inputs[3]);
                                taskList.add(d);
                                break;

                            case "E":
                                Event e = new Event(inputs[0], isDone, inputs[1], inputs[2]);
                                taskList.add(e);
                                break;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("LOG: Task file corrupted.");
                        return new ArrayList<>();
                    }
                }
            } catch (IOException e) {
                System.out.println("No more tasks recorded.");
            }
            return taskList;
        } else {
            System.out.println("LOG: File not found, empty list");
            return new ArrayList<Task>();
        }
    }

    public static void updateFile(ArrayList<Task> taskList) {
        createFile();
        ArrayList<String> taskContent = new ArrayList<>();
        for (Task t : taskList) {
            taskContent.add(t.toStore());
        }
        try {
            Files.write(FILEPATH, taskContent);
            System.out.println("LOG: Updated file contents.");
        } catch (IOException e) {
            System.out.println("LOG: File cannot be found.");
        }

    }

    public static void createFile() {
        try {
            Files.createFile(FILEPATH);
            System.out.println("LOG: File created");
        }
        catch (IOException e) {
            System.out.println("LOG: File already exists.");
        }

    }
}
