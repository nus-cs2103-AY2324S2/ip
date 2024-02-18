package bmo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import bmo.task.Task;

public class Storage {

    private static final String FILE_PATH = "./data/task_data.txt";
    private static File dataFile;

    public Storage() {
        try {
            dataFile = new File(FILE_PATH);
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
        }
    }

    public String loadData() throws IOException{
        try {
            Scanner sc = new Scanner(dataFile);
            StringBuilder output = new StringBuilder();
            while (sc.hasNext()) {
                output.append(sc.nextLine());
                if (sc.hasNext()) {
                    output.append("\n");
                }
            }
            return output.toString();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return "";
        }
    }

    public void saveData(TaskList tasks) {
        StringBuilder taskContents = new StringBuilder();
        for (Task task : tasks) {
            taskContents.append(task.toSaveData());
        }
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(taskContents.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
