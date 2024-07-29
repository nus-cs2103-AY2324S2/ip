package datuk;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import datuk.task.Task;
import datuk.task.Todo;
import datuk.task.Deadline;
import datuk.task.Event;

/**
 * The class handles reading and writing to save the file.
 */

public class Storage {
    protected File path;

    /**
     * Returns a list of tasks from data/data.txt.
     * <p>
     * The method will attempt to load the data in the file if the folder exists, and will create the folder
     * if it does not.
     *
     * @return an ArrayList of type Task.
     * @throws IOException when the file fails to be created.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        path = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/data");

        if (!path.exists()) {
            boolean isCreated = path.mkdir();

            assert isCreated : "path should be created";

            Files.createFile(Paths.get(path.toString() + "/data.txt"));
        }

        return parseText();
    }

    private ArrayList<Task> parseText() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        BufferedReader  br = Files.newBufferedReader(Paths.get(path.toString() + "/data.txt"));

        while(br.ready()) {
            String[] text = br.readLine().split(Pattern.quote(" | "));

            if (text[0].isEmpty()) {
                break;
            }

            if (text[0].equals("T")) {

                assert text.length == 3 : "length should be 3";

                Todo temp = new Todo(text[2]);
                temp.setCheck(text[1].equals("1"));
                tasks.add(temp);
            } else if (text[0].equals("D")) {

                assert text.length == 4 : "length should be 4";

                Deadline temp = new Deadline(text[2], LocalDate.parse(text[3]));
                temp.setCheck(text[1].equals("1"));
                tasks.add(temp);
            } else {

                assert text.length == 4 : "length should be 4";

                String[] time = text[3].split("-");
                Event temp = new Event(text[2], time[0], time[1]);
                temp.setCheck(text[1].equals("1"));
                tasks.add(temp);
            }
        }

        return tasks;
    }

    /**
     * This method iterates through all tasks in TaskList and appends the data into a StringBuilder.
     * When the TaskList has been iterated through, this method will write the data to the file data/data.txt.
     *
     * @param tasks The list of Tasks.
     * @throws IOException when the File cannot be written to.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        StringBuilder s = new StringBuilder();

        for(Task t : tasks) {
            if(t.getType().equals("T")) {
                s.append(t.toSave());
            } else if(t.getType().equals("D")) {
                Deadline d = (Deadline) t;
                s.append(d.toSave());
            } else {
                Event e = (Event) t;
                s.append(e.toSave());
            }
        }

        String str = s.toString();

        java.nio.file.Files.write(Paths.get(path.toString() + "/data.txt"),
                str.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
