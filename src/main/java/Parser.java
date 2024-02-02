
public class Parser {
    // Method to parse task from a line read from file
    public Task parseTaskFromFileString(String line) throws DuchessException {
        Task task = null;
        // Parse the line and create task objects accordingly
        // Example line format: "T | 1 | read book"
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = parts[3].trim();
                task = new Deadline(description, isDone, by);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, from, to);
                break;
            default:
                System.out.println("Unknown task type: " + type);
        }
        return task;
    }
}
