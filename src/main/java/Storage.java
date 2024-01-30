import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File tasksFile;
    private String FILENAME = "./data/duke.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private TaskList taskList;

    public Storage() {
        this.taskList = new TaskList();

    }

    public TaskList loadTasks() throws DukeException {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                throw new DukeException("hey");
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] splitLine = line.split(" \\| ");
                    String event = splitLine[2];
                    String type = splitLine[0];
                    Task newTask;
                    if (splitLine.length < 3 || splitLine.length > 4) {
                        throw new DukeException("Erm... Textfile not in correct format!" + splitLine.length);
                    }
                    if (type.equalsIgnoreCase("T")) {
                        newTask = new Todo(event);
                    } else if (type.equalsIgnoreCase("D")) {
                        String extraInfo = splitLine[3];
                        checkDateFormat(extraInfo);

                        newTask = new Deadline(event, extraInfo);
                    } else if (type.equalsIgnoreCase("E")) {
                        String extraInfo = splitLine[3];
                        newTask = new Event(event, extraInfo);
                    } else {
                        throw new DukeException("Erm... Invalid type!" + type);
                    }
                    this.taskList.add(newTask);

                }
                s.close();
            }
            return this.taskList;
        } catch (IOException e) {
            throw new DukeException("Erm... Error loading tasks from file");
        }
    }

    public void saveTasks(Task newTask) throws DukeException {
        String msg = " | 0 | ";
        msg = "\n" + newTask.getType() + msg + newTask.getDescription();
        if (newTask instanceof Event || newTask instanceof Deadline) {
            msg += "| " + newTask.getExtraInfoShortened();
        }
        try {
            FileWriter fw = new FileWriter(FILENAME, true);
            fw.write(msg);
            fw.close();
            this.taskList.add(newTask);

        } catch (IOException e) {
            throw new DukeException("Erm... Error saving tasks to file");
        }
    }


    public void checkDateFormat(String date) throws DukeException {
        try {
            LocalDate d = LocalDate.parse(date, this.formatter);

        } catch (Exception ex) {

            throw new DukeException("Erm... Date not keyed in correct format! Correct format is yyyy-MM-dd"+ex);
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.getSize();
    }
    public void delete(Task t) {
        this.taskList.delete(t);
    }
}