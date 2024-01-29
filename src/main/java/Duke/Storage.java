package Duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    Ui ui;
    Parser parser;

    public Storage (Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    public String formatTaskForFile(Task task) {
        String returnString = "";
        String type =
                task instanceof Todo ? "T" :
                        task instanceof Deadline ? "D" :
                                task instanceof Event ? "E" : "";
        int status = task.isDone();
        String details = task.getDescription();
        returnString += type + " | " + status + " | " + details;
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            returnString += " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            returnString += " | " + event.getFrom() + " | " + event.getTo();
        }
        return returnString;
    }
    public void saveTaskToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter("./data/duke.txt", true);
            String taskLine = formatTaskForFile(task);
            fileWriter.write(taskLine + "\n");
            fileWriter.close();
        } catch (IOException e) {
            ui.saveError(e);
        }

    }
    public void saveAllTasksToFile(ArrayList<Task> taskList) {
        // overwrites all files for mark/unmark functions
        try {
            FileWriter fileWriter = new FileWriter("./data/duke.txt", false);
            for (Task task : taskList) {
                String taskLine = formatTaskForFile(task);
                fileWriter.write(taskLine + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            ui.saveError(e);
        }
    }
}
