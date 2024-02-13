package duke;
import duke.Command.*;
import duke.Task.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Parser {
    static Command parseCommand(String command) throws DukeException {
        String[] parts = command.split(" ", 2);
    
        // Ensure there's at least one part
        if (parts.length < 1) {
            throw new DukeException("Command is empty.");
        }
    
        String keyword = parts[0].toLowerCase(); // Convert to lowercase for case-insensitive comparison
    
        switch (keyword) {
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of a " + keyword + " cannot be empty.");
                }
                return new AddCommand();
            case "delete":
                return new DeleteCommand();
            case "mark":
                return new MarkCommand();
            case "unmark":
                return new UnmarkCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
    
    
    

    static Task parseTaskFromString(String line) throws DukeException {
        String[] parts = line.split("\\|");
    
        if (parts.length < 3) {
            throw new DukeException("Invalid task format: " + line);
        }
    
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1"); // "1" represents task is done
        String description = parts[2].trim();
    
        switch (type) {
            case "D":
                if (parts.length < 4) {
                    throw new DukeException("Invalid deadline task format: " + line);
                }
                String by = parts[3].trim();
                LocalTime byTime = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
                DeadlineTask deadlineTask = new DeadlineTask(description, byTime);
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                return deadlineTask;
            case "E":
                if (parts.length < 5) {
                    throw new DukeException("Invalid event task format: " + line);
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                LocalTime startTime = LocalTime.parse(from, DateTimeFormatter.ofPattern("HHmm"));
                LocalTime endTime = LocalTime.parse(to, DateTimeFormatter.ofPattern("HHmm"));
                EventTask eventTask = new EventTask(description, startTime, endTime);
                if (isDone) {
                    eventTask.markAsDone();
                }
                return eventTask;
            default:
                ToDoTask toDoTask = new ToDoTask(description);
                if (isDone) {
                    toDoTask.markAsDone();
                }
                return toDoTask;
        }
    }
    
}

