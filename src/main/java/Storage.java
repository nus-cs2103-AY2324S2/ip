import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private List<Task> taskList;

    private String FILEPATH = "data/duke.txt";

    public Storage() {
        this.taskList = new ArrayList<>();
        File f = new File(FILEPATH);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                writeToFile(FILEPATH,"");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    // Saves whole taskList to hard disk
    public void updateStorage() {
        String text = "";
        for (Task t : taskList) {
            text += t.fileString();
            text += "\n";
        }
        try {
            writeToFile(FILEPATH, text);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeToFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
        this.updateStorage();
    }

    public Task deleteTask(int index) {
        Task removed = this.taskList.remove(index);
        this.updateStorage();
        return removed;
    }

    public Task markAsDone(int index) {
        // Note input index is 1-indexed
        this.taskList.get(index-1).mark();
        this.updateStorage();
        return this.taskList.get(index-1);
    }

    public Task unmark(int index) {
        // Note input index is 1-indexed
        this.taskList.get(index-1).unmark();
        this.updateStorage();
        return this.taskList.get(index-1);
    }

    public int getSize() {
        return this.taskList.size();
    }

        public String displayList() {
        String d = "";
        for (int i = 1; i <= this.taskList.size(); ++i) {
            d += (i + ". " + taskList.get(i-1).toString() + '\n');
        }
        return d;
    }

}
