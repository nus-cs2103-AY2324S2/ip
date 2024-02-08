package area;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Storage {

    private static ArrayList<String> taskList = new ArrayList<String>();
    private static final String FILE = "./data/duke.txt";

    private File file;
    private File folder;
    private String filePath;
    private TaskList tasks;
    private ArrayList<String> instructions;

    public Storage(String path, TaskList tasks) {
        this.filePath = path;
        file = new File(path);
        folder = new File("./data");
        this.tasks = tasks;
        this.instructions = new ArrayList<String>();
    }

    public void addInstruction(String instruction) {
        this.instructions.add(instruction);
    }

    public void createFolder() {
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void createFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getTasks() {
        return taskList;
    }

    public void loadTasks() {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] sentence = line.split(" ", 2);
                    String command = sentence[0];
                    if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                        tasks.addTask(line);
                    } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                        tasks.modifyTask(line);
                    }else if(command.equals("unmark")){
                        tasks.modifyTask(line);
                    }else if(command.equals("delete")){
                        tasks.modifyTask(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
    }

    public void saveTasks() {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            for (String instruction : this.instructions) {
                writer.write(instruction + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
