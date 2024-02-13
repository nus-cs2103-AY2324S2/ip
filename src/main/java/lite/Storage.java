package lite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import lite.task.Task;
import lite.util.FileParseInput;
import lite.util.LiteException;
public class Storage {
    private static final String PATH = "./lite.txt";

    public static void save(ArrayList<Task> tasks) {
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < tasks.size(); i++) {
                bw.write(tasks.get(i).saveToFile());
            }
            bw.close();
        } catch (IOException e) {
            LiteException.SaveException(e);
        }
    }

    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) {
            return tasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = br.readLine();
        while(s != null) {
            tasks.add(FileParseInput.parse(s));
            s = br.readLine();
        }
        return tasks;
    }
}
