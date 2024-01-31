import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static final String fileRoute = "./src/main/data/acadList.txt";
    private FileManager() {

    }

    public static ArrayList<Task> getList(File readFile) {
        ArrayList<Task> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(readFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] lineSplit = line.split("\\|");
                String action = lineSplit[0].trim();
                String description = lineSplit[2].trim();
                String isDone = lineSplit[1].trim();
                if (action.equals("T")) {
                    Todo addTask = new Todo(description);
                    checkIfDone(addTask, isDone);
                    lst.add(addTask);
                } else if (action.equals("D")) {
                    Deadline addTask = new Deadline(description, lineSplit[3].trim());
                    checkIfDone(addTask, isDone);
                    lst.add(addTask);
                } else if (action.equals("E")) {
                    String[] splitFromAndTo = lineSplit[3].trim().split("-");
                    Event addTask = new Event(description, splitFromAndTo[0], splitFromAndTo[1]);
                    checkIfDone(addTask, isDone);
                    lst.add(addTask);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return lst;
        }
    }

    public static void checkIfDone(Task t, String val) {
        if (val.equals("0")) {
            t.markAsNotDone();
        } else {
            t.markAsDone();
        }
    }

    public static File load() {
        File file = new File(fileRoute);
        try {
            if (file.exists()) {
                return file;
            } else {
                file.createNewFile();
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void readFile(File fileToRead) {
        try {
            Scanner sc = new Scanner(fileToRead);
            System.out.println("This is retrieved from the file.");
            while(sc.hasNext()) {
                String currentLine = sc.nextLine();
                DukeExceptions.checkCorruptedFile(currentLine);
                System.out.println(currentLine);
            }
            System.out.println("____________________________________________________________");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I can't seem to find the file that you want.");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveFile(File fileToSave, ArrayList<Task> lst) {
        try {
            FileWriter writer = new FileWriter(fileToSave);
            for (Task task : lst) {
                writer.write(task.fileToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
