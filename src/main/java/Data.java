import com.sun.source.util.TaskListener;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
public class Data {
    TaskList tasks;
    Input input;
    public Data() {
        this.tasks = new TaskList();
        this.input = new Input();
    }
    public Data(TaskList taskList) {
        this.tasks = taskList;
        this.input = new Input();
    }
    public void createFile() {
        String directoryPath = "data";
        String filePath = directoryPath + File.separator + "Duke.txt";

        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(TaskList tasks) {
        String filePath = "data/Duke.txt";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
            bufferedWriter.write(tasks.getInputs());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load() {
        String filePath = "data/Duke.txt";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] arr = line.split(" ", 2);
                String command = arr[0].toLowerCase();
                Command cmd = null;
                if (command.equals("todo")) {
                    cmd = Command.TODO;
                } else if (command.equals("deadline")) {
                    cmd = Command.DEADLINE;
                } else if (command.equals("event")) {
                    cmd = Command.EVENT;
                }

                try {
                    cmd.execute(tasks, line);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                line = bufferedReader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
