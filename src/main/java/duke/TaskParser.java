package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The type Task parser.
 * Used for parsing task details in text format to Task Objects
 */
public class TaskParser {
    private static final String MARKED_TAG="[X]";
    private static final String TODO_TAG="[T]";
    private static final String TODO_TYPE="T";
    private static final String EVENT_TAG="[E]";
    private static final String EVENT_TYPE="E";
    private static final String DEADLINE_TAG="[D]";
    private static final String DEADLINE_TYPE="D";
    private static final String FROM="(from: ";
    private static final String TO=" to:";
    private static final String BY="(by: ";

    /**
     * Parse task in String to Task objects.
     *
     * @param taskstring the taskstring
     * @return the task
     */
    public static Task parse(String taskstring){
        assert taskstring != null;
        assert !taskstring.equals("");

        Task task;
        String startString = taskstring.substring(taskstring.indexOf("["),3);
        switch (startString) {
            case "[T]":
                task = parseTodo(taskstring);
                break;
            case "[D]":
                task = parseDeadline(taskstring);
                break;
            case "[E]":
                task = parseEvent(taskstring);
                break;
            default:
                task = parseOthers(taskstring);
        }
        assert task != null;

        return task;
    }

    //[T] | [X] | read book
    //[D] | [X] | return book (/by Sunday)
    //[E] | [X] | meeting (from: Mar 1 2024 2pm to: 4pm)
    private static Task parseTodo(String todoStr){
        Todo todo;
        String taskType = todoStr.substring(0,3);
        String markedStr = todoStr.substring(6,9);
        boolean marked = MARKED_TAG.equals(markedStr);
        String taskname = todoStr.substring(11);
        taskname = taskname.trim();
        todo = new Todo(taskname,marked,-1);
        return todo;
    }

    //[E] | [X] | meeting (from: Mar 13 2024 12:00 to: Mar 1 2024 16:00)
    private static Task parseEvent(String evtStr){
        Event task;
        String taskType = evtStr.substring(0,3);
        String markedStr = evtStr.substring(6,9);
        boolean marked = MARKED_TAG.equals(markedStr);
        String taskname = evtStr.substring(11, evtStr.indexOf(FROM));
        taskname = taskname.replace('|',' ');
        taskname = taskname.trim();
        String from = evtStr.substring(evtStr.indexOf(FROM), evtStr.indexOf(TO));
        from = from.substring(7);//to exclude "(from: "
        String to = evtStr.substring(evtStr.indexOf(TO),evtStr.indexOf(")"));
        to = to.substring(4);//to exclude "to: "
        to = to;//to exclude last bracket
        from = from.trim();
        to = to.trim();

        task = new Event(taskname,marked, -1, EVENT_TYPE, from, to);
        return task;
    }

    //[D] | [X] | return book (/by Mar 13 2024 15:00)
    private static Deadline parseDeadline(String deadlineStr){
        Deadline task;
        String taskType = deadlineStr.substring(0,3);
        String markedStr = deadlineStr.substring(6,9);
        boolean marked = MARKED_TAG.equals(markedStr);
        String taskname = deadlineStr.substring(11, deadlineStr.indexOf(BY));
        taskname = taskname.replace('|',' ');
        taskname = taskname.trim();

        String by = deadlineStr.substring(deadlineStr.indexOf(BY));
        by = by.substring(5);//to exclude "(by/ "
        by = by.substring(0, by.length()-1);//to exclude last bracket
        task = new Deadline(taskname,marked, -1, DEADLINE_TYPE, by);

        return task;
    }
    private static Task parseOthers(String othStr){
        Task task;
        String taskType = othStr.substring(0,3);
        String markedStr = othStr.substring(6,9);
        boolean marked = MARKED_TAG.equals(markedStr);
        String taskname = othStr.substring(11);
        taskname = taskname.trim();
        task = new Task(taskname,marked,-1);

        return task;
    }

}
