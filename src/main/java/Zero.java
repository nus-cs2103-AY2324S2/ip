import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Zero {
    private static final String name = "Zero";
    private static final File saveFile = new File("data/save.ser");

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        saveFile.getParentFile().mkdirs();
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        ArrayList<Task> tasks;
        try {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException | ClassNotFoundException | ClassCastException |
            EOFException | StreamCorruptedException e) {
            tasks = new ArrayList<>();
        }
        saveTasks(tasks, saveFile); // Rewrite task data as readObject deletes save data

        pw.printf(Messages.GREET.toString(), name);
        pw.flush();
        String[] cmd;
        int r; // Temporary integer for results
        Hashtable<String, String> dict; // Temporary dictionary for results
        String s; // Temporary string for results
        Task t; // Temporary task
        do {
            pw.print(">>>");
            pw.flush();
            cmd = br.readLine().split(" ");
            switch (cmd[0]) {
                case "bye":
                    pw.println(Messages.BYE);
                    break;
                case "list":
                    String list = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        list += "\n" + (i+1) + "." + tasks.get(i);
                    }
                    pw.printf(Messages.LIST.toString(), list);
                    break;
                case "mark":
                    r = checkIndex(cmd[1], pw, tasks);
                    if (r == -1) break;
                    tasks.set(r, tasks.get(r).mark());
                    saveTasks(tasks, saveFile);
                    pw.printf(Messages.MARKED.toString(), tasks.get(r));
                    break;
                case "unmark":
                    r = checkIndex(cmd[1], pw, tasks);
                    if (r == -1) break;
                    tasks.set(r, tasks.get(r).unmark());
                    saveTasks(tasks, saveFile);
                    pw.printf(Messages.UNMARKED.toString(), tasks.get(r));
                    break;
                case "delete":
                    r = checkIndex(cmd[1], pw, tasks);
                    if (r == -1) break;
                    t = tasks.remove(r);
                    saveTasks(tasks, saveFile);
                    pw.printf(Messages.REMOVE.toString(), t, tasks.size());
                    break;
                case "todo":
                    dict = filterArguements(cmd);
                    s = dict.get("name");
                    if (isNullOrEmpty(s)) {
                        pw.printf(Messages.MISSING_TASK_NAME.toString(), "ToDo");
                        break;
                    }
                    t = new ToDo(s);
                    tasks.add(t);
                    saveTasks(tasks, saveFile);
                    pw.printf(Messages.ADD.toString(), t, tasks.size());
                    break;
                case "deadline":
                    dict = filterArguements(cmd);
                    s = dict.get("name");
                    if (isNullOrEmpty(s)) {
                        pw.printf(Messages.MISSING_TASK_NAME.toString(), "Deadline");
                        break;
                    }
                    String deadlineBy = dict.get("/by");
                    if (isNullOrEmpty(deadlineBy)) {
                        pw.println(Messages.INVALID_DEADLINE_DATE);
                        break;
                    }
                    t = new Deadline(s, deadlineBy);
                    tasks.add(t);
                    saveTasks(tasks, saveFile);
                    pw.printf(Messages.ADD.toString(), t, tasks.size());
                    break;
                case "event":
                    dict = filterArguements(cmd);
                    s = dict.get("name");
                    if (isNullOrEmpty(s)) {
                        pw.printf(Messages.MISSING_TASK_NAME.toString(), "Event");
                        break;
                    }
                    String eventFrom = dict.get("/from");
                    if (isNullOrEmpty(eventFrom)) {
                        pw.println(Messages.INVALID_EVENT_FROM);
                        break;
                    }
                    String eventTo = dict.get("/to");
                    if (isNullOrEmpty(eventTo)) {
                        pw.println(Messages.INVALID_EVENT_TO);
                        break;
                    }
                    t = new Event(s, eventFrom, eventTo);
                    tasks.add(t);
                    saveTasks(tasks, saveFile);
                    pw.printf(Messages.ADD.toString(), t, tasks.size());
                    break;
                default:
                    pw.printf(Messages.INVALID_CMD.toString(), String.join(" ", cmd));
                    break;
            }
            pw.flush();
        } while (!cmd[0].equals("bye"));

        br.close();
        pw.close();
    }
}
