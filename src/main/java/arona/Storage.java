package arona;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private ArrayList<String> text = new ArrayList<>();
    private ArrayList<Boolean> status = new ArrayList<>();
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TwoArrayList load() throws FileException {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                text.add(Parser.FileInputToTask(line));
                status.add(Parser.FileInputToTaskStatus(line));
                line = reader.readLine();
            }
            reader.close();
            return new TwoArrayList(text, status);
        } catch (FileNotFoundException e) {
            throw new FileException("Oops, Arona cannot find your task file!");
        } catch (IOException e) {
            throw new FileException("Sensei! There were some errors reading tasks from the file: " + e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws FileException {
        try {
            File file = new File(filePath);
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
