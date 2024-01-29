import exceptions.BluException;

public class TaskDecoder {

    private static ToDo parseToDo(String[] tokens) {
        ToDo todo = new ToDo(tokens[2]);
        if (tokens[1] == "T") {
            todo.setMarked();
        }
        return todo;
    }

    private static Deadline parseDeadline(String[] tokens) {
        Deadline deadline = new Deadline(tokens[2], tokens[3]);
        if (tokens[1] == "T") {
            deadline.setMarked();
        }
        return deadline;
    }

    private static Event parseEvent(String[] tokens) {
        Event event = new Event(tokens[2], tokens[3], tokens[4]);
        if (tokens[1] == "T") {
            event.setMarked();
        }
        return event;
    }

    public static Task decodeCsv(String csv) throws BluException {
        String[] tokens = csv.split(",");
        String type = tokens[0];
        
        switch (type) {
        case "T":
            return parseToDo(tokens);
        case "D":
            return parseDeadline(tokens);
        case "E":
            return parseEvent(tokens);
        default:
            throw new BluException("Could not parse Task from CSV");
        }
    }
    
}
