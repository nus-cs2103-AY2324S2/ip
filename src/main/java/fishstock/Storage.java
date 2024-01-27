package fishstock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    private final ArrayList<Task> list = new ArrayList<>();
    private final File db;
    private final String filePath;

    protected Storage(String filePath) {
        db = new File(filePath);
        this.filePath = filePath;
    }

    private void loadTask(String line) {
        String[] arr = line.split("\\|");
        Task task = null;
        if ("T".equals(arr[0])) { // Todo
            task = new Todo(arr[1]);
        } else if ("D".equals(arr[0])) { // Deadline
            task = new Deadline(arr[1], Parser.parseDate(arr[2]));
        } else if ("E".equals(arr[0])) { // Event
            task = new Event(arr[1], Parser.parseDate(arr[2]), Parser.parseDate(arr[3]));
        }

        if (task == null) { // invalid format
            return;
        }

        if (arr[arr.length - 1].equals("1")) {
            task.markAsDone();
        }
        list.add(task);
    }

    protected ArrayList<Task> load() throws FishStockException {
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNext()) {
                loadTask(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            String pathToDb = filePath.substring(0, filePath.lastIndexOf("/"));
            new File(pathToDb).mkdir();
            throw new FishStockException("File not found... Starting new session...\n");
        }
        return list;
    }

    protected void close() {
        try {
            FileWriter fw = new FileWriter(db);
            for (Task task : list) {
                fw.write(task.toSaveString());
            }
            fw.close();

        } catch (IOException e) {
            Ui.printError(e.getMessage());
        }
    }
}
