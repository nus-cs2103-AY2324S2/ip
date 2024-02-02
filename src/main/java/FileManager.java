import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class FileManager extends Parser {
    private static final String PATH = "data/tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                bufferedWriter.write(task.toSavedString());
            }
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
