public class FileParseInput {
    public static Task parse(String input) {
        String[] instruction = input.split("!");
        if (instruction[0].equals("T")) {
            return parseTodo(instruction);
        } else if (instruction[0].equals("E")) {
            return parseEvent(instruction);
        } else {
            return parseDeadline(instruction);
        }
    }

    public static Todo parseTodo(String[] instruction) {
        Todo todo = new Todo(instruction[2]);
        if (instruction[1].equals("1")) {
            todo.setDone();
        }
        return todo;
    }

    public static Event parseEvent(String[] instruction) {
        Event event = new Event(instruction[2], instruction[3],instruction[4]);
        if (instruction[1].equals("1")) {
            event.setDone();
        }
        return event;
    }

    public static Deadline parseDeadline(String[] instruction) {
        Deadline deadline = new Deadline(instruction[2], instruction[3]);
        if (instruction[1].equals("1")) {
            deadline.setDone();
        }
        return deadline;
    }
}
