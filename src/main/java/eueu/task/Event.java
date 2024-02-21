package eueu.task;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task that inherits from Task
 */
public class Event extends Task {
    private String start;
    private String end;
    static final String FILE_NOT_FOUND = "file not found! try again xx";


    public Event(String start, String end, String task) {
        super(task);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the time of the Event task specified by user.
     *
     * @return Time/Date Event task is happening.
     * @throws DateTimeParseException When user does not specify date of event in "MMM d yyyy" format.
     */
    public String getEvent() throws DateTimeParseException {
         try {
             LocalDate startDate = LocalDate.parse(start);
             LocalDate endDate = LocalDate.parse(end);
             return ("from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + ")");
         } catch (DateTimeParseException e) {
            return "(from: " + start + " to: " + end + ")";
        }
    }

    /**
     * Overrides Task.add() to specify the Event task to be added.
     *
     * @return String representation of Event task to be added.
     */
    @Override
    public String add() {
            return this.getCat() + this.marked() + " "
                    + this.getTask() + " " + this.getEvent();
    }

    /**
     * Overrides Task.writeToFile() to specify the Event task to be added to the File.
     *
     * @param filePath Filepath to the file to be written to.
     * @throws IOException When file does not exist.
     */
    @Override
    public void writeToFile(File filePath) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            if (!this.isDone()) {
                fw.write("E/0/" + this.getTask() + "/" + start + "/" + end + "\n");
            } else {
                fw.write("E/1/" + this.getTask() + "/" + start + "/" + end + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
