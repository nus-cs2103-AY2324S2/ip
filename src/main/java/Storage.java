import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String file) {
        this.FILE_PATH = file;
    }

    public ArrayList<Task> loadTasksFile() throws DukeException {
        String FILE_DIR = "./data";
        File directory = new File(FILE_DIR);
        File file = new File(FILE_PATH);
        try {
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ArrayList<Task> inputList = new ArrayList<>();
            Scanner fileSc = new Scanner(file);
            while (fileSc.hasNext()) {
                String task = fileSc.nextLine();
                String[] argument = task.split(" \\| ");
                String category = argument[0];
                String status = argument[1];
                String description = argument[2];

                switch (category) {
                    case "T":
                        inputList.add(new ToDo(status, description));
                        break;
                    case "D":
                        String by = argument[3];
                        inputList.add(new Deadline(status, description, by));
                        break;
                    case "E":
                        String[] duration = argument[3].split(" - ");
                        String start = duration[0];
                        String end = duration[1];
                        inputList.add(new Event(status, description, start, end));
                        break;
                    default:
                        break;
                }
            }
            fileSc.close();
            return inputList;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void saveTasksFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        StringBuilder msg = new StringBuilder();
        for (Task task : taskList) {
            msg.append(task.toFile()).append("\n");
        }
        fw.write(msg.toString());
        fw.close();
    }
}
