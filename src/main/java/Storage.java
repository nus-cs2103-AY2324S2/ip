import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String storageFilepath;

    public Storage(String filepath) {
        this.storageFilepath = filepath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader(this.storageFilepath));
        String line = br.readLine();
        while (line != null) {
            String[] taskDescription = line.split(" \\| ");
            String taskType = taskDescription[0];

            switch (taskType) {
            case "TODO":
                taskList.add(new Todo(taskDescription[2]));
                break;

            case "DEADLINE":
                taskList.add(new Deadline(taskDescription[2], taskDescription[3]));
                break;

            case "EVENT":
                taskList.add(new Event(taskDescription[2], taskDescription[3], taskDescription[4]));
                break;

            default:
                System.out.println("An error has occurred");
                break;
            }

            if (taskDescription[1].equals("COMPLETED")) {
                taskList.get(taskList.size() - 1).isCompleted();
            }

            line = br.readLine();
        }
        br.close();
        return taskList;
    }

}
