package echon;

/**
 * Creates a task object from a line in the file.
 */
public class TaskBuilder {
    private static Task buildTentativeEvent(String description, String[] lineSplit)
            throws EchonException {
        String fromDate = lineSplit[3];
        String toDate = lineSplit[4];
        TentativeEvent event = new TentativeEvent(description, fromDate, toDate);
        for (int i = 5; i < lineSplit.length; i += 2) {
            String tentativeFromDate = lineSplit[i];
            String tentativeToDate = lineSplit[i + 1];
            event.addTentativeSlot(tentativeFromDate, tentativeToDate);
        }
        return event;
    }

    /**
     * Creates a task object from a line in the file.
     *
     * @param line Line in the file.
     * @return Task object.
     * @throws EchonException If the line is invalid.
     */
    public static Task createTaskFromFileLine(String line) throws EchonException {
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
        case "TE":
            task = buildTentativeEvent(description, lineSplit);
            break;
        default:
            throw new EchonException("File corrupted.");
        }
        if (status.equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
