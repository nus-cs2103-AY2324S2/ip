package duke;

/**
 * Creates a task object from a line in the file.
 */
public class TaskBuilder {
    /**
     * Creates a task object from a line in the file.
     * 
     * @param line Line in the file.
     * @return Task object.
     * @throws DukeException If the line is invalid.
     */
    public static Task createTaskFromFileLine(String line) throws DukeException {
        String[] lineSplit = line.split(" \\| ");
        String type = lineSplit[0];
        String status = lineSplit[1];
        String description = lineSplit[2];
        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String byDate = lineSplit[3];
            task = new Deadline(description, byDate);
            break;
        case "E":
            String fromDate = lineSplit[3];
            String toDate = lineSplit[4];
            task = new Event(description, fromDate, toDate);
            break;
        default:
            throw new DukeException("File corrupted.");
        }
        if (status.equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
