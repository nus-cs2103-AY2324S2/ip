import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> items;
    private final TaskListFileIo taskFileIo;
    private class TaskListFileIo {
        private static final String directoryPath = "data";
        private static final String fileName = "task_list.txt";
        public void loadFile() throws IOException {
            // check if the directory exists if not create it
            File directory = new File(directoryPath);
            directory.mkdir();

            // check if the file exists if not create it
            File file = new File(directoryPath + "/" + fileName);
            file.createNewFile();

            // read the file and parse into the array
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] strArr = sc.nextLine().split(" \\| ");
                Task t = null;
                switch (strArr[0]) {
                case "T":
                    t = new ToDo(strArr[2]);
                    break;
                case "D":
                    t = new Deadline(strArr[2], strArr[3]);
                    break;
                case "E":
                    String[] periodArr = strArr[3].split("-");
                    t = new Event(strArr[2], periodArr[0], periodArr[1]);
                    break;
                default:
                    // Fatal Error occured
                }

                if (strArr[1].equals("1")) {
                    t.markItem();
                }
                items.add(t);
            }
        }

    }

    public TaskList() {
        this.items = new ArrayList<>();
        this.taskFileIo = new TaskListFileIo();
    }

    public int getListSize() {
        return this.items.size();
    }

    public Task getItem(int index) {
        return this.items.get(index);
    }

    public void loadTaskListFromFile() throws IOException {
        this.taskFileIo.loadFile();
    }

    public void addItem(Task item) {
        this.items.add(item);
    }

    public void markItemDone(int index) {
        if (index < 0 || index >= this.getListSize()) return;
        Task currItem = this.items.get(index);
        currItem.markItem();

    }

    public void unmarkItemDone(int index) {
        if (index < 0 || index >= this.getListSize()) return;
        Task currItem = this.items.get(index);
        currItem.unmarkItem();
    }

    public Task deleteItem(int index) {
        if (index < 0 || index >= this.getListSize()) return null;
        return this.items.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < items.size(); ++i) {
            if (i > 0) output.append("\t");
            output.append(i + 1).append(".  ").append(items.get(i).toString());
            if (i < items.size() - 1) output.append("\n");
        }
        return output.toString();
    }
}
