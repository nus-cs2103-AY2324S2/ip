public class Parser {
    public static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    public static Task convertTask(String line) {
        String taskType = line.substring(1, 2);
        boolean isDone = line.substring(5, 6).equals("X");
        String description;

        if (taskType.equals("T")) {
            description = line.substring(8);
        } else if (taskType.equals("D")) {
            int byIndex = line.indexOf("(by: ");
            int endIndex = line.indexOf(")");
            description = line.substring(8, byIndex - 1) + line.substring(endIndex + 1);
        } else if (taskType.equals("E")) {
            int fromIndex = line.indexOf("(from: ");
            int toIndex = line.indexOf(" to: ");
            int endIndex = line.indexOf(")");
            description = line.substring(8, fromIndex - 1) + line.substring(endIndex + 1);
        } else {
            return null;
        }

        Task task;

        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                int byIndex = line.indexOf("(by: ");
                int endIndex = line.indexOf(")");
                String by = line.substring(byIndex + 5, endIndex);
                task = new Deadline(description, by);
                break;
            case "E":
                int fromIndex = line.indexOf("(from: ");
                int toIndex = line.indexOf(" to: ");
                int end = line.indexOf(")");
                String from = line.substring(fromIndex + 7, toIndex);
                String to = line.substring(toIndex + 5, end);
                task = new Event(description, from, to);
                break;
            default:
                return null;
        }

        if (isDone) {
            task.mark();
        }

        return task;
    }
}
