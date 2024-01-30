import Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    Storage(String filePath) {
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (Task task : tasks) {
                fw.write(task.saveFormat());
            }
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }


}


