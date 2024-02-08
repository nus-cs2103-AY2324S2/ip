package me.ruibin.leto.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.ruibin.leto.tasklist.Deadline;
import me.ruibin.leto.tasklist.Event;
import me.ruibin.leto.tasklist.InvalidTaskException;
import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.tasklist.Todo;
import me.ruibin.leto.ui.Ui;

/**  returns <code>Results.OK</code>*/
public class Storage {
    private static final String PATH_TO_STORE = "Leto-Tasks.csv";

    /**
     * Read the files, takes in a ArrayList of taskLists and
     * populates it. Throws Error if file is invalid.
     * If file is not found, will create one by default.
     */
    public static void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH_TO_STORE));
            Ui.letoSpeak("We found an existing file, will attempt to import it to the list\nGive me a while");
            File f = new File(PATH_TO_STORE);
            String absPath = f.getAbsolutePath();
            Ui.shortSay("File path: " + absPath);
            Stream<String> s = br.lines();
            List<String> l = s.collect(Collectors.toList());
            for (String entry : l) {
                readEntry(entry);
            }
            Ui.shortSay("Import completed");
        } catch (FileNotFoundException e) {
            Ui.letoSpeak("We did not find an existing file containing tasks"
                    + "\n  We will carry on without a blank list");
        } catch (UncheckedIOException e) {
            Ui.letoSpeak("Blast! Ran into error while reading the file.\n"
                    + "We will proceed with an empty list.\n");
        } catch (InvalidTaskException e) {
            Ui.letoSpeak("File might be corrupted\n"
                    + "Full error report below: " + e);
        }
    }

    /**
     * Read an entry from the file into a Task and add it to the list.
     * Throws Error if entry is invalid.
     * If file is not found, will create one by default.
     *
     * @throws InvalidTaskException entry cannot be parsed.
     */
    private static void readEntry(String entry) throws InvalidTaskException {
        String[] parts = entry.split(",");
        switch (parts[0]) {
        case "T":
            TaskList.addTaskToList(Todo.todoFromCsv(entry));
            Ui.shortSay("Todo added");
            break;
        case "D":
            TaskList.addTaskToList(Deadline.deadlineFromCsv(entry));
            Ui.shortSay("Deadline added");
            break;
        case "E":
            TaskList.addTaskToList(Event.eventFromCsv(entry));
            Ui.shortSay("Event added");
            break;
        default:
            throw new InvalidTaskException("Bad command for creating new entry");
        }
    }

    /**
     * Write the current task lists to a csv file.
     */
    public static void writeFile() {
        int saved = 0;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TO_STORE));
            Ui.letoSpeak("Saving tasks");
            for (Task t : TaskList.getTasks()) {
                writeTask(bw, t);
                saved++;
            }
            bw.flush();
            bw.close();
            Ui.shortSay("Saving completed: " + saved + " tasks saved");
            File f = new File(PATH_TO_STORE);
            String absPath = f.getAbsolutePath();
            Ui.shortSay("File path: " + absPath);
        } catch (IOException e) {
            Ui.letoSpeak("Blast! Ran into error while writing the file.\n"
                    + "Saved " + saved + " tasks in the list.\n");
        }
    }

    /**
     * Helper method for writing an individual task to BufferedWriter.
     * Used within WriteFile.
     *
     * @param bw BufferedWriter for the csv file.
     * @param t Task to write.
     * @throws IOException Pass up possible IOException from bw write.
     */
    private static void writeTask(BufferedWriter bw, Task t) throws IOException {
        bw.write(t.toCsvString() + "\n");
    }
}
