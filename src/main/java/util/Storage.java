package util;

import exceptions.ChatBotException;
import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/duke/.txt";
    public Storage() {};

    public void saveToFile(TaskList taskList) {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            for (Task task: taskList.getTasks()) {
                fw.write(task.toStringForFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! There was an error saving to file.");
        }
    }

    public void loadFromFile(TaskList taskList) throws IOException {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = Parser.parseTasksFromFile(taskString);
                taskList.getTasks().add(task);
            }
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading the file.");
        } catch (ChatBotException e) {
            System.out.println("Oops! There was an error parsing the file.");
        }
    }


}

