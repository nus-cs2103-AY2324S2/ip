package wis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import wis.task.Task;
import wis.task.TaskList;
import wis.util.WisException;
import wis.util.FileDataParser;

public class Storage {
    private static final String PATH = "data/tasks.txt";

    public static void saveTasks(TaskList tasks) {
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            tasks.save(bufferedWriter);
            bufferedWriter.close();

        } catch (IOException e) {
            WisException.SaveFileExceptionHandler(e);
        }
    }

    public static ArrayList<Task> loadTasks() throws IOException, InputMismatchException {
        File file = new File(PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks;
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while (line != null) {
            tasks.add(FileDataParser.parseLine(line));
            line = bufferedReader.readLine();
        }
        return tasks;
    }
}
