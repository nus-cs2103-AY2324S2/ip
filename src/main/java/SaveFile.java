import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Task.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SaveFile {
    private String fileName;
    private String directoryName;
    public SaveFile(String fileName, String directoryName) {
        this.fileName = fileName;
        this.directoryName = directoryName;
    }

    public void createDirectory() {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
            } else {
                System.err.println("Error creating directory");
            }
        }
    }

    public void createFile() {
        File file = new File(directoryName, fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error generating task list");
            }
        }
    }

    public void saveToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(this.directoryName + "/" + this.fileName);
            for (Task t : taskList) {
                fw.write(t.toFileString());
                fw.write((System.lineSeparator()));
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error writing task list to file: " + e.getMessage());
        }
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File f = new File(this.directoryName + "/" + this.fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                boolean isDone;

                if (data.split("[|]")[1].equals("0")) {
                    isDone = false;
                } else {
                    isDone = true;
                }

                if (data.startsWith("T")) {
                    String description = data.split("[|]")[2];
                    Todo newTodo = new Todo(description, isDone);
                    taskList.add(newTodo);
                } else if (data.startsWith("D")) {
                    String description = data.split("[|]")[2];
                    String by = data.split("[|]")[3];
                    Deadline newDeadline = new Deadline(description, isDone, by);
                    taskList.add(newDeadline);
                } else if (data.startsWith("E")) {
                    String description = data.split("[|]")[2];
                    String from = data.split("[|]")[3];
                    String to = data.split("[|]")[4];
                    Event newEvent = new Event(description, isDone, from, to);
                    taskList.add(newEvent);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {

        }
        return taskList;
    }
}