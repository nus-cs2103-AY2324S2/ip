package dylanbot;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadDataFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String nextLine;
        ArrayList<Task> res = new ArrayList<>();
        try {
            while ((nextLine = reader.readLine()) != null) {
                String[] tokens = nextLine.split(Pattern.quote(" | "));
                Task curr;
                if (tokens[0].equals("T")) {
                    curr = new TodoTask("T", tokens[2]);
                } else if (tokens[0].equals("D")) {
                    curr = new DeadlineTask("D", tokens[2], ConvertStringToDateTime(tokens[3]));
                } else {
                    curr = new EventTask("E", tokens[2], ConvertStringToDateTime(tokens[3]),
                            ConvertStringToDateTime(tokens[4]));
                }
                curr.completed = tokens[1].equals("true");
                res.add(curr);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }

    public void saveDataToFile(TaskList tl) throws IOException {
        ArrayList<Task> tasks = tl.getTasks();
        File newFile = new File(filePath);
        newFile.getParentFile().mkdirs();
        newFile.createNewFile();

        FileWriter writer = new FileWriter(filePath);
        for (Task t : tasks) {
            String data = "";
            data += t.type + " | " + t.completed + " | " + t.desc;
            if (t.type.equals("D")) {
                data += " | " + ((DeadlineTask) t).deadline;
            } else if (t.type.equals("E")) {
                data += " | " + ((EventTask) t).from + " | " + ((EventTask) t).to;
            }
            writer.write(data);
            writer.write("\n");
        }
        writer.close();
    }


    public static LocalDateTime ConvertStringToDateTime(String input) throws DateTimeParseException {
        LocalDateTime deadline = null;
        if (input.length() < 11) {
            deadline = LocalDate.parse(input).atStartOfDay();
        } else {
            deadline = LocalDateTime.parse(input);
        }
        return deadline;
    }
}

