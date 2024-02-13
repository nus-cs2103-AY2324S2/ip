package me.ruibin.leto.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import me.ruibin.leto.parser.Result;
import me.ruibin.leto.parser.ResultTypes;
import me.ruibin.leto.tasklist.Deadline;
import me.ruibin.leto.tasklist.Event;
import me.ruibin.leto.tasklist.InvalidTaskException;
import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.tasklist.Todo;
import me.ruibin.leto.ui.Ui;

/**  returns <code>ResultTypes.OK</code>*/
public class Storage {
    private static final String PATH_TO_STORE = "Leto-Tasks.csv";

    /**
     * Read the files, takes in a ArrayList of taskLists and
     * populates it. Throws Error if file is invalid.
     * If file is not found, will create one by default.
     */
    public static Result readFile() {
        Result result = Result.makeResultOk("");
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH_TO_STORE));
            result.updateMessage(
                Ui.letoSpeak("We found an existing file, will attempt to import it to the list\nGive me a while")
            );
            File f = new File(PATH_TO_STORE);
            String absPath = f.getAbsolutePath();
            result.updateMessage(Ui.shortSay("File path: " + absPath));
            String entry = br.readLine();
            List<Task> taskList = new ArrayList<>();
            while (entry != null) {
                taskList.add(readEntry(entry));
                entry = br.readLine();
            }
            Task[] t = new Task[taskList.size()];
            taskList.toArray(t);
            TaskList.addToList(t);
            return result;
        } catch (FileNotFoundException e) {
            String message = Ui.letoSpeak("We did not find an existing file containing tasks"
                    + "\n  We will carry on without a blank list");
            result.updateWithException(message, e);
        } catch (UncheckedIOException | IOException e) {
            String message = Ui.letoSpeak("Blast! Ran into error while reading the file.\n"
                    + "We will proceed with an empty list.\n");
            result.updateWithException(message, e);
        } catch (InvalidTaskException e) {
            String message = Ui.letoSpeak("File might be corrupted\n"
                    + "Full error report below: " + e);
            result.updateWithException(message, e);
        }
        return result;
    }

    /**
     * Read an entry from the file into a Task and returns the task object.
     * Throws Error if entry is invalid.
     * If file is not found, will create one by default.
     *
     * @return A task specified by the csv entry
     * @throws InvalidTaskException entry cannot be parsed.
     */
    private static Task readEntry(String entry) throws InvalidTaskException {
        String[] parts = entry.split(",");
        switch (parts[0]) {
        case "T":
            return Todo.todoFromCsv(entry);
        case "D":
            return Deadline.deadlineFromCsv(entry);
        case "E":
            return Event.eventFromCsv(entry);
        default:
            throw new InvalidTaskException("Bad command for creating new entry");
        }
    }

    /**
     * Write the current task lists to a csv file.
     */
    public static Result writeFile() {
        int saved = 0;
        Result result = Result.makeResultOk("");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TO_STORE));
            result.updateMessage(Ui.letoSpeak("Saving tasks"));
            for (Task t : TaskList.getTasks()) {
                writeTask(bw, t);
                saved++;
            }
            bw.flush();
            bw.close();
            result.updateMessage(Ui.shortSay("Saving completed: " + saved + " tasks saved"));
            File f = new File(PATH_TO_STORE);
            String absPath = f.getAbsolutePath();
            result.updateMessage(Ui.shortSay("File path: " + absPath));
        } catch (IOException e) {
            String message = Ui.letoSpeak("Blast! Ran into error while writing the file.\n"
                    + "Saved " + saved + " tasks in the list.\n");
            result.updateWithException(message, e);
        }
        return result;
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
