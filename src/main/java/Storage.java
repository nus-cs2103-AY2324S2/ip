import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        } else {
            try {
                loadTasks(f);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

    // Loads taskList from storage file
    public void loadTasks(File f) throws FileNotFoundException {
        // Format:
        // T | 1 | read book
        // D | 0 | homework | Jan
        // E | 0 | meeting | 2pm | 4pm
        // Currently does not handle if file is not in this format
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String curr = s.nextLine();
            String[] parts = curr.split("\\|");
            for (int i = 0; i < parts.length; ++i) {
                parts[i] = parts[i].trim();
            }

            String taskType = parts[0];
            String done = parts[1];
            String name = parts[2];
            Task newTask;
            if (taskType.equals("T")) {
                newTask = new Todo(name);
            } else if (taskType.equals("D")) {
                newTask = new Deadline(name, parts[3]);
            } else {
                newTask = new Event(name, parts[3], parts[4]);
            }
            if (done.equals("1")) {
                newTask.mark();
            }
            taskList.add(newTask);
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
