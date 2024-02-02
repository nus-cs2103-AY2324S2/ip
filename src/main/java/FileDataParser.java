import java.util.InputMismatchException;

public class FileDataParser extends Parser {
    public static Task parseLine(String line) throws InputMismatchException {
        String[] words = line.split("#!#");
        if (words[0].equals("T")) {
            return FileDataParser.parseTodo(words);
        }
        if (words[0].equals("D")) {
            return FileDataParser.parseDeadline(words);
        }
        if (words[0].equals("E")) {
            return FileDataParser.parseEvent(words);
        }
        throw new InputMismatchException();
    }

    private static Todo parseTodo(String[] words) {
        Todo todo = new Todo(words[2]);
        if (words[1].equals("1")) {
            todo.setDone();
        }
        return todo;
    }

    private static Deadline parseDeadline(String[] words) {
        Deadline deadline = new Deadline(words[2], words[3]);
        if (words[1].equals("1")) {
            deadline.setDone();
        }
        return deadline;
    }

    private static Event parseEvent(String[] words) {
        Event event = new Event(words[2], words[3], words[4]);
        if (words[1].equals("1")) {
            event.setDone();
        }
        return event;
    }
}
