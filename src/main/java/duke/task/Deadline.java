package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task that inherits from Task
 */

public class Deadline extends Task{

    private String deadline;

    public Deadline(String deadline, String task) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline specified by user.
     *
     * @return Deadline of Deadline task.
     * @throws DateTimeParseException When user does not specify date of deadline in "MMM d yyyy" format.
     */
    public String getDeadline() throws DateTimeParseException {
        try {
            LocalDate ddl = LocalDate.parse(deadline);
            return "(by: " + ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } catch (DateTimeParseException e) {
            return "(by: " + deadline + ")";
        }
    }

    /**
     * Overrides Task.add() to specify the Deadline task to be added.
     *
     * @return String representation of Deadline task to be added.
     */
    @Override
    public String add() {
            return this.getCat()+ this.marked() + " "
                    + this.getTask()
                    + this.getDeadline();

    }

    /**
     * Overrides Task.writeToFile() to specify the Deadline task to be added to the File.
     *
     * @param filePath Filepath to the file to be written to.
     * @throws IOException When file does not exist.
     */
    @Override
    public void writeToFile(File filePath) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            if (!this.isDone()) {
                fw.write("D/0/" + this.getTask() + "/" + deadline + "\n");
            } else {
                fw.write("D/1/" + this.getTask() + "/" + deadline + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("file not found! try again xx");
        }
    }

}
