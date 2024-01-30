import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Storage {
    private final String STORAGE_PATH;

    public Storage(String filePath) {
        this.STORAGE_PATH = "/" + filePath;
    }

    public void saveToFile(ArrayList<Task> tasksList) {
        try {
            String directory = System.getProperty("user.dir") + "/data";
            File file = new File(directory);
            if (!file.exists()) {
                file.mkdir();
            }
            File saveFile = new File(System.getProperty("user.dir") + STORAGE_PATH);
            FileWriter fw = new FileWriter(saveFile, false);
            for (Task task : tasksList) {
                fw.write(task.toString() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadFromFile() throws FileNotFoundException {
        ArrayList<Task> tasksList = new ArrayList<>();
        System.out.println(STORAGE_PATH);
        String path = System.getProperty("user.dir") + STORAGE_PATH;
        System.out.println(path);
        File sourceFile = new File(path);
        if (!sourceFile.exists()) {
            return tasksList;
        } else {
            return readFile(sourceFile);
        }
    }

    public ArrayList<Task> readFile(File file) throws FileNotFoundException {
        ArrayList<Task> tasksList = new ArrayList<>();
        Scanner sc =new Scanner(file);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            String[] actionArray = nextLine.split("]");
            String[] actionNextArray = actionArray[1].split(":");
            String action = actionNextArray[0].strip();
            switch(action) {
                case "Todo": {
                    String[] taskArray = nextLine.split(":");
                    String task = taskArray[1].strip();
                    Task savedTask = new Todo(task);
                    if (nextLine.substring(1, 2).equals("X")) {
                        savedTask.check();
                    }
                    tasksList.add(savedTask);
                    break;
                }
                case "Deadline": {
                    String[] frontTaskArray = nextLine.split(":");
                    String[] backTaskArray = frontTaskArray[1].split("\\[");
                    String task = backTaskArray[0].strip();
                    String[] frontByArray = nextLine.split("\\[");
                    String[] backByArray = frontByArray[1].split("]");
                    String by = backByArray[0].strip();
                    LocalDate storageBy = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    String outputBy = storageBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Task savedTask = new Deadline(task, outputBy);
                    if (nextLine.substring(1, 2).equals("X")) {
                        savedTask.check();
                    }
                    tasksList.add(savedTask);
                    break;
                }
                case "Event": {
                    String[] frontTaskArray = nextLine.split(":");
                    String[] backTaskArray = frontTaskArray[1].split("\\[");
                    String task = backTaskArray[0].strip();
                    String[] frontFromArray = nextLine.substring(3).split("\\[");
                    String[] backFromArray = frontFromArray[1].split("-");
                    String from = backFromArray[0].strip();
                    String[] frontToArray = nextLine.split("-");
                    String[] backToArray = frontToArray[1].split("]");
                    String to = backToArray[0].strip();
                    LocalDate storageFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    String outputFrom = storageFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate storageTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    String outputTo = storageTo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Task savedTask = new Event(task, outputFrom, outputTo);
                    if (nextLine.substring(1, 2).equals("X")) {
                        savedTask.check();
                    }
                    tasksList.add(savedTask);
                    break;
                }
            }
        }
        return tasksList;
    }
}