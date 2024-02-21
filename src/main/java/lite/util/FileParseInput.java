package lite.util;

import lite.task.Deadline;
import lite.task.Event;
import lite.task.Task;
import lite.task.Todo;

public class FileParseInput {
    //Usage inspired by 0-yibai

    /**
     * Returns a Task based on the input given
     * @param input Input string given
     * @return Task corresponding to the input
     */
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

    /**
     * Returns a Todo Task
     * @param instruction The Todo description and status
     */
    public static Todo parseTodo(String[] instruction) {
        Todo todo = new Todo(instruction[2]);
        if (instruction[1].equals("1")) {
            todo.setDone();
        }
        return todo;
    }

    /**
     * Returns an Event Task
     * @param instruction The Event description, start, end, and status
     */
    public static Event parseEvent(String[] instruction) {
        Event event = new Event(instruction[2], instruction[3],instruction[4]);
        if (instruction[1].equals("1")) {
            event.setDone();
        }
        return event;
    }

    /**
     * Returns a deadline task
     * @param instruction The Deadline description and due date
     */
    public static Deadline parseDeadline(String[] instruction) {
        Deadline deadline = new Deadline(instruction[2], instruction[3]);
        if (instruction[1].equals("1")) {
            deadline.setDone();
        }
        return deadline;
    }
}
