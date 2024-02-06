package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    final static String dataPath = System.getProperty("user.dir") + "/data";
    final static String fileName = "duke.txt";
    public ArrayList<Task> loadData() {
        File directory = new File(dataPath);
        File file = new File(dataPath + "/" + fileName);
        ArrayList<Task> tempTaskStorage = new ArrayList<>(100);

        // check if data directory and file exist already
        if (!directory.exists()){
            directory.mkdir();
            try {
                file.createNewFile();
                System.out.println("First initialization, creating new save file...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Data file missing, creating new save file...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String newEntry = s.nextLine();
                    String[] entryDetails = newEntry.split(" \\| ");
                    Task newTask;
                    switch (entryDetails[0]) {
                        case "T":
                            newTask = new ToDo(entryDetails[1], entryDetails[2]);
                            tempTaskStorage.add(newTask);
                            break;
                        case "D":
                            newTask = new Deadline(entryDetails[1], entryDetails[2], entryDetails[3]);
                            tempTaskStorage.add(newTask);
                            break;
                        case "E":
                            newTask = new Event(entryDetails[1], entryDetails[2], entryDetails[3], entryDetails[4]);
                            tempTaskStorage.add(newTask);
                            break;
                    }
                }
                return tempTaskStorage;
            } catch (DukeException | ArrayIndexOutOfBoundsException e) {
                try {
                    file.delete();
                    file.createNewFile();
                    System.out.println("Data file corrupted, creating new save file...");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(100);
    }

    public void saveData(TaskList tasks) {
        File directory = new File(dataPath);
        File file = new File(dataPath + "/" + fileName);
        // reset file
        try {
            file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            ArrayList<Task> taskList = tasks.getTaskList();
            for (Task tsk: taskList) {
                if (tsk instanceof ToDo) {
                    writer.write("T | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + System.lineSeparator());
                } else if (tsk instanceof Deadline) {
                    writer.write("D | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + " | " + ((Deadline) tsk).getBy()
                            + System.lineSeparator());
                } else if (tsk instanceof Event) {
                    writer.write("E | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + " | " + ((Event) tsk).getFrom() + " | " + ((Event) tsk).getTo()
                            + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
