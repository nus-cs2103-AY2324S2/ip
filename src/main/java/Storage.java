import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Storage {
    protected static File path;
    public static ArrayList<Task> loadTasks() throws IOException {
        path = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/data");

        if (!path.exists()) {
            path.mkdir();
            Files.createFile(Paths.get(path.toString() + "/data.txt"));
        }
        return parseText();
    }

    private static ArrayList<Task> parseText() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        BufferedReader br = Files.newBufferedReader(Paths.get(path.toString()+"/data.txt"));

        while(br.ready()) {
            String[] text = br.readLine().split(Pattern.quote(" | "));

            if (text[0].isEmpty()) break;

            if (text[0].equals("T")) {
                Todo temp = new Todo(text[2]);
                temp.setCheck(text[1].equals("1"));
                tasks.add(temp);
            } else if (text[0].equals("D")) {
                Deadline temp = new Deadline(text[2], text[3]);
                temp.setCheck(text[1].equals("1"));
                tasks.add(temp);
            } else {
                String[] time = text[3].split("-");
                Event temp = new Event(text[2], time[0], time[1]);
                temp.setCheck(text[1].equals("1"));
                tasks.add(temp);
            }
        }

        return tasks;
    }
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
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
