import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String HOME = System.getProperty("user.home");
    private static final Path FILEPATH = Paths.get(HOME, "Downloads", "NUS_CS",
            "CS2103", "duke.txt");

    public Storage() {
    }

    public static ArrayList<Task> retrieveList() throws IOException {
        if (Files.exists(FILEPATH)) {
            ArrayList<Task> taskList = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(FILEPATH);
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
                        LocalDateTime by = LocalDateTime.parse(inputs[3], Parser.dateTimeFormatter);
                        Deadline d = new Deadline(inputs[2], isDone, by);
                        taskList.add(d);
                        break;

                    case "E":
                        LocalDateTime from = LocalDateTime.parse(inputs[3], Parser.dateTimeFormatter);
                        LocalDateTime to = LocalDateTime.parse(inputs[4], Parser.dateTimeFormatter);
                        Event e = new Event(inputs[0], isDone, from, to);
                        taskList.add(e);
                        break;
                        }
                } catch (DateTimeException e) {
                    //System.out.println("LOG: Dates of tasks corrupted.");
                    Ui.showLoadingError();
                    return new ArrayList<>();
                }
            }
            return taskList;
        } else {
            //System.out.println("LOG: File not found, empty list");
            Ui.showLoadingError();
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
//            System.out.println("LOG: Updated file contents.");
        } catch (IOException e) {
//            System.out.println("LOG: File cannot be found.");
        }

    }

    public static void createFile() {
        try {
            Files.createFile(FILEPATH);
//            System.out.println("LOG: File created");
        }
        catch (IOException e) {
//            System.out.println("LOG: File already exists.");
        }

    }
}
