import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                tasks.add(Task.fromStorageString(str));
            }
        } catch (IOException e) {
            throw new DukeException("IO error: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tasks;
    }

    public void writeTasks(ArrayList<Task> tasks) throws DukeException {
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toStorageString()).append('\n');
        }
        String toWrite = sb.toString();
        try {
            pw = new PrintWriter(new File(path));
            pw.write(toWrite);
            pw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("IO Error encountered while writing: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}