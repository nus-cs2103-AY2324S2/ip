package dylanbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private final String filePath;
    private final Ui ui;
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
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
        File newFile = new File(filePath);
        newFile.getParentFile().mkdirs();
        newFile.createNewFile();

        FileWriter writer = new FileWriter(filePath);
        for (Task t : tasks) {
            String data = "";
            data += t.getType() + " | " + t.isCompleted() + " | " + t.getDesc();
            if (t.getType().equals("D")) {
                data += " | " + ((DeadlineTask) t).getDeadline();
            } else if (t.getType().equals("E")) {
                data += " | " + ((EventTask) t).getFrom() + " | " + ((EventTask) t).getTo();
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

