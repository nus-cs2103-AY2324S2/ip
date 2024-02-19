package arona;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String FILE_PATH = "./tasklist.txt";
    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<Boolean> statuses = new ArrayList<>();

    public TwoArrayList load() throws FileException {
        try {
            File file = new File(FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            if (!file.exists()) {
                file.createNewFile();
            }

            while (line != null) {
                texts.add(Parser.FileInputToTask(line));
                statuses.add(Parser.FileInputToTaskStatus(line));
                line = reader.readLine();
            }
            reader.close();
            return new TwoArrayList(texts, statuses);
        } catch (FileNotFoundException e) {
            throw new FileException("Oops, Arona cannot find your task file!");
        } catch (IOException e) {
            throw new FileException("Sensei! There were some errors reading tasks from the file: " + e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws FileException {
        try {
            File file = new File(FILE_PATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : tasks) {
                // System.out.println(task.toString());
                writer.write(Parser.taskToFileOutput(task));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e ) {
            throw new FileException("Sensei! There were some errors reading tasks from the file: " + e.getMessage());
        }
    }
}
