import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(s, "|");

            String t = st.nextToken().strip();
            boolean done = st.nextToken().strip().equalsIgnoreCase("1");
            String description = st.nextToken().strip();

            if (t.equalsIgnoreCase("t")) {
                tasks.add(new ToDo(description, done));

            } else if (t.equalsIgnoreCase("e")) {
                String from = st.nextToken().strip();
                tasks.add(new Event(description, done, from, st.nextToken().strip()));

            } else if (t.equalsIgnoreCase("d")) {
                tasks.add(new Deadline(description, done, st.nextToken().strip()));

            } else {
                System.out.println("File error, cannot read list");
            }
        }
        scanner.close();

        return tasks;
    }

    public void addNewTask(Task task) throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        FileWriter fw = new FileWriter(file, true);

        if (file.length() == 0) {
            fw.write(task.getSaveTask());
        } else {
            fw.write(System.lineSeparator() + task.getSaveTask());
        }

        fw.close();
    }

    public void deleteTask(int index, int numOfTasks) throws IOException{
        File oldFile = file;
        File temp = new File("./data/temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));

        for (int i = 0; i < numOfTasks; ++i) {
            String currentLine = br.readLine();

            if (i != index) {
                System.out.println(currentLine);

                if (i < numOfTasks - 1) {
                    bw.write(currentLine + System.lineSeparator());
                } else {
                    bw.write(currentLine);
                }
            }

        }

        bw.close();
        br.close();

        oldFile.delete();
        temp.renameTo(new File(filePath));
    }

    public void updateTask(Task task, int num, int size) throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        String updated = task.getSaveTask();
        File oldFile = file;
        File temp = new File("./data/temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));

        for (int i = 0; i < size; ++i) {
            String currentLine = br.readLine();

            if (i == (num - 1)) {
                bw.write(updated + System.lineSeparator());
            } else {
                bw.write(currentLine + System.lineSeparator());
            }
        }

        bw.close();
        br.close();

        oldFile.delete();
        temp.renameTo(new File("./data/duke.txt"));
    }

    // Move to error handling class?
    private void handleFileAccessErrors() throws IOException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            new File("./data").mkdirs();
        } finally {
            file.createNewFile();
        }
    }
}