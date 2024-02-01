package leto.storage;

import leto.tasklist.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static leto.ui.Ui.letoSpeak;
import static leto.ui.Ui.shortSay;

public class Storage {
    private static final String PATH_TO_STORE = "Leto-Tasks.csv";

    /**
     * Read the files, takes in a ArrayList of taskLists and
     * populates it. Throws Error if file is invalid.
     * If file is not found, will create one by default.
     */
    public static void ReadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH_TO_STORE));
            letoSpeak("We found an existing file, will attempt to import it to the list\nGive me a while");
            File f = new File(PATH_TO_STORE);
            String absPath = f.getAbsolutePath();
            shortSay("File path: " + absPath);
            Stream<String> s = br.lines();
//            s.forEach(Storage::ReadEntry);
            List<String> l = s.collect(Collectors.toList());
            for (String entry : l) {
                ReadEntry(entry);
            }
            shortSay("Import completed");
        } catch (FileNotFoundException e) {
            letoSpeak("We did not find an existing file containing tasks"
             + "\n  We will carry on without a blank list");
            return;
        } catch (UncheckedIOException e) {
            letoSpeak("Blast! Ran into error while reading the file.\n"
                    + "We will proceed with an empty list.\n");
        } catch (InvalidTaskException e) {
            letoSpeak("File might be corrupted\n"
                    + "Full error report below: " + e.toString());
        }
    }

    private static void ReadEntry(String entry) throws InvalidTaskException {
        String[] parts = entry.split(",");
        switch (parts[0]) {
        case "T":
            TaskList.addTaskToList(Todo.TodoFromCSV(entry));
            shortSay("Todo added");
            break;
        case "D":
            TaskList.addTaskToList(Deadline.DeadlineFromCSV(entry));
            shortSay("Deadline added");
            break;
        case "E":
            TaskList.addTaskToList(Event.EventFromCSV(entry));
            shortSay("Event added");
            break;
        }
    }

    public static void WriteFile(ArrayList<Task> taskLists) {
        int saved = 0;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TO_STORE));
            letoSpeak("Saving tasks");
            for (Task t : TaskList.getTasks()) {
                WriteTask(bw, t);
                saved++;
            }
            bw.flush();
            bw.close();
            shortSay("Saving completed: " + saved + " tasks saved");
            File f = new File(PATH_TO_STORE);
            String absPath = f.getAbsolutePath();
            shortSay("File path: " + absPath);
        } catch (IOException e) {
            letoSpeak("Blast! Ran into error while writing the file.\n"
                    + "Saved " + saved + " tasks in the list.\n");
        }
    }

    private static void WriteTask(BufferedWriter bw, Task t) throws IOException {
        bw.write(t.toCSVString() + "\n");
    }
}
