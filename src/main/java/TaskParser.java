public class TaskParser {
    public static Task parseTask(String taskString) throws YapperException {
        String[] parts = taskString.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            String by = parts[3];
            return new Deadline(description, isDone, by);
        case "E":
            String from = parts[3];
            String to = parts[4];
            return new Event(description, isDone, from, to);
        default:
            throw new YapperException("The task type you're yapping about is invalid.");
        }
    }
}
