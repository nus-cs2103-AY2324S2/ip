import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private final static String dirPath = "./data/";
    private final static String filePath = "./data/taskList.txt";
    private static File file;


    public static void init() {
        try{ 
            create();
            load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    private static void create() throws IOException {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory.");
                return;
            } 
        }

        file = new File(directory, "taskList.txt");
        if (!file.exists()) {
             file.createNewFile();  
        }  
    }

    public static void store() {
        FileWriter fw = null;
        try {
            // To reset the file
            fw = new FileWriter(filePath);
            fw.write("");
            fw.close();;

            fw = new FileWriter(filePath, true);
            for (int i = 1; i <= TaskList.listSize(); i++) {
                String textToAppend = TaskList.getTask(i).toString();
                System.out.println(textToAppend);
                fw.write(textToAppend + "\n");
            }   
        } catch (IOException e) {
            System.out.println("An error occurred while storing data: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred while closing the FileWriter: " + e.getMessage());
            }
        }
    }

    private static void load() throws IOException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            decode(line);
        }
        s.close();
    }

    private static void decode(String line) {
        String name;
        String[] parts;

        char taskType = line.charAt(1);
        boolean marked = line.charAt(4) == 'X' ? true : false; 
        Task task;

        line = line.substring(7);
        switch (taskType) {
            case 'T':
                task = new ToDo(line);
                break;
            case 'D':
                parts = line.split("\\(by: ");
                name = parts[0].trim();
                String by = parts[1].substring(0, parts[1].length() - 1).trim();
                task = new Deadline(name, by);
                break;
            case 'E':
                parts = line.split("\\(from: ");
                name = parts[0].trim();
                String[] parts2 = parts[1].split("to: ");
                String from = parts2[0];
                String to = parts2[1].substring(0, parts2[1].length() - 1).trim();
                task = new Event(name, from, to);
                break;
            default:
                task = new ToDo("ERROR");
                System.out.println("Error: Decoding Error, task does not match any of the known types");           
        } 
        TaskList.addTaskSlient(task);
        if (marked) {
            TaskList.markTaskSilent(TaskList.listSize());
        }
    }

}
