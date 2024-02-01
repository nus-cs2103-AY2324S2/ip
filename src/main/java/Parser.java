public class Parser {
    public static Command parse(String userInput) throws DukeException {
        String[] parts = userInput.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                return new AddCommand(new Todo(parts[1].trim()));
            case "deadline":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = parts[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                    throw new DukeException("The deadline must include a time.");
                }
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            case "event":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                String[] eventParts = parts[1].split(" /from ");
                if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                    throw new DukeException("The event time must include a start and end time.");
                }
                String[] fromto = eventParts[1].split("/to");
                if (fromto.length < 2 || fromto[0].trim().isEmpty() || fromto[1].trim().isEmpty()) {
                    throw new DukeException("Both start and end times must be provided for an event.");
                }
                return new AddCommand(new Event(eventParts[0].trim(), fromto[0].trim(), fromto[1].trim()));
            case "mark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The task number to mark as done cannot be empty.");
                }
                try {
                    int indexToMark = Integer.parseInt(parts[1].trim()) - 1;
                    return new MarkCommand(indexToMark);
                } catch (NumberFormatException e) {
                    throw new DukeException("The task number to mark as done must be a number.");
                }
            case "unmark": 
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The task number to unmark as done cannot be empty.");
                }
                try {
                    int indexToUnmark = Integer.parseInt(parts[1].trim()) - 1;
                    return new UnmarkCommand(indexToUnmark);
                } catch (NumberFormatException e) {
                    throw new DukeException("The task number to unmark as done must be a number.");
                }
            case "delete":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The task number to delete cannot be empty.");
                }
                try {
                    int indexToDelete = Integer.parseInt(parts[1].trim()) - 1;
                    return new DeleteCommand(indexToDelete);
                } catch (NumberFormatException e) {
                    throw new DukeException("The task number to delete must be a number.");
                }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseLineToTask(String line) throws DukeException {
        String[] parts = line.split(" \\| ");

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
    
        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                String by = parts[3].trim();
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                String[] timeParts = parts[3].split(" to ");
                String start = timeParts[0].substring(6).trim(); // Remove "from " prefix
                String end = timeParts[1].trim();
                Event event = new Event(description, start, end);
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type in file: " + type);
        }
    }

}
