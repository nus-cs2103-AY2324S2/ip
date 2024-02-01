import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Zero {
    private static final String NAME_STRING = "Zero";
    private static final File SAVE_FILE = new File("data/save.ser");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_INPUT = 
            DateTimeFormatter.ofPattern("d/M/uu HHmm");
    private static final String DATE_TIME_INPUT_SIMPLE_STRING = "dd/mm/yy HHMM"; // For display to users
    private static final DateTimeFormatter DATE_TIME_FORMATTER_OUTPUT = 
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu hh:mm a");
    
    // Checks if the string is null, empty or filled with spaces
    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty() || s.trim().isEmpty();
    }

    // For Mark, Unmark, Delete commands
    // Checks if task index supplied is valid, returns -1 for invalid index
    private static int checkIndex(String s, PrintWriter pw, ArrayList<Task> tasks) {
        int idx;
        try{
            idx = Integer.parseInt(s) - 1;
        } catch (NumberFormatException e) {
            pw.println(Messages.INVALID_TASK_NUMBER);
            return -1;
        }
        if (idx < 0 || idx >= tasks.size()) {
            pw.printf(Messages.OUT_OF_RANGE_TASK_NUMBER.toString(), tasks.size());
            return -1;
        }
        return idx;
    }

    // For ToDo, Deadline, Event commands
    // Filter input arguements and returns keys for name, /by, /from, /to
    private static Hashtable<String, String> filterArguements(String[] s) {
        Hashtable<String, String> result = new Hashtable<>(3);
        String type = "name";
        StringBuilder arg = new StringBuilder();
        for (int i = 1; i < s.length; i++) {
            if (s[i].startsWith("/")) {
                result.put(type, arg.toString().trim());
                type = s[i];
                arg.setLength(0);
            } else {
                if (!isNullOrEmpty(s[i])) {
                    arg.append(" ");
                    arg.append(s[i]);
                }
            }
        }
        result.put(type, arg.toString().trim());
        return result;
    }
    
    // Updates the save file with the task data
    // Reopen and close the ObjectOutputStream each time to override the file and update properly
    private static void saveTasks(ArrayList<Task> tasks, File saveFile) throws IOException {
        try{
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        // Initialise Input reader and Output writer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        // Set Date Time formats for relevant classes
        Deadline.dtf = DATE_TIME_FORMATTER_OUTPUT;
        Event.dtf = DATE_TIME_FORMATTER_OUTPUT;

        // Create or Load save file into ArrayList Tasks
        SAVE_FILE.getParentFile().mkdirs();
        if (!SAVE_FILE.exists()) {
            SAVE_FILE.createNewFile();
        }
        ArrayList<Task> tasks;
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException | ClassNotFoundException | ClassCastException |
                EOFException | StreamCorruptedException | InvalidClassException e) {
            tasks = new ArrayList<>();
        }
        saveTasks(tasks, SAVE_FILE); // Rewrite task data as readObject deletes save data

        // Startup message
        pw.printf(Messages.GREET.toString(), NAME_STRING);
        pw.flush();

        // Start command loop, exit on bye
        String[] cmd;
        do {
            pw.print(">>>");
            pw.flush();
            cmd = br.readLine().split(" ");
            switch (cmd[0]) {
            case "bye": {
                pw.println(Messages.BYE);
                break;
            }
            case "list": {
                String list = "";
                for (int i = 0; i < tasks.size(); i++) {
                    list += "\n" + (i+1) + "." + tasks.get(i);
                }
                pw.printf(Messages.LIST.toString(), list);
                break;
            }
            case "mark": {
                int r = checkIndex(cmd[1], pw, tasks);
                if (r == -1) break;
                tasks.set(r, tasks.get(r).mark());
                saveTasks(tasks, SAVE_FILE);
                pw.printf(Messages.MARKED.toString(), tasks.get(r));
                break;
            }
            case "unmark": {
                int r = checkIndex(cmd[1], pw, tasks);
                if (r == -1) break;
                tasks.set(r, tasks.get(r).unmark());
                saveTasks(tasks, SAVE_FILE);
                pw.printf(Messages.UNMARKED.toString(), tasks.get(r));
                break;
            }
            case "delete": {
                int r = checkIndex(cmd[1], pw, tasks);
                if (r == -1) break;
                Task t = tasks.remove(r);
                saveTasks(tasks, SAVE_FILE);
                pw.printf(Messages.REMOVE.toString(), t, tasks.size());
                break;
            }
            case "todo": {
                Hashtable<String, String> d = filterArguements(cmd);
                String s = d.get("name");
                if (isNullOrEmpty(s)) {
                    pw.printf(Messages.MISSING_TASK_NAME.toString(), "ToDo");
                    break;
                }
                Task t = new ToDo(s);
                tasks.add(t);
                saveTasks(tasks, SAVE_FILE);
                pw.printf(Messages.ADD.toString(), t, tasks.size());
                break;
            }
            case "deadline": {
                Hashtable<String, String> d = filterArguements(cmd);
                String s = d.get("name");
                if (isNullOrEmpty(s)) {
                    pw.printf(Messages.MISSING_TASK_NAME.toString(), "Deadline");
                    break;
                }
                String deadlineBy = d.get("/by");
                LocalDateTime ldt;
                try {
                    ldt = LocalDateTime.parse(deadlineBy, DATE_TIME_FORMATTER_INPUT);
                } catch (NullPointerException | DateTimeParseException e) {
                    pw.printf(Messages.INVALID_DEADLINE_DATE.toString(), DATE_TIME_INPUT_SIMPLE_STRING);
                    break;
                }
                Task t = new Deadline(s, ldt);
                tasks.add(t);
                saveTasks(tasks, SAVE_FILE);
                pw.printf(Messages.ADD.toString(), t, tasks.size());
                break;
            }
            case "event": {
                Hashtable<String, String> dict = filterArguements(cmd);
                String s = dict.get("name");
                if (isNullOrEmpty(s)) {
                    pw.printf(Messages.MISSING_TASK_NAME.toString(), "Event");
                    break;
                }
                String eventFrom = dict.get("/from");
                LocalDateTime ldtFrom;
                try {
                    ldtFrom = LocalDateTime.parse(eventFrom, DATE_TIME_FORMATTER_INPUT);
                } catch (NullPointerException | DateTimeParseException e) {
                    pw.printf(Messages.INVALID_EVENT_FROM.toString(), DATE_TIME_INPUT_SIMPLE_STRING);
                    break;
                }
                String eventTo = dict.get("/to");
                LocalDateTime ldtTo;
                try {
                    ldtTo = LocalDateTime.parse(eventTo, DATE_TIME_FORMATTER_INPUT);
                } catch (NullPointerException | DateTimeParseException e) {
                    pw.printf(Messages.INVALID_EVENT_TO.toString(), DATE_TIME_INPUT_SIMPLE_STRING);
                    break;
                }
                Task t = new Event(s, ldtFrom, ldtTo);
                tasks.add(t);
                saveTasks(tasks, SAVE_FILE);
                pw.printf(Messages.ADD.toString(), t, tasks.size());
                break;
            } 
            default: {
                pw.printf(Messages.INVALID_CMD.toString(), cmd[0]);
                break;
            } 
            }
            pw.flush(); // Print any Strings remaining in buffer before next command
        } while (!cmd[0].equals("bye"));

        // Close Input reader and Output writer
        br.close();
        pw.close();
    }
}
