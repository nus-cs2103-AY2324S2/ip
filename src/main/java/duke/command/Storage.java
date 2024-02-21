package duke.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Loads tasks from the file and saves tasks in file.
 */
public class Storage {
    private File file;
    private String filePath;

    /**
     * Storage constructor.
     *
     * @param filePath      Location of save file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads tasks from save file.
     *
     * @return taskList     List of tasks retrieved from save file.
     * @throws IOException  If scanner cannot read next line.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(s, "|");

            String t = st.nextToken().strip();
            boolean isDone = st.nextToken().strip().equalsIgnoreCase("1");
            String description = st.nextToken().strip();

            if (t.equalsIgnoreCase("t")) {
                tasks.add(new ToDo(description, isDone));

            } else if (t.equalsIgnoreCase("e")) {
                String from = st.nextToken().strip();
                tasks.add(new Event(description, isDone, from, st.nextToken().strip()));

            } else if (t.equalsIgnoreCase("d")) {
                tasks.add(new Deadline(description, isDone, st.nextToken().strip()));

            } else {
                System.out.println("File error, cannot read list");
            }
        }
        scanner.close();

        return tasks;
    }

    /**
     * Adds new task to file.
     *
     * @param task          New task to save to file.
     * @throws IOException  If FileWriter cannot access/write to file.
     */
    public void addNewTask(Task task) throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        FileWriter fw = new FileWriter(file, true);

        if (file.length() == 0) {
            fw.write(task.saveFileString());
        } else {
            fw.write(System.lineSeparator() + task.saveFileString());
        }

        fw.close();
    }

    /**
     * Deletes task from file.
     *
     * @param index         Index of task to be deleted.
     * @param numOfTasks    Total number of tasks in task list.
     * @throws IOException  If unable to access/read/write to file.
     */
    public void deleteTask(int index, int numOfTasks) throws IOException {
        File oldFile = file;
        File temp = new File("./data/temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));

        for (int i = 0; i < numOfTasks; ++i) {
            String currentLine = br.readLine();

            if (i == index) {
                continue;
            }

            if (index == numOfTasks - 1 && i == numOfTasks - 2) {
                bw.write(currentLine);
            } else if (i < numOfTasks - 1) {
                bw.write(currentLine + System.lineSeparator());
            } else {
                bw.write(currentLine);
            }
        }

        bw.close();
        br.close();

        oldFile.delete();
        temp.renameTo(new File(filePath));

        assert !oldFile.exists();
        assert file.exists();
    }

    /**
     * Updates task saved in file.
     *
     * @param task          Updated task.
     * @param index         Index of task to be deleted.
     * @param numOfTasks    Total number of tasks in task list.
     * @throws IOException  If unable to access/read/write to file.
     */
    public void updateTask(Task task, int index, int numOfTasks) throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        String updated = task.saveFileString();
        File oldFile = file;
        File temp = new File("./data/temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));

        for (int i = 0; i < numOfTasks; ++i) {
            String currentLine = br.readLine();

            if (i == (index - 1)) {
                bw.write(updated);
            } else {
                bw.write(currentLine);
            }

            if (i == numOfTasks - 1) {
                continue;
            }
            bw.write(System.lineSeparator());
        }

        bw.close();
        br.close();

        oldFile.delete();
        temp.renameTo(new File("./data/duke.txt"));

        assert !oldFile.exists();
        assert file.exists();
    }

    /**
     * Handles missing directory and file creating.
     *
     * @throws IOException  If unable to create file.
     */
    private void handleFileAccessErrors() throws IOException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            new File("./data").mkdirs();
        } finally {
            file.createNewFile();
        }

        assert file.exists();
    }
}
