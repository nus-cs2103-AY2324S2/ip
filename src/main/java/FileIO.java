import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileIO {

    private static final File FILE = new File("./data/logfile.txt");

    public FileIO() {
    }

    public static void createFile() throws IOException {

        if (!FILE.getParentFile().exists()) {
            FILE.getParentFile().mkdirs();
        }
        if (!FILE.exists()) {
            FILE.createNewFile();
        }
    }

    public List<Task> readFromFile() throws FileNotFoundException {
        List<Task> l = new ArrayList<>();
        try {
            if (!FILE.exists()) {
                createFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileReader reader = new FileReader(FILE);
        Scanner sc = new Scanner(reader);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] array_split = line.split(" \\| ");
            if (array_split[0].equals("T")) {
                ToDo todo = new ToDo(array_split[2]);
                if (array_split[1].equals("1")) {
                    todo.markDone();
                }
                l.add(todo);
            } else if (array_split[0].equals("D")) {
                Deadline deadline = new Deadline(array_split[2], array_split[3]);
                if (array_split[1].equals("1")) {
                    deadline.markDone();
                }
                l.add(deadline);
            } else if (array_split[0].equals("E")) {
                Event event = new Event(array_split[2], array_split[3], array_split[4]);
                if (array_split[1].equals("1")) {
                    event.markDone();
                }
                l.add(event);
            }
        }
        return l;
    }


    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }


    protected void saveToFile(List<Task> l) throws IOException {
        createFile(); // Ensure the file and directory exist

        try (FileWriter fw = new FileWriter(FILE, false)) {
            for (Task task : l) {
                String taskString = formatTaskForFile(task) + System.lineSeparator();
                fw.write(taskString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    private String formatTaskForFile(Task task) {
        String status = task.getStatusIcon();
        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            return "D | " + status + " | " + task.description + " | " + ((Deadline) task).endTime;
        } else if (task instanceof Event) {
            return "E | " + status + " | " + task.description + " | " + ((Event) task).startTime + "-" + ((Event) task).endTime;
        }
        return "";
    }


}
