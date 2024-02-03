package dylanbot;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private final String FILE_PATH;
    private final Ui ui;
    public Storage(String FILE_PATH, Ui ui) {
        this.FILE_PATH = FILE_PATH;
        this.ui = ui;
    }

    public ArrayList<Task> loadDataFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String nextLine;
        ArrayList<Task> res = new ArrayList<>();
        try {
            while ((nextLine = reader.readLine()) != null) {
                String[] tokens = nextLine.split(Pattern.quote(" | "));
                Task curr;
                if (tokens[0].equals("T")) {
                    curr = new TodoTask(tokens[2]);
                } else if (tokens[0].equals("D")) {
                    curr = new DeadlineTask(tokens[2], convertStringToDateTime(tokens[3]));
                } else {
                    curr = new EventTask(tokens[2], convertStringToDateTime(tokens[3]),
                            convertStringToDateTime(tokens[4]));
                }
                if (tokens[1].equals("true")) {
                    curr.mark();
                }
                res.add(curr);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }

    public void saveDataToFile(TaskList tl) throws IOException {
        ArrayList<Task> tasks = tl.getTasks();
        File newFile = new File(FILE_PATH);
        newFile.getParentFile().mkdirs();
        newFile.createNewFile();

        FileWriter writer = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            String data = "";
            data += t.getType() + " | " + t.isCompleted() + " | " + t.getDesc();
            if (t.getType().equals("D")) {
                data += " | " + ((DeadlineTask) t).deadline;
            } else if (t.getType().equals("E")) {
                data += " | " + ((EventTask) t).from + " | " + ((EventTask) t).to;
            }
            writer.write(data);
            writer.write("\n");
        }
        writer.close();
    }


    public static LocalDateTime convertStringToDateTime(String input) throws DateTimeParseException {
        LocalDateTime deadline;
        if (input.length() < 11) {
            deadline = LocalDate.parse(input).atStartOfDay();
        } else {
            deadline = LocalDateTime.parse(input);
        }
        return deadline;
    }
}

