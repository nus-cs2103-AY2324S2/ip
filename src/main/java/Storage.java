import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    private File infostored;

    public void makeNewDirectory() {
        File location = new File("./data");
        if (location.mkdirs()) {
            System.out.println("Successfully made new directory!");
        } else {
            System.out.println("Data file already exists.");
        }
    }

    public void makeNewFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                // Create the file if it doesn't exist
                if (file.createNewFile()) {
                    System.out.println("File successfully created.");
                }
            } catch (IOException e) {
                file = new File("./data/tasks.txt"); //hardcode option
                System.out.println("Filepath invalid. Data has been stored to the filepath ./data/tasks.txt");
            }
        }
        infostored = file;
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (infostored.length() == 0) {
            return tasks;
        }
        try {
            Scanner s = new Scanner(infostored);
            while (s.hasNext()) {
                String nextinput = s.nextLine();
                Task task = convert(nextinput);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return tasks;
    }

    public Task convert(String input) {
        String[] inputs = input.split("~");
        String type = inputs[0];
        boolean marked = inputs[1].equals("marked");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        switch (type) {
        case "T" -> {
            Task task = new ToDo(inputs[2]);
            if (marked) {
                task.mark();
            }
            return task;
        }
        case "E" -> {
            LocalDate start = LocalDate.parse(inputs[3], dateFormatter);
            LocalDate end = LocalDate.parse(inputs[4], dateFormatter);
            Task task = new Event(inputs[2], start, end);
            if (marked) {
                task.mark();
            }
            return task;
        }
        case "D" -> {
            LocalDate due = LocalDate.parse(inputs[3], dateFormatter);
            Task task = new Deadline(inputs[2], due);
            if (marked) {
                task.mark();
            }
            return task;
        }
        }
        return new Task("Error");
    }

    public void save() {

    }

    public Storage(String filePath) {
        makeNewDirectory(); //creates a new data file in the same folder if not already existing
        makeNewFile(filePath); //creates a new file in filepath if not already existing
    }

}
