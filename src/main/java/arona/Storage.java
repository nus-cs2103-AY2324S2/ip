package arona;

import java.io.*;
import java.util.ArrayList;

/**
 * Access and store user tasklist
 */
public class Storage {
    private final String FILE_PATH = "./src/data/tasklist.txt";
    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<Boolean> statuses = new ArrayList<>();

    /**
     * Load information from the file.
     *
     * @return TwoArrayList which contains two arrays. The first contain tasks
     * to add while the second contain information if the task should be marked
     * as completed.
     * @throws FileException if the file destination cannot be found.
     */
    public TwoArrayList load() throws FileException {
        try {
            File file = new File(FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

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

    /**
     * Save information to the file.
     *
     * @param tasks Tasks which should be saved in the file.
     * @throws FileException if file destination cannot be found.
     */
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
